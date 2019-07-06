package com.product.repository;

import com.product.model.BrowseHistory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BrowseHistoryRepository extends JpaRepository<BrowseHistory,Integer> {

    List<BrowseHistory> findByCustomer_id(Integer customerId);

}
