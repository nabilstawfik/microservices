/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.microservice.service;

import com.microservice.domain.Item;
import java.util.List;

/**
 *
 * @author nabil
 */
public interface ItemService {

    /**
     *
     * @param item
     */
    void save(Item item);

    /**
     *
     * @return
     */
    List<Item> findAllOrderById();

    /**
     *
     * @param itemId
     * @return
     */
    Item findById(long itemId);

    /**
     *
     * @param item
     */
    void delete(Item item);

}
