package com.techelevator.tenmo.dao;

import com.techelevator.tenmo.model.Transfer;

import java.util.List;

public interface TransferDao {

    Transfer createTransfer(Transfer transfer);

    List<Transfer> listTransfersByUser(Integer userId);

    Transfer getTransferById(Integer transferId);

    void send(Integer transferId);

    void withdraw(Integer transferId);
}
