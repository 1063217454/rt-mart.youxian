package com.customer.repository;

import com.customer.model.CustomerBalanceLog;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CustomerBalanceLogRepository extends JpaRepository<CustomerBalanceLog,Integer>, JpaSpecificationExecutor<CustomerBalanceLog> {
    List<CustomerBalanceLog> findByCustomerId(Integer customerId);

   @Query(value = "select cb from CustomerBalanceLog cb where cb.customerId=:customerId")
   Page<CustomerBalanceLog> findCustomerBalanceLogByCustomerIdPageable(@Param("customerId") Integer customerId, Pageable pageable);

}
