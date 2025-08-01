package com.college.lostandfoundapi.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "items")
public class Item {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(length = 1000)
    private String description;

    private String category;

    private String locationFound;

    private String locationLost;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private ItemStatus status;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private ItemState itemState;

    private LocalDateTime reportedAt = LocalDateTime.now();

    private String imageUrl;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getLocationFound() {
        return locationFound;
    }

    public void setLocationFound(String locationFound) {
        this.locationFound = locationFound;
    }

    public String getLocationLost() {
        return locationLost;
    }

    public void setLocationLost(String locationLost) {
        this.locationLost = locationLost;
    }

    public ItemStatus getStatus() {
        return status;
    }

    public void setStatus(ItemStatus status) {
        this.status = status;
    }

    public ItemState getItemState() {
        return itemState;
    }

    public void setItemState(ItemState itemState) {
        this.itemState = itemState;
    }

    public LocalDateTime getReportedAt() {
        return reportedAt;
    }

    public void setReportedAt(LocalDateTime reportedAt) {
        this.reportedAt = reportedAt;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
}