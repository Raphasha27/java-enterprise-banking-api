package com.banking.controller;

import com.banking.model.Account;
import com.banking.service.AccountService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/accounts")
@Tag(name = "Account Management", description = "API for managing bank accounts")
public class AccountController {

    private final AccountService accountService;

    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

    @PostMapping
    @Operation(summary = "Create a new account")
    public ResponseEntity<Account> createAccount(
            @AuthenticationPrincipal UserDetails userDetails,
            @RequestParam BigDecimal initialBalance) {
        Account account = accountService.createAccount(userDetails.getUsername(), initialBalance);
        return ResponseEntity.status(HttpStatus.CREATED).body(account);
    }

    @GetMapping("/{accountId}")
    @Operation(summary = "Get account by ID")
    public ResponseEntity<Account> getAccount(
            @PathVariable UUID accountId,
            @AuthenticationPrincipal UserDetails userDetails) {
        Account account = accountService.getAccount(accountId, userDetails.getUsername());
        return ResponseEntity.ok(account);
    }

    @GetMapping
    @Operation(summary = "Get all accounts for current user")
    public ResponseEntity<List<Account>> getAllAccounts(
            @AuthenticationPrincipal UserDetails userDetails) {
        List<Account> accounts = accountService.getAllAccounts(userDetails.getUsername());
        return ResponseEntity.ok(accounts);
    }

    @PostMapping("/{accountId}/transfer")
    @Operation(summary = "Transfer funds between accounts")
    public ResponseEntity<Void> transferFunds(
            @PathVariable UUID accountId,
            @RequestParam UUID toAccountId,
            @RequestParam BigDecimal amount,
            @AuthenticationPrincipal UserDetails userDetails) {
        accountService.transferFunds(accountId, toAccountId, amount, userDetails.getUsername());
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{accountId}/balance")
    @Operation(summary = "Get account balance")
    public ResponseEntity<BigDecimal> getBalance(
            @PathVariable UUID accountId,
            @AuthenticationPrincipal UserDetails userDetails) {
        BigDecimal balance = accountService.getBalance(accountId, userDetails.getUsername());
        return ResponseEntity.ok(balance);
    }
}
