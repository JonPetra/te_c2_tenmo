package com.techelevator.tenmo.controller;

import com.techelevator.tenmo.dao.TransferDao;
import com.techelevator.tenmo.model.Transfer;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("transfers")
public class TransferController {

    private TransferDao transferDao;

    public TransferController(TransferDao transferDao) {
        this.transferDao = transferDao;
    }

    //create transfer
    @RequestMapping(method = RequestMethod.POST)
    public Transfer createTransfer(@RequestBody Transfer transfer) {
        return transferDao.createTransfer(transfer);
    }

    //list transfers by user
    @RequestMapping(path = "/user/{userId}", method = RequestMethod.GET)
    public List<Transfer> listTransfersByUser(@PathVariable Long userId) {
        return transferDao.listTransfersByUser(userId);
    }

    //get transfer by transfer id
    @RequestMapping(path = "/{transferId}", method = RequestMethod.GET)
    public Transfer getTransferById(@PathVariable Long transferId) {
        return transferDao.getTransferById(transferId);
    }

    //send
    @RequestMapping(path = "/send/{userId}", method = RequestMethod.PUT)
    public void send(@RequestBody Long transferId){
        transferDao.send(transferId);
    }

    //withdraw
    @RequestMapping(path = "/withdraw/{userId}", method = RequestMethod.PUT)
    public void withdraw(@RequestBody Long transferId){
        transferDao.withdraw(transferId);
    }

}
