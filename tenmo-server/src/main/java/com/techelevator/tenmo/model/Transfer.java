package com.techelevator.tenmo.model;

public class Transfer {

    private Long transferId;
    private Integer transferTypeId;
    private Integer transferStatusId;
    private Integer accountFrom;
    private Integer accountTo;
    private Long amount;

    public Transfer() { }

    public Transfer(Long transferId, Integer transferTypeId, Integer transferStatusId, Integer accountFrom, Integer accountTo, Long amount) {
        this.transferId = transferId;
        this.transferTypeId = transferTypeId;
        this.transferStatusId = transferStatusId;
        this.accountFrom = accountFrom;
        this.accountTo = accountTo;
        this.amount = amount;
    }

    public Long getId() {
        return transferId;
    }
    public void setId(Long transferId) {
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

    public Long getAmount() {
        return amount;
    }
    public void setAmount(Long amount) {
        this.amount = amount;
    }
}
