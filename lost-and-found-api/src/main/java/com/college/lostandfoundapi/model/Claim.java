package com.college.lostandfoundapi.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties; // <-- Add this import
import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "claims")
public class Claim {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Foreign key to the Item being claimed
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "item_id", nullable = false)
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"}) // <-- Add this annotation
    private Item item;

    // Placeholder for the user who made the claim
    private String claimerUsername;

    @Column(length = 1000, nullable = false)
    private String proof;

    private LocalDateTime claimedAt = LocalDateTime.now();

    // Getters and Setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
    }

    public String getClaimerUsername() {
        return claimerUsername;
    }

    public void setClaimerUsername(String claimerUsername) {
        this.claimerUsername = claimerUsername;
    }

    public String getProof() {
        return proof;
    }

    public void setProof(String proof) {
        this.proof = proof;
    }

    public LocalDateTime getClaimedAt() {
        return claimedAt;
    }

    public void setClaimedAt(LocalDateTime claimedAt) {
        this.claimedAt = claimedAt;
    }
}