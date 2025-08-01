package com.college.lostandfoundapi.model;

/**
 * Represents the status of an item, indicating whether it has been
 * reported as lost or found. This enum restricts the 'status' field
 * to only these two possible values.
 */
public enum ItemStatus {

    /**
     * The item has been reported as lost by a user.
     */
    LOST,

    /**
     * The item has been reported as found by a user.
     */
    FOUND
}
