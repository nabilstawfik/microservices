/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.microservice.domain;

import com.microservice.util.domain.Persistable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 *
 * @author nabil
 */
@Entity
@Table(name = "item")
@NamedQueries({
    @NamedQuery(name = "Item.findById", query = "SELECT item FROM Item item where item.id=:id"),
    @NamedQuery(name = "Item.findByName", query = "SELECT item FROM Item item where item.name=:name"),
    @NamedQuery(name = "Item.findAllOrderById", query = "SELECT item FROM Item item order by item.id asc")})
public class Item implements Persistable<Long> {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Long id;
    @Column(name = "name")
    private String name;

    @Column(name = "price")
    private double price;

    public Item() {
    }

    public Item(String name, double price) {
        this.name = name;
        this.price = price;
    }

    @Override
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    @Override
    public boolean isNew() {
        return id == null || id == 0;
    }
}
