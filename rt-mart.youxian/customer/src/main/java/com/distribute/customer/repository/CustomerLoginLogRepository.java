package com.distribute.customer.repository;

import com.distribute.customer.model.CustomerLoginLog;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CustomerLoginLogRepository extends JpaRepository<CustomerLoginLog,Integer> {
    List<CustomerLoginLog> findByCustomerId(Integer customerId);

}
