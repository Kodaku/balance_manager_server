package com.balance.balancemanager.rest;

import com.balance.balancemanager.entity.AppUser;
import com.balance.balancemanager.entity.Balance;
import com.balance.balancemanager.error.exceptions.ResourceNotFoundException;
import com.balance.balancemanager.service.appuser.AppUserService;
import com.balance.balancemanager.service.balance.BalanceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class BalanceController {

    private final AppUserService appUserService;
    private final BalanceService balanceService;

    @Autowired
    public BalanceController(AppUserService appUserService, BalanceService balanceService) {
        this.appUserService = appUserService;
        this.balanceService = balanceService;
    }

    private AppUser getAppUser(int userId) {
        AppUser appUser = appUserService.findById(userId);

        if (appUser == null) {
            throw new ResourceNotFoundException("User with ID " + userId + " not found");
        }

        return appUser;
    }

    private Balance getBalancePrivate(int balanceId) {
        Balance balance = balanceService.findById(balanceId);

        if (balance == null) {
            throw new ResourceNotFoundException("Balance with ID " + balanceId + " not found");
        }

        return balance;
    }

    @GetMapping("/balances")
    @CrossOrigin(origins = "http://localhost:3000")
    public List<Balance> getBalances() {
        return balanceService.findAll();
    }

    @GetMapping("/users/balances/{userId}")
    @CrossOrigin(origins = "http://localhost:3000")
    public List<Balance> getUserBalances(@PathVariable int userId) {
        AppUser appUser = getAppUser(userId);

        return appUser.getBalances();
    }

    @GetMapping("/balances/{balanceId}")
    @CrossOrigin(origins = "http://localhost:3000")
    public Balance getBalance(@PathVariable int balanceId) {
        return getBalancePrivate(balanceId);
    }

    @PostMapping("/balances/{userId}")
    @CrossOrigin(origins = "http://localhost:3000")
    public Balance saveBalance(@PathVariable int userId, @RequestBody Balance balance) {

        AppUser appUser = getAppUser(userId);

        balance.setId(0);
        appUser.addBalance(balance);

        appUserService.save(appUser);
        balanceService.save(balance);

        return balance;
    }

    @PutMapping("/balances/{balanceId}")
    @CrossOrigin(origins = "http://localhost:3000")
    public Balance updateBalance(@PathVariable int balanceId, @RequestBody Balance balance) {
        Balance currentBalance = getBalancePrivate(balanceId);

        currentBalance.setName(balance.getName());
        balanceService.save(currentBalance);

        return currentBalance;
    }

    @DeleteMapping("/balances/{balanceId}")
    @CrossOrigin(origins = "http://localhost:3000")
    public String deleteBalance(@PathVariable int balanceId) {
        // just check if the balance actually exists
        getBalancePrivate(balanceId);

        balanceService.deleteById(balanceId);
        return "Balance with id " + balanceId + " deleted";
    }
}
