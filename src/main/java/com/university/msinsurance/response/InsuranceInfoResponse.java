package com.university.msinsurance.response;

import java.time.LocalDateTime;

public class InsuranceInfoResponse {

    private String department;

    private String type;

    private Integer price;

    private LocalDateTime startDate;

    private LocalDateTime endDate;

    private String contractNumber;

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public LocalDateTime getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDateTime startDate) {
        this.startDate = startDate;
    }

    public LocalDateTime getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDateTime endDate) {
        this.endDate = endDate;
    }

    public String getContractNumber() {
        return contractNumber;
    }

    public void setContractNumber(String contractNumber) {
        this.contractNumber = contractNumber;
    }

    @Override
    public String toString() {
        return "Insurance{" +
                ", department=" + department +
                ", type=" + type +
                ", price=" + price +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                ", contractNumber='" + contractNumber + '\'' +
                '}';
    }

}
