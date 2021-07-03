package com.techelevator.tenmo.dao;

public interface AccountDao {

    Double getBalance(Integer userId);

    String getUsernameFromAccountId(Integer accountId);

    Integer getAccountIdFromUserId(Integer userId);

}
