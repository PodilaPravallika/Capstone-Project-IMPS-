package com.example.insurance;
import java.time.LocalDate;
public class Policy {
    private long id; private String type; private String holderName; private int age;
    private double coverageAmount; private double premium; private LocalDate startDate; private LocalDate endDate; private String status;
    public long getId(){return id;} public void setId(long id){this.id=id;}
    public String getType(){return type;} public void setType(String t){this.type=t;}
    public String getHolderName(){return holderName;} public void setHolderName(String n){this.holderName=n;}
    public int getAge(){return age;} public void setAge(int a){this.age=a;}
    public double getCoverageAmount(){return coverageAmount;} public void setCoverageAmount(double c){this.coverageAmount=c;}
    public double getPremium(){return premium;} public void setPremium(double p){this.premium=p;}
    public java.time.LocalDate getStartDate(){return startDate;} public void setStartDate(java.time.LocalDate d){this.startDate=d;}
    public java.time.LocalDate getEndDate(){return endDate;} public void setEndDate(java.time.LocalDate d){this.endDate=d;}
    public String getStatus(){return status;} public void setStatus(String s){this.status=s;}
}
