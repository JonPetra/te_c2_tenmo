package com.techelevator.tenmo.controller;

import com.techelevator.tenmo.dao.AccountDao;
import com.techelevator.tenmo.model.Account;
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
    public Long getBalance(@PathVariable int userId) {
        return accountDao.getBalance(userId);
    }

    //update balance
    @RequestMapping(path = "/{userId}", method = RequestMethod.PUT)
    public void updateBalance(@RequestBody Account user){
        accountDao.updateBalance(user);
    }

}
