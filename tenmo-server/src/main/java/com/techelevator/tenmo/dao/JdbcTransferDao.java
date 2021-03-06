package com.techelevator.tenmo.dao;

import com.techelevator.tenmo.model.Transfer;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.List;

@Component
public class JdbcTransferDao implements TransferDao {

    private JdbcTemplate jdbcTemplate;

    public JdbcTransferDao(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public Transfer createTransfer(Transfer transfer) {
        String sql = "INSERT INTO transfers (transfer_type_id, transfer_status_id, account_from, account_to, amount) " +
                "VALUES (?, ?, ?, ?, ?) RETURNING transfer_id;";
        Integer newId = jdbcTemplate.queryForObject(sql, Integer.class, transfer.getTransferTypeId(), transfer.getTransferStatusId(),
                transfer.getAccountFrom(), transfer.getAccountTo(), transfer.getAmount());
        send(newId);
        withdraw(newId);
        return getTransferById(newId);
    }

    @Override
    public List<Transfer> listTransfersByUser(Integer userId) {
        List<Transfer> transfers = new ArrayList<>();
        String sql = "SELECT t.transfer_id, t.transfer_type_id, t.transfer_status_id, t.account_from, t.account_to, t.amount " +
                "FROM transfers t " +
                "INNER JOIN accounts a ON a.account_id = t.account_from OR a.account_id = t.account_to " +
                "INNER JOIN users u ON u.user_id = a.user_id " +
                "WHERE u.user_id = ?;";
        SqlRowSet results = jdbcTemplate.queryForRowSet(sql, userId);
        while (results.next()) {
            transfers.add(mapRowToTransfer(results));
        }
        return transfers;
    }

    //do we need to add the person's name here?

    @Override
    public Transfer getTransferById(Integer transferId) {
        Transfer transfer = new Transfer();
        String sql = "SELECT t.transfer_id, t.transfer_type_id, t.transfer_status_id, t.account_from, t.account_to, t.amount " +
                "FROM transfers t " +
                "WHERE transfer_id = ?;";
        SqlRowSet results = jdbcTemplate.queryForRowSet(sql, transferId);
        if (results.next()) {
            transfer = mapRowToTransfer(results);
        }
        return transfer;
    }

    @Override
    public void send(Integer transferId) {
        String sql = "UPDATE accounts a " +
                "SET balance = a.balance + f.amount " +
                "FROM transfers f " +
                "WHERE f.transfer_id = ? AND a.account_id = f.account_to;";
        jdbcTemplate.update(sql, transferId);
    }

    @Override
    public void withdraw(Integer transferId) {
        String sql = "UPDATE accounts a " +
                "SET balance = a.balance - f.amount " +
                "FROM transfers f " +
                "WHERE f.transfer_id = ? AND a.account_id = f.account_from;";
        jdbcTemplate.update(sql, transferId);
    }

    private Transfer mapRowToTransfer(SqlRowSet rowSet) {
        Transfer transfer = new Transfer();
        transfer.setTransferId(rowSet.getInt("transfer_id"));
        transfer.setTransferTypeId(rowSet.getInt("transfer_type_id"));
        transfer.setTransferStatusId(rowSet.getInt("transfer_status_id"));
        transfer.setAccountFrom(rowSet.getInt("account_from"));
        transfer.setAccountTo(rowSet.getInt("account_to"));
        transfer.setAmount(rowSet.getDouble("amount"));
        return transfer;
    }
}
