package com.college.lostandfoundapi.repository;

import com.college.lostandfoundapi.model.Item;
import com.college.lostandfoundapi.model.ItemStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ItemRepository extends JpaRepository<Item, Long> {

    List<Item> findByStatus(ItemStatus status);
}