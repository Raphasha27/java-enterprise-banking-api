package com.banking.service;

import com.banking.model.Account;
import com.banking.model.User;
import com.banking.repository.AccountRepository;
import com.banking.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

@Service
@Transactional
public class AccountService {

    private final AccountRepository accountRepository;
    private final UserRepository userRepository;

    public AccountService(AccountRepository accountRepository, UserRepository userRepository) {
        this.accountRepository = accountRepository;
        this.userRepository = userRepository;
    }

    public Account createAccount(String email, BigDecimal initialBalance) {
        User user = userRepository.findByEmail(email)
            .orElseThrow(() -> new RuntimeException("User not found"));

        Account account = new Account(user, initialBalance);
        return accountRepository.save(account);
    }

    @Transactional(readOnly = true)
    public Account getAccount(UUID accountId, String email) {
        Account account = accountRepository.findById(accountId)
            .orElseThrow(() -> new RuntimeException("Account not found"));

        if (!account.getUser().getEmail().equals(email)) {
            throw new RuntimeException("Unauthorized access to account");
        }

        return account;
    }

    @Transactional(readOnly = true)
    public List<Account> getAllAccounts(String email) {
        User user = userRepository.findByEmail(email)
            .orElseThrow(() -> new RuntimeException("User not found"));
        return accountRepository.findByUserId(user.getId());
    }

    public void transferFunds(UUID fromAccountId, UUID toAccountId, BigDecimal amount, String email) {
        Account fromAccount = getAccount(fromAccountId, email);
        Account toAccount = accountRepository.findById(toAccountId)
            .orElseThrow(() -> new RuntimeException("Destination account not found"));

        if (fromAccount.getBalance().compareTo(amount) < 0) {
            throw new RuntimeException("Insufficient funds");
        }

        fromAccount.setBalance(fromAccount.getBalance().subtract(amount));
        toAccount.setBalance(toAccount.getBalance().add(amount));

        accountRepository.save(fromAccount);
        accountRepository.save(toAccount);
    }

    @Transactional(readOnly = true)
    public BigDecimal getBalance(UUID accountId, String email) {
        Account account = getAccount(accountId, email);
        return account.getBalance();
    }
}
