package com.example.caref.models;

import com.example.caref.models.enums.PaymentStatus;
import lombok.Data;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Date;

@Data
public class PaymentDetail {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Long id;
    private String email;
    private String name;
    private String phone;
    private String productInfo;
    private String amount;
    private String txnId;
    private String hash;
    private String sUrl;
    private String fUrl;
    private String key;

    public Date getPaymentDate() {
        return paymentDate;
    }

    public PaymentDetail setPaymentDate(Date paymentDate) {
        this.paymentDate = paymentDate;
        return this;
    }

    private Date paymentDate;

    public PaymentStatus getStatus() {
        return status;
    }

    public PaymentDetail setStatus(PaymentStatus status) {
        this.status = status;
        return this;
    }

    private PaymentStatus status;

    public String getTxnId() {
        return txnId;
    }

    public PaymentDetail setTxnId(String txnId) {
        this.txnId = txnId;
        return this;
    }

    public String getHash() {
        return hash;
    }

    public PaymentDetail setHash(String hash) {
        this.hash = hash;
        return this;
    }

    public String getsUrl() {
        return sUrl;
    }

    public PaymentDetail setsUrl(String sUrl) {
        this.sUrl = sUrl;
        return this;
    }

    public String getfUrl() {
        return fUrl;
    }

    public PaymentDetail setfUrl(String fUrl) {
        this.fUrl = fUrl;
        return this;
    }

    public String getKey() {
        return key;
    }

    public PaymentDetail setKey(String key) {
        this.key = key;
        return this;
    }

    private String postByName;
    private Long postById;

    public static final class PaymentDetailBuilder {
        public Long getId() {
            return id;
        }

        public PaymentDetailBuilder setId(Long id) {
            this.id = id;
            return this;
        }

        public String getEmail() {
            return email;
        }

        public PaymentDetailBuilder setEmail(String email) {
            this.email = email;
            return this;
        }

        public String getName() {
            return name;
        }

        public PaymentDetailBuilder setName(String name) {
            this.name = name;
            return this;
        }

        public String getPhone() {
            return phone;
        }

        public PaymentDetailBuilder setPhone(String phone) {
            this.phone = phone;
            return this;
        }

        public String getProductInfo() {
            return productInfo;
        }

        public PaymentDetailBuilder setProductInfo(String productInfo) {
            this.productInfo = productInfo;
            return this;
        }

        public String getAmount() {
            return amount;
        }

        public PaymentDetailBuilder setAmount(String amount) {
            this.amount = amount;
            return this;
        }

        public String getPostByName() {
            return postByName;
        }

        public PaymentDetailBuilder setPostByName(String postByName) {
            this.postByName = postByName;
            return this;
        }

        public Long getPostById() {
            return postById;
        }

        public PaymentDetailBuilder setPostById(Long postById) {
            this.postById = postById;
            return this;
        }

        public PaymentDetailBuilder() {
        }

        public static PaymentDetailBuilder aPaymentDetail() {
            return new PaymentDetailBuilder();
        }

        private Long id;
        private String email;
        private String name;
        private String phone;
        private String productInfo;
        private String amount;
        private String postByName;
        private Long postById;

        public Date getPaymentDate() {
            return paymentDate;
        }

        public PaymentDetailBuilder setPaymentDate(Date paymentDate) {
            this.paymentDate = paymentDate;
            return this;
        }

        private Date paymentDate;

        public PaymentStatus getStatus() {
            return status;
        }

        public PaymentDetailBuilder setStatus(PaymentStatus status) {
            this.status = status;
            return this;
        }

        private PaymentStatus status;

        public PaymentDetail build() {
            PaymentDetail  paymentDetail = new PaymentDetail();
            paymentDetail.setId(id);
            paymentDetail.setEmail(email);
            paymentDetail.setName(name);
            paymentDetail.setAmount(amount);
            paymentDetail.setPhone(phone);
            paymentDetail.setStatus(status);
            paymentDetail.setProductInfo(productInfo);
            paymentDetail.setPostByName(postByName);
            paymentDetail.setPostById(postById);
            paymentDetail.setPaymentDate(paymentDate);
            return paymentDetail;
        }


    }

}
