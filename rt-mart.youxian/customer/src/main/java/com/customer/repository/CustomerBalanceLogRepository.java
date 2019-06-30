package com.customer.repository;

import com.customer.model.CustomerBalanceLog;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CustomerBalanceLogRepository extends JpaRepository<CustomerBalanceLog,Integer> {
    List<CustomerBalanceLog> findByCustomerId(Integer customerId);
}
