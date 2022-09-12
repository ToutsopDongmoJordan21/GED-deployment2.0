package com.example.caref.controllers;


import com.example.caref.execption.ResourceNotFoundException;
import com.example.caref.models.Subscription;
import com.example.caref.models.dto.SubscriptionDto;
import com.example.caref.repository.SubscriptionRepository;
import com.example.caref.repository.UserRepository;
import com.example.caref.service.SubscriptionDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/api/auth")
public class SubscriptionController {

    @Autowired
    SubscriptionRepository subscriptionRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    SubscriptionDetailsService subscriptionDetailsService;

    @PostMapping("/subscription/{userId}")
    public ResponseEntity<?> saveSubscription(@RequestBody SubscriptionDto subscription,
                                              @PathVariable Long userId) throws Exception {
        return ResponseEntity.ok(subscriptionDetailsService.save(subscription,userId));
    }

    @GetMapping("/subscription")
    public List<Subscription> getAllSubscription() {
        return subscriptionRepository.findAll();
    }

    @GetMapping("/subscription/{id}")
    public ResponseEntity<Subscription> getSubscriptionById(@PathVariable(value = "id") Long subscriptionId)
            throws ResourceNotFoundException {
        Subscription subscription = subscriptionRepository.findById(subscriptionId)
                .orElseThrow(() -> new ResourceNotFoundException("Subscrition not found for this id ::" +subscriptionId));
        return ResponseEntity.ok().body(subscription);
    }

    @DeleteMapping("/subscription/{id}")
    public Map<String, Boolean> deleteSubscription(@PathVariable(value = "id") Long subscriptionId)
            throws ResourceNotFoundException {
        Subscription subscription = subscriptionRepository.findById(subscriptionId)
                .orElseThrow(() -> new ResourceNotFoundException("Subscription not found for this id ::" +subscriptionId));

        subscriptionRepository.delete(subscription);
        Map<String, Boolean> response = new HashMap<>();
        response.put("Subscription was successful delete", Boolean.TRUE);
        return response;
    }

    @GetMapping("/subscription/userSubscribe/{id}")
    public ResponseEntity<?> getUserSubscribe(@PathVariable("id") Long userId) {
        return ResponseEntity.ok(subscriptionDetailsService.findAllSubscribeBy(userId));
    }

}

