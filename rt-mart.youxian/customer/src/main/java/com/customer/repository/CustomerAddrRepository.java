package com.customer.repository;

import com.customer.model.CustomerAddr;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CustomerAddrRepository extends JpaRepository<CustomerAddr,Integer> {
    List<CustomerAddr> findByCustomerId(Integer customerId);
}
