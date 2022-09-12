package com.example.caref.models;

import com.example.caref.models.enums.PaymentMode;
import com.example.caref.models.enums.PaymentStatus;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "Payment")
public class Payment extends AbstractIforce5Audit{
    public Long getId() {
        return id;
    }

    public Payment setId(Long id) {
        this.id = id;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public Payment setEmail(String email) {
        this.email = email;
        return this;
    }

    public String getName() {
        return name;
    }

    public Payment setName(String name) {
        this.name = name;
        return this;
    }

    public String getPhone() {
        return phone;
    }

    public Payment setPhone(String phone) {
        this.phone = phone;
        return this;
    }

    public String getProductInfo() {
        return productInfo;
    }

    public Payment setProductInfo(String productInfo) {
        this.productInfo = productInfo;
        return this;
    }

    public String getAmount() {
        return amount;
    }

    public Payment setAmount(String amount) {
        this.amount = amount;
        return this;
    }

    public PaymentStatus getPaymentStatus() {
        return paymentStatus;
    }

    public Payment setPaymentStatus(PaymentStatus paymentStatus) {
        this.paymentStatus = paymentStatus;
        return this;
    }

    public Date getPaymentDate() {
        return paymentDate;
    }

    public Payment setPaymentDate(Date paymentDate) {
        this.paymentDate = paymentDate;
        return this;
    }

    public String getTxnId() {
        return txnId;
    }

    public Payment setTxnId(String txnId) {
        this.txnId = txnId;
        return this;
    }

    public String getMihpayId() {
        return mihpayId;
    }

    public Payment setMihpayId(String mihpayId) {
        this.mihpayId = mihpayId;
        return this;
    }

    public PaymentMode getMode() {
        return mode;
    }

    public Payment setMode(PaymentMode mode) {
        this.mode = mode;
        return this;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id",
            referencedColumnName = "id")
    private User user;

    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column
    private String email;
    @Column
    private String name;
    @Column
    private String phone;
    @Column
    private String productInfo;
    @Column
    private String amount;
    @Column
    @Enumerated(EnumType.STRING)
    private PaymentStatus paymentStatus;
    @Column
    @Temporal(TemporalType.DATE)
    private Date paymentDate;
    @Column
    private String txnId;
    @Column
    private String mihpayId;
    @Column
    @Enumerated(EnumType.STRING)
    private PaymentMode mode;
}

