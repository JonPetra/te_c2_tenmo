package com.techelevator.tenmo.dao;

import com.techelevator.tenmo.model.Account;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;

@Component
public class JdbcAccountDao implements AccountDao {

    private final JdbcTemplate jdbcTemplate;

    public JdbcAccountDao(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public Double getBalance(Integer userId) {
        String sql = "SELECT a.balance " +
                "FROM accounts a " +
                "INNER JOIN users u ON u.user_id = a.user_id " +
                "WHERE u.user_id = ?;";
        return jdbcTemplate.queryForObject(sql, Double.class, userId);
    }

    @Override
    public String getUsernameFromAccountId(Integer accountId) {
        String sql = "SELECT u.username " +
                "FROM users u " +
                "INNER JOIN accounts a ON a.user_id = u.user_id " +
                "WHERE a.account_id = ?;";
        return jdbcTemplate.queryForObject(sql, String.class, accountId);
    }

    @Override
    public Integer getAccountIdFromUserId(Integer userId) {
        String sql = "SELECT a.account_id " +
                "FROM accounts a " +
                "INNER JOIN users u ON u.user_id = a.user_id " +
                "WHERE u.user_id = ?;";
        return jdbcTemplate.queryForObject(sql, Integer.class, userId);
    }

    private Account mapRowToAccount(SqlRowSet rowSet) {
        Account account = new Account();
        account.setAccountId(rowSet.getInt("account_id"));
        account.setUserId(rowSet.getInt("user_id"));
        account.setBalance(rowSet.getDouble("balance"));
        return account;
    }
}
