package com.example.freightmanagement.model;

import java.util.Date;

public class ContractParam {

    private String contractUrl;

    private String responsibilityUrl;

    private String commitmentUrl;

    private String endTime;

    private Integer carId;

    public String getContractUrl() {
        return contractUrl;
    }

    public void setContractUrl(String contractUrl) {
        this.contractUrl = contractUrl;
    }

    public String getResponsibilityUrl() {
        return responsibilityUrl;
    }

    public void setResponsibilityUrl(String responsibilityUrl) {
        this.responsibilityUrl = responsibilityUrl;
    }

    public String getCommitmentUrl() {
        return commitmentUrl;
    }

    public void setCommitmentUrl(String commitmentUrl) {
        this.commitmentUrl = commitmentUrl;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public Integer getCarId() {
        return carId;
    }

    public void setCarId(Integer carId) {
        this.carId = carId;
    }
}
