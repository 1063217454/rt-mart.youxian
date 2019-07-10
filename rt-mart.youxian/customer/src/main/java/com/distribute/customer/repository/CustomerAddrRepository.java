package com.distribute.customer.repository;

import com.distribute.customer.model.CustomerAddr;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface CustomerAddrRepository extends JpaRepository<CustomerAddr,Integer> {
    List<CustomerAddr> findByCustomerId(Integer customerId);

    @Modifying
    @Transactional
    @Query("update CustomerAddr ca set ca.isDefault = 1 where ca.customerId = ?1 and ca.customerAddrId = ?2")
    int updateCustomerAddrByCustomerIdAndAddressId(Integer customerid,Integer customerAddrId);
}
