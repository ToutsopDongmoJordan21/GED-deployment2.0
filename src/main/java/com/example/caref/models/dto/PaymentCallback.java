package com.example.caref.models.dto;

import com.example.caref.models.enums.PaymentMode;

public class PaymentCallback {

    public PaymentCallback() {
    }

    public String getTxnid() {
        return txnid;
    }

    public PaymentCallback setTxnid(String txnid) {
        this.txnid = txnid;
        return this;
    }

    public String getMihpayid() {
        return mihpayid;
    }

    public PaymentCallback setMihpayid(String mihpayid) {
        this.mihpayid = mihpayid;
        return this;
    }

    public String getStatus() {
        return status;
    }

    public PaymentCallback setStatus(String status) {
        this.status = status;
        return this;
    }

    public String getHash() {
        return hash;
    }

    public PaymentCallback setHash(String hash) {
        this.hash = hash;
        return this;
    }

    private String txnid;
    private String mihpayid;

    public PaymentMode getMode() {
        return mode;
    }

    public PaymentCallback setMode(PaymentMode mode) {
        this.mode = mode;
        return this;
    }

    private PaymentMode mode;
    private String status;
    private String hash;
}

