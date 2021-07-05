package com.techelevator;

import com.techelevator.tenmo.dao.JdbcAccountDao;
import com.techelevator.tenmo.model.Account;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class JdbcAccountDaoTests extends TenmoDaoTests {

    private static final Account ACCOUNT_1 =
            new Account(2001, 1001, 1000.00);
    private static final Account ACCOUNT_2 =
            new Account(2002, 1002, 1000.00);
    private static final Account ACCOUNT_3 =
            new Account(2003, 1003, 1000.00);
    private static final Account ACCOUNT_4 =
            new Account(2004, 1004, 1000.00);
    private static final Account ACCOUNT_5 =
            new Account(2005, 1005, 1000.00);
    private static final Account ACCOUNT_6 =
            new Account(2006, 1006, 1000.00);

    private Account testAccount;
    private JdbcAccountDao sut;

    @Before
    public void setup() {
        sut = new JdbcAccountDao(dataSource);
        testAccount = new Account(2007, 1007, 1000.00);
    }

    //Double getBalance(Integer userId);
    @Test
    public void getBalance_returns_correct_balance() {
        Double balance = sut.getBalance(1001);
        Double expected = 1000.00;
        Assert.assertEquals(expected, balance);

        balance = sut.getBalance(1006);
        expected = 1000.00;
        Assert.assertEquals(expected, balance);
    }

    //String getUsernameFromAccountId(Integer accountId);
    @Test
    public void getUsernameFromAccountId_returns_correct_username() {
        String username = sut.getUsernameFromAccountId(2001);
        Assert.assertEquals("testuser1", username);

        username = sut.getUsernameFromAccountId(2006);
        Assert.assertEquals("testuser6", username);
    }

    //Integer getAccountIdFromUserId(Integer userId);
    @Test
    public void getAccountIdFromUserId_returns_correct_id() {
        Integer id = sut.getAccountIdFromUserId(1001);
        Integer expected = 2001;
        Assert.assertEquals(expected, id);

        id = sut.getAccountIdFromUserId(1006);
        expected = 2006;
        Assert.assertEquals(expected, id);
    }

    //helper method
    private void assertAccountsMatch(Account expected, Account actual) {
        Assert.assertEquals(expected.getAccountId(), actual.getAccountId());
        Assert.assertEquals(expected.getUserId(), actual.getUserId());
        Assert.assertEquals(expected.getBalance(), actual.getBalance());
    }
}
