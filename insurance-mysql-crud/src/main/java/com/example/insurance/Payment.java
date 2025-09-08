package com.example.insurance;

import java.time.LocalDateTime;

public class Payment {
    private long id;
    private long policyId;
    private double amount;
    private String mode;
    private String status;
    private String reference;
    private LocalDateTime createdAt;
    private String policyHolder;

    public long getId() { return id; }
    public void setId(long id) { this.id = id; }

    public long getPolicyId() { return policyId; }
    public void setPolicyId(long policyId) { this.policyId = policyId; }

    public double getAmount() { return amount; }
    public void setAmount(double amount) { this.amount = amount; }

    public String getMode() { return mode; }
    public void setMode(String mode) { this.mode = mode; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    public String getReference() { return reference; }
    public void setReference(String reference) { this.reference = reference; }

    public LocalDateTime getCreatedAt() { return createdAt; }
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }

    public String getPolicyHolder() { return policyHolder; }
    public void setPolicyHolder(String policyHolder) { this.policyHolder = policyHolder; }
}
