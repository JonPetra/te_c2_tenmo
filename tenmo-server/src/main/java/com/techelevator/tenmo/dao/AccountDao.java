package com.techelevator.tenmo.dao;

import com.techelevator.tenmo.model.Account;

import java.util.List;

public interface AccountDao {

    Long getBalance(int userId);

    void updateBalance(Account user);
}
