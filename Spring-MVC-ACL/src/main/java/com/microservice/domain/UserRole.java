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
import javax.persistence.Table;

/**
 *
 * @author nabil
 */
@Entity
@Table(name = "user_role")
public class UserRole implements Persistable<Long> {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Long id;
    @Basic(optional = false)
    @Column(name = "code")
    private String code;
    @JoinColumn(name = "user", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private User user;

    public UserRole() {
    }

    public UserRole(Long id) {
        this.id = id;
    }

    public UserRole(String code) {
        this.code = code;
    }

    public UserRole(String code, User user) {
        this.code = code;
        this.user = user;
    }

    public UserRole(Long id, String code, String label) {
        this.id = id;
        this.code = code;
    }

    @Override
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof UserRole)) {
            return false;
        }
        UserRole other = (UserRole) object;
        return !((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id)));
    }

    @Override
    public boolean isNew() {
        return id == null || id == 0;
    }
}
