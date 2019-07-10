package com.distribute.customer.repository;

import com.distribute.customer.model.CustomerLevelInf;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CustomerLevelInfRepository extends JpaRepository<CustomerLevelInf,Integer> {
    List<CustomerLevelInf> findByCustomerLevel(Integer customerLevel);
}
