package com.distribute.customer.repository;

import com.distribute.customer.model.CustomerInf;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface CustomerInfRepository extends JpaRepository<CustomerInf,Integer> {
    List<CustomerInf> findByCustomerId(Integer customerId);

    @Modifying
    @Transactional
    @Query("update CustomerInf ci set ci.customerName = ?1 where ci.customerId = ?2")
    int updateCustomerNameByCustomerId(String customerName,Integer customerid);

    @Modifying
    @Transactional
    @Query("update CustomerInf ci set ci.headPicUrl = ?1 where ci.customerId = ?2")
    int updateHeadPicUrlByCustomerId(String headPicUrl,Integer customerid);

    @Modifying
    @Transactional
    @Query("update CustomerInf ci set ci.customerName = ?1,ci.mobilePhone = ?2 where ci.customerId = ?3")
    int updateCustomerNameAndMobilePhoneByCustomerId(String customerName,String mobilePhone,Integer customerid);

}
