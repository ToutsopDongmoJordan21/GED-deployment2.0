package com.example.caref.controllers;

import com.example.caref.execption.ResourceNotFoundException;
import com.example.caref.models.Payment;
import com.example.caref.models.PaymentDetail;
import com.example.caref.models.dto.PaymentCallback;
import com.example.caref.models.enums.PaymentMode;
import com.example.caref.repository.PaymentRepo;
import com.example.caref.service.PaymentServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/payment")
public class PaymentController {

    @Autowired
    private PaymentServiceImpl paymentService;

    @Autowired
    private PaymentRepo paymentRepo;

    @PostMapping(path = "/payment-details")
    public @ResponseBody PaymentDetail proceedPayment(@RequestBody PaymentDetail paymentDetail){
        return paymentService.proceedPayment(paymentDetail);
    }

    @RequestMapping(path = "/payment-response", method = RequestMethod.POST)
    public @ResponseBody String payuCallback(@RequestParam String mihpayid, @RequestParam String status, @RequestParam PaymentMode mode, @RequestParam String txnid, @RequestParam String hash){
        PaymentCallback paymentCallback = new PaymentCallback();
        paymentCallback.setMihpayid(mihpayid);
        paymentCallback.setTxnid(txnid);
        paymentCallback.setMode(mode);
        paymentCallback.setHash(hash);
        paymentCallback.setStatus(status);
        return paymentService.payuCallback(paymentCallback);
    }

    @GetMapping(path = "/payment")
    public ResponseEntity<?> getAllPayment() {
        return ResponseEntity.ok(paymentService.findAllPayment());
    }

    @GetMapping(path = "/payment/{id}")
    public ResponseEntity<?> getPaymentById(@PathVariable(value = "id") Long paymentId) {
        return ResponseEntity.ok(paymentService.findOnePaymentById(paymentId));
    }

    @GetMapping(path = "/payment/payment/{id}")
    public ResponseEntity<?> getPaymentUser(@PathVariable("id") Long userId) {
        return ResponseEntity.ok(paymentService.findAllPaymentBy(userId));
    }

    @DeleteMapping(path = "/payment/{id}")
    public Map<String, Boolean> deletePayment(@PathVariable(value = "id") Long paymendId)
            throws ResourceNotFoundException {
        Payment payment = paymentRepo.findById(paymendId)
                .orElseThrow(() -> new ResourceNotFoundException("payment not found for this id ::" +paymendId));
        paymentRepo.delete(payment);
        Map<String, Boolean> response = new HashMap<>();
        response.put("payment was successful delete", Boolean.TRUE);
        return response;
    }
}

