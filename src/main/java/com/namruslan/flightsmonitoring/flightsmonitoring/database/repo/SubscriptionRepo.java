package com.namruslan.flightsmonitoring.flightsmonitoring.database.repo;

import com.namruslan.flightsmonitoring.flightsmonitoring.database.entities.Subscription;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SubscriptionRepo extends JpaRepository<Subscription, Long> {
    List<Subscription> findByEmail(String email);
}
