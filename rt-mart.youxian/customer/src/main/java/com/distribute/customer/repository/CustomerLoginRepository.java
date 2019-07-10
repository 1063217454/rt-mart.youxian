package com.distribute.customer.repository;

import com.distribute.customer.model.CustomerLogin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface CustomerLoginRepository extends JpaRepository<CustomerLogin,Integer> {
    List<CustomerLogin> findByLoginName(String loginName);
    List<CustomerLogin> findByCustomerId(Integer customerId);
    List<CustomerLogin> findByLoginNameAndPassword(String loginName,String password);
    List<CustomerLogin> findByCustomerIdAndPassword(Integer customerId,String password);

    @Modifying
    @Transactional
    @Query("update CustomerLogin cl set cl.password = ?1 where cl.customerId = ?2")
    int updatePasswordByCustomerId(String password,Integer customerid);
}
