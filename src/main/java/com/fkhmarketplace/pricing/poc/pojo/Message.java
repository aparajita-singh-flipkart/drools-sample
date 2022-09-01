package com.fkhmarketplace.pricing.poc.pojo;

public class Message {
    String status;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Message{" +
                "status='" + status + '\'' +
                '}';
    }
}
