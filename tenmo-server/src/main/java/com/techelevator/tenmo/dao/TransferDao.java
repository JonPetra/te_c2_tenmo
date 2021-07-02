package com.techelevator.tenmo.dao;

import com.techelevator.tenmo.model.Transfer;

import java.util.List;

public interface TransferDao {

    Transfer createTransfer(Transfer transfer);

    List<Transfer> listTransfersByUser(Long userId);

    Transfer getTransferById(Long transferId);

    void send(Long transferId);

    void withdraw(Long transferId);
}
