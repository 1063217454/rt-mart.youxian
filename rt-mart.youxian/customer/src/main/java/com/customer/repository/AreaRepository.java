package com.customer.repository;

import com.customer.model.Area;
import com.customer.model.CustomerAddr;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface AreaRepository extends JpaRepository<Area,Integer> {
    @Query("select a from Area a where a.level = :level and a.name like %:name%")
    List<Area> findByLevelAndNameLike(Integer level,@Param("name") String name);

    List<Area> findByCode(String code);

}
