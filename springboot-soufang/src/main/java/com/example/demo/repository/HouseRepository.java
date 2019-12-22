package com.example.demo.repository;


import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import com.example.demo.entity.House;

public interface HouseRepository extends PagingAndSortingRepository<House, Long>,JpaSpecificationExecutor<House>{

}
