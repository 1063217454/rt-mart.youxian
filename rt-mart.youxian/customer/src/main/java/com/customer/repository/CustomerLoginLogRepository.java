package com.customer.repository;

import com.customer.model.CustomerLoginLog;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CustomerLoginLogRepository extends JpaRepository<CustomerLoginLog,Integer> {
    List<CustomerLoginLog> findByCustomerId(Integer customerId);
}
