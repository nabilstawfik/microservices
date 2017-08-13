/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.microservice.dao;

import com.microservice.domain.Item;
import java.util.List;

/**
 *
 * @author nabil
 */
public interface ItemDAO {

    /**
     *
     * @param item
     */
    void save(Item item);

    /**
     *
     * @param item
     * @return
     */
    Item update(Item item);

    /**
     *
     * @param item
     */
    void delete(Item item);

    /**
     *
     * @param id
     * @return
     */
    Item findById(Long id);

    /**
     *
     * @return
     */
    List<Item> findAllOrderById();
}
