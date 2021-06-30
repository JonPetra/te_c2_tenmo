package com.techelevator.tenmo.dao;

import com.techelevator.tenmo.model.Account;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;

public class JdbcAccountDao implements AccountDao {

    private JdbcTemplate jdbcTemplate;

    public JdbcAccountDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Account getBalance(int userId) {
        Account account = new Account();
        String sql = "SELECT a.balance " +
                "FROM accounts a " +
                "INNER JOIN users u ON u.user_id = a.user_id " +
                "WHERE user_id = ?;";
        SqlRowSet results = jdbcTemplate.queryForRowSet(sql, Integer.class, userId);
        if (results.next()) {
            account = mapRowToAccount(results);
        }
        return account;
    }

    // Might need to change depending on transfer logic
    @Override
    public void updateBalance(Account user) {
        String sql = "UPDATE accounts " +
                "SET balance = ? " +
                "WHERE user_id = ?;";
        jdbcTemplate.update(sql, user.getBalance(), user.getUserId());
    }

    private Account mapRowToAccount(SqlRowSet rowSet) {
        Account account = new Account();
        account.setAccountId(rowSet.getLong("account_id"));
        account.setUserId(rowSet.getInt("user_id"));
        account.setBalance(rowSet.getLong("balance"));
        return account;
    }
}
