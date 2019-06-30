package com.customer.repository;

import com.customer.model.CustomerPointLog;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CustomerPointLogRepository extends JpaRepository<CustomerPointLog,Integer> {
    List<CustomerPointLog> findByCustomerId(Integer customerId);
}
