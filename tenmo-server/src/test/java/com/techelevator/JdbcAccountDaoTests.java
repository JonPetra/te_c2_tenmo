package com.techelevator;

import com.techelevator.tenmo.dao.JdbcAccountDao;
import com.techelevator.tenmo.model.Account;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class JdbcAccountDaoTests extends TenmoDaoTests {

    private static final Account ACCOUNT_1 =
            new Account(2091, 1091, 1000.00);
    private static final Account ACCOUNT_2 =
            new Account(2092, 1092, 1000.00);
    private static final Account ACCOUNT_3 =
            new Account(2093, 1093, 1000.00);
    private static final Account ACCOUNT_4 =
            new Account(2094, 1094, 1000.00);
    private static final Account ACCOUNT_5 =
            new Account(2095, 1095, 1000.00);
    private static final Account ACCOUNT_6 =
            new Account(2096, 1096, 1000.00);

    private Account testAccount;
    private JdbcAccountDao sut;

    @Before
    public void setup() {
        sut = new JdbcAccountDao(dataSource);
        testAccount = new Account(2097, 1097, 1000.00);
    }

    //Double getBalance(Integer userId);
    @Test
    public void getBalance_returns_correct_balance() {
        Double balance = sut.getBalance(1091);
        Double expected = 1000.00;
        Assert.assertEquals(expected, balance);

        balance = sut.getBalance(1096);
        expected = 1000.00;
        Assert.assertEquals(expected, balance);
    }

    //String getUsernameFromAccountId(Integer accountId);
    @Test
    public void getUsernameFromAccountId_returns_correct_username() {
        String username = sut.getUsernameFromAccountId(2091);
        Assert.assertEquals("testuser1", username);

        username = sut.getUsernameFromAccountId(2096);
        Assert.assertEquals("testuser6", username);
    }

    //Integer getAccountIdFromUserId(Integer userId);
    @Test
    public void getAccountIdFromUserId_returns_correct_id() {
        Integer id = sut.getAccountIdFromUserId(1091);
        Integer expected = 2091;
        Assert.assertEquals(expected, id);

        id = sut.getAccountIdFromUserId(1096);
        expected = 2096;
        Assert.assertEquals(expected, id);
    }

    //helper method
    private void assertAccountsMatch(Account expected, Account actual) {
        Assert.assertEquals(expected.getAccountId(), actual.getAccountId());
        Assert.assertEquals(expected.getUserId(), actual.getUserId());
        Assert.assertEquals(expected.getBalance(), actual.getBalance());
    }
}
