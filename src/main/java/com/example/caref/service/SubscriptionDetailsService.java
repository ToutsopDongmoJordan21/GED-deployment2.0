package com.example.caref.service;

import com.example.caref.models.Subscription;
import com.example.caref.models.User;
import com.example.caref.models.dto.SubscriptionDto;
import com.example.caref.repository.SubscriptionRepository;
import com.example.caref.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

@Service
public class SubscriptionDetailsService {

    @Autowired
    private SubscriptionRepository subscriptionRepository;

    @Autowired
    private UserRepository userRepository;

    public Subscription save(SubscriptionDto subscription,
                             @PathVariable long userId) throws Exception {
        Subscription newSubscription = new Subscription();
        newSubscription.setPrice(subscription.getPrice());
        newSubscription.setSubscriptionType(subscription.getSubscriptionType());
        newSubscription.setDateDebut(new Date());
        newSubscription.setDateFin(subscription.getDateFin());

        User user = userRepository.findById(userId).orElseThrow(Exception::new);
        newSubscription.setUser(user);

        if(subscription.getSubscriptionType().equals("MONTH")) {

            newSubscription.setPrice("1000");
            newSubscription.setDateFin(new Date(new Date().getTime() + TimeUnit.DAYS.toMillis(365)));
            //Afficher la date pour voir si cela marche
            System.out.println("la date du mois finira en:" +newSubscription.getDateFin());

            return newSubscription = subscriptionRepository.save(newSubscription);

        } else if (subscription.getSubscriptionType().equals("WEEK")) {

            newSubscription.setPrice("100");
            newSubscription.setDateFin(new Date(new Date().getTime() + TimeUnit.DAYS.toMillis(7)));
            //Afficher la date pour voir si cela marche
            System.out.println("la date de la semaine finira en:" +newSubscription.getDateFin());

            return newSubscription = subscriptionRepository.save(newSubscription);

        } else if (subscription.getSubscriptionType().equals("YEAR")) {

            newSubscription.setPrice("10000");
            newSubscription.setDateFin(new Date(new Date().getTime() + TimeUnit.DAYS.toMillis(31)));
            //Afficher la date pour voir si cela marche
            System.out.println("la date de l'année finira en:" +newSubscription.getDateFin());

            return newSubscription = subscriptionRepository.save(newSubscription);

        }

        /*return null; */
        return newSubscription;

    }

    public List<SubscriptionDto> findAllSubscribeBy(Long userId) {
        return subscriptionRepository.findAllByUser(userRepository.getOne(userId));
    }


}

