package com.traffic.api.models.dto;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class PublicTransportAmountDTO {

    @Id
    private Integer id;
    private Integer amount;
    private String type;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
