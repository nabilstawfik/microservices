/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.microservice.util.domain;

import java.io.Serializable;

/**
 *
 * @author nabil
 */
public interface Persistable<PK extends Serializable> extends Serializable {

    /**
     * Returns the id of the entity.
     *
     * @return the id
     */
    PK getId();

    /**
     * Returns if the {@code Persistable} is new or was persisted already.
     *
     * @return if the object is new
     */
    boolean isNew();
}