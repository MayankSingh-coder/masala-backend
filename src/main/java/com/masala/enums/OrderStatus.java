package com.masala.enums;

public enum OrderStatus {

    ORDERDED("Accepted"),
    DECLINED("Declined"),
    PENDING("Pending"),
    COMPLETED("Completed"),
    FAILED("Failed");

    private String status;
    OrderStatus(String status) {
        this.status = status;
    }
}
