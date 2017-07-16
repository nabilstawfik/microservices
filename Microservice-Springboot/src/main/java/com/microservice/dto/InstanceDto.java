/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.microservice.dto;

import java.util.Date;

/**
 *
 * @author nabil
 */
public class InstanceDto {
    
    private String uuid;
    private String correlationId;
    private Date creationTime;

    public InstanceDto() {
    }

    public InstanceDto(String uuid, String correlationId, Date creationTime) {
        this.uuid = uuid;
        this.correlationId = correlationId;
        this.creationTime = creationTime;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public Date getCreationTime() {
        return creationTime;
    }

    public void setCreationTime(Date creationTime) {
        this.creationTime = creationTime;
    }

    
    public String getCorrelationId() {
        return correlationId;
    }

    public void setCorrelationId(String correlationId) {
        this.correlationId = correlationId;
    }
    
}
