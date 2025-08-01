package com.college.lostandfoundapi.model;

/**
 * Represents the current state of an item in the lost and found system.
 * This indicates its progression through the claim resolution process.
 */
public enum ItemState {

    /**
     * The item has been reported and is awaiting a claim.
     */
    UNCLAIMED,

    /**
     * A claim has been submitted for the item and is awaiting admin review.
     */
    CLAIM_PENDING,

    /**
     * The claim has been approved, and the item is considered successfully returned.
     */
    CLAIMED,

    /**
     * The item's entire lost and found process is complete (e.g., returned to owner,
     * disposed of, or no longer actively managed). Items in this state
     * are typically removed from public listings.
     */
    RESOLVED
}
