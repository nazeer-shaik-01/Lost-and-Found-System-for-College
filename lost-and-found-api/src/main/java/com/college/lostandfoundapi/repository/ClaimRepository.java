package com.college.lostandfoundapi.repository;

import com.college.lostandfoundapi.model.Claim;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List; // <-- Add this import

@Repository
public interface ClaimRepository extends JpaRepository<Claim, Long> {
    List<Claim> findByItemId(Long itemId); // <-- Add this line
}