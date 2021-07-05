package com.techelevator.tenmo.model;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

public class Transfer {

    private Integer transferId;
    @NotNull
    private Integer transferTypeId;
    @NotNull
    private Integer transferStatusId;
    @NotNull
    private Integer accountFrom;
    @NotNull
    private Integer accountTo;
    @Positive
    private Double amount;

    public Transfer() {
    }

    public Transfer(Integer transferId, Integer transferTypeId, Integer transferStatusId, Integer accountFrom, Integer accountTo, Double amount) {
        this.transferId = transferId;
        this.transferTypeId = transferTypeId;
        this.transferStatusId = transferStatusId;
        this.accountFrom = accountFrom;
        this.accountTo = accountTo;
        this.amount = amount;
    }

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
}
