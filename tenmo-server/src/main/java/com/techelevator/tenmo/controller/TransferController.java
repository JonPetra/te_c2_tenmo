package com.techelevator.tenmo.controller;

import com.techelevator.tenmo.dao.TransferDao;
import com.techelevator.tenmo.model.Transfer;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("transfers")
@PreAuthorize("isAuthenticated()")
public class TransferController {

    private TransferDao transferDao;

    public TransferController(TransferDao transferDao) {
        this.transferDao = transferDao;
    }

    //create transfer
    @RequestMapping(method = RequestMethod.POST)
    public Transfer createTransfer(@Valid @RequestBody Transfer transfer) {
        return transferDao.createTransfer(transfer);
    }

    //list transfers by user
    @RequestMapping(path = "/user/{userId}", method = RequestMethod.GET)
    public List<Transfer> listTransfersByUser(@PathVariable Integer userId) {
        return transferDao.listTransfersByUser(userId);
    }

    //get transfer by transfer id
    @RequestMapping(path = "/{transferId}", method = RequestMethod.GET)
    public Transfer getTransferById(@PathVariable Integer transferId) {
        return transferDao.getTransferById(transferId);
    }

    //send
    @PreAuthorize("denyAll")
    @RequestMapping(path = "/send/{userId}", method = RequestMethod.PUT)
    public void send(@Valid @RequestBody Integer transferId){
        transferDao.send(transferId);
    }

    //withdraw
    @PreAuthorize("denyAll")
    @RequestMapping(path = "/withdraw/{userId}", method = RequestMethod.PUT)
    public void withdraw(@Valid @RequestBody Integer transferId){
        transferDao.withdraw(transferId);
    }

}
