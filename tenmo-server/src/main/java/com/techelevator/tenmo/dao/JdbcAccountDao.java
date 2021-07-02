package com.techelevator.tenmo.dao;

import com.techelevator.tenmo.model.Account;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

@Component
public class JdbcAccountDao implements AccountDao {

    private JdbcTemplate jdbcTemplate;

    public JdbcAccountDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Double getBalance(int userId) {
        String sql = "SELECT a.balance " +
                "FROM accounts a " +
                "INNER JOIN users u ON u.user_id = a.user_id " +
                "WHERE u.user_id = ?;";
        Double balance = jdbcTemplate.queryForObject(sql, Double.class, userId);
        if (balance != null) {
            return balance;
        } else {
            return null;
        }
    }

    private Account mapRowToAccount(SqlRowSet rowSet) {
        Account account = new Account();
        account.setAccountId(rowSet.getLong("account_id"));
        account.setUserId(rowSet.getInt("user_id"));
        account.setBalance(rowSet.getDouble("balance"));
        return account;
    }
}
