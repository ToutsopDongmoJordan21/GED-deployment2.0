package com.example.caref.service;

import com.example.caref.models.Payment;
import com.example.caref.models.PaymentDetail;
import com.example.caref.models.User;
import com.example.caref.models.dto.PaymentCallback;
import com.example.caref.models.enums.PaymentStatus;
import com.example.caref.repository.PaymentRepo;
import com.example.caref.repository.UserRepository;
import com.example.caref.security.util.PaymentUtil;
import com.example.caref.security.util.SecurityUtils;
import com.example.caref.service.interfaces.PaymentServiceInter;
import com.example.caref.service.interfaces.PaymentServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
public class PaymentServiceImpl implements PaymentServiceInterface, PaymentServiceInter {

    @Autowired
    private PaymentRepo paymentRepository;

    @Autowired
    private UserRepository userRepository;

    @Override
    public PaymentDetail proceedPayment(PaymentDetail paymentDetail) {
        PaymentUtil paymentUtil = new PaymentUtil();
        paymentDetail = paymentUtil.populatePaymentDetail(paymentDetail);
        savePaymentDetail(paymentDetail);

        return paymentDetail;
    }

    @Override
    public String payuCallback(PaymentCallback paymentResponse) {
        String msg = "Transaction failed.";
        Payment payment = paymentRepository.findByTxnId(paymentResponse.getTxnid());
        if(payment != null) {
            //TODO validate the hash
            PaymentStatus paymentStatus = null;
            if(paymentResponse.getStatus().equals("failure")){
                paymentStatus = PaymentStatus.Failed;
            }else if(paymentResponse.getStatus().equals("success")) {
                paymentStatus = PaymentStatus.Success;
                msg = "Transaction success";
            }
            payment.setPaymentStatus(paymentStatus);
            payment.setMihpayId(paymentResponse.getMihpayid());
            payment.setMode(paymentResponse.getMode());
            paymentRepository.save(payment);
        }
        return msg;
    }

    private void savePaymentDetail(PaymentDetail paymentDetail) {
        User user = userRepository.getOne(SecurityUtils.getCurrentUserId());
        Payment payment = new Payment();
        payment.setAmount(paymentDetail.getAmount());
        payment.setEmail(paymentDetail.getEmail());
        payment.setName(paymentDetail.getName());
        payment.setPaymentDate(new Date());
        payment.setPaymentStatus(PaymentStatus.Pending);
        payment.setPhone(paymentDetail.getPhone());
        payment.setProductInfo(paymentDetail.getProductInfo());
        payment.setTxnId(paymentDetail.getTxnId());
        payment.setUser(user);
        paymentRepository.save(payment);
    }

    public List<PaymentDetail> findAllPayment() {
        return paymentRepository.findAll().stream().map(buildListPaymentDetail()).collect(Collectors.toList());
    }

    private Function<Payment, PaymentDetail> buildListPaymentDetail() {
        return payment -> findOnePaymentById(payment.getId());
    }

    public PaymentDetail findOnePaymentById(Long paymentId) {
        Payment payment = paymentRepository.getOne(paymentId);
        Optional<User> optionalUser = userRepository.findByUsername(payment.getCreatedBy());

        return PaymentDetail.PaymentDetailBuilder.aPaymentDetail()
                .setId(paymentId)
                .setEmail(payment.getEmail())
                .setName(payment.getName())
                .setPhone(payment.getPhone())
                .setProductInfo(payment.getProductInfo())
                .setAmount(payment.getAmount())
                .setPaymentDate(payment.getPaymentDate())
                .setStatus(payment.getPaymentStatus())
                .setPostByName(optionalUser.map(User::getUsername).orElse(null))
                .setPostById(optionalUser.map(User::getId).orElse(null))
                .build();


    }

    public List<Payment> findAllPaymentBy(Long userId) {
        return paymentRepository.findAllByUser(userRepository.getOne(userId));
    }
}

