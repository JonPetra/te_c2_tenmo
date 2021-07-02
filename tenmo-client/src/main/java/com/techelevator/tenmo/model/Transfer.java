package com.techelevator.tenmo.model;

import io.cucumber.java.sl.In;

public class Transfer {

    private Integer transferId;
    private Integer transferTypeId;
    private Integer transferStatusId;
    private Integer accountFrom;
    private Integer accountTo;
    private Double amount;

//    public Transfer(Integer transferTypeId, Integer transferStatusId, Integer accountFrom, Integer accountTo, Double amount){
//        this.transferTypeId = transferTypeId;
//        this.transferStatusId = transferStatusId;
//        this.accountFrom = accountFrom;
//        this.accountTo = accountTo;
//        this.amount = amount;
//    }

    public Integer getTransferId() {
        return transferId;
    }

    public void setTransferId(Integer transferId) {
        this.transferId = transferId;
    }

    public Integer getTransferTypeId() {
        return transferTypeId;
    }

    public void setTransferTypeId(Integer transferTypeId) {
        this.transferTypeId = transferTypeId;
    }

    public Integer getTransferStatusId() {
        return transferStatusId;
    }

    public void setTransferStatusId(Integer transferStatusId) {
        this.transferStatusId = transferStatusId;
    }

    public Integer getAccountFrom() {
        return accountFrom;
    }

    public void setAccountFrom(Integer accountFrom) {
        this.accountFrom = accountFrom;
    }

    public Integer getAccountTo() {
        return accountTo;
    }

    public void setAccountTo(Integer accountTo) {
        this.accountTo = accountTo;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }
    public String transferToString() {
        return transferId + "        To: " + accountTo + "    $ " + amount;
    }
}
