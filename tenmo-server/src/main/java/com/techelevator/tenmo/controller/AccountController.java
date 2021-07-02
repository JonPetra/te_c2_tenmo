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
    public Double getBalance(@PathVariable int userId) {
        return accountDao.getBalance(userId);
    }

}
