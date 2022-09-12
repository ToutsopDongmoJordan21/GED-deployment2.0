package com.example.caref.repository;

import com.example.caref.models.Subscription;
import com.example.caref.models.User;
import com.example.caref.models.dto.SubscriptionDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SubscriptionRepository extends JpaRepository<Subscription, Long> {

    List<SubscriptionDto> findAllByUser(User user);

}
