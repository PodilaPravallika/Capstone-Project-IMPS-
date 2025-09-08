package com.example.insurance;
import java.time.LocalDateTime;
public class Claim {
    private long id; private long policyId; private String description; private double amount; private String status; private LocalDateTime createdAt; private String policyHolder;
    public long getId(){return id;} public void setId(long id){this.id=id;}
    public long getPolicyId(){return policyId;} public void setPolicyId(long p){this.policyId=p;}
    public String getDescription(){return description;} public void setDescription(String d){this.description=d;}
    public double getAmount(){return amount;} public void setAmount(double a){this.amount=a;}
    public String getStatus(){return status;} public void setStatus(String s){this.status=s;}
    public LocalDateTime getCreatedAt(){return createdAt;} public void setCreatedAt(LocalDateTime t){this.createdAt=t;}
    public String getPolicyHolder(){return policyHolder;} public void setPolicyHolder(String ph){this.policyHolder=ph;}
}
