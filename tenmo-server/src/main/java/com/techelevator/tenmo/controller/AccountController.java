package com.techelevator.tenmo.controller;

import com.techelevator.tenmo.dao.AccountDao;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("accounts")
public class AccountController {

    private AccountDao accountDao;

    public AccountController(AccountDao accountDao) {
        this.accountDao = accountDao;
    }

    //get balance
    @RequestMapping(path = "/{userId}", method = RequestMethod.GET)
    public Double getBalance(@PathVariable Integer userId) {
        return accountDao.getBalance(userId);
    }

    //get username from account id
    @RequestMapping(path = "/retrieve_username/{accountId}", method = RequestMethod.GET)
    public String getUsername(@PathVariable Integer accountId) {
        return accountDao.getUsernameFromAccountId(accountId);
    }

    //get account id from user id
    @RequestMapping(path = "/retrieve_account_id/{userId}", method = RequestMethod.GET)
    public Integer getAccountId(@PathVariable Integer userId) {
        return accountDao.getAccountIdFromUserId(userId);
    }
}
