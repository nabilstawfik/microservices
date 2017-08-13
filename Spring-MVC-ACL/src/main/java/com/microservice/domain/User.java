package com.microservice.domain;

import com.microservice.util.domain.Persistable;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import org.hibernate.validator.constraints.Email;

/**
 *
 * @author nabil
 */
@Entity
@Table(name = "user")
@NamedQueries({
    @NamedQuery(name = "User.findById", query = "SELECT u FROM User u where u.id=:id"),
    @NamedQuery(name = "User.findByEmail", query = "SELECT u FROM User u where u.email=:email"),
    @NamedQuery(name = "User.findAllOrderById", query = "SELECT u FROM User u order by u.id asc")})
public class User implements Persistable<Long> {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue
    private Long id;
    
    @Email
    private String email;

    private String password;
    
    private boolean enabled;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "user", fetch = FetchType.EAGER)
    private List<UserRole> accountRoleList;

    public User() {
    }

    public User(final String email) {
        this.email = email;
    }

    @Override
    public Long getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPassword() {
        return password;
    }

    public List<UserRole> getAccountRoleList() {
        return accountRoleList;
    }

    public void setAccountRoleList(List<UserRole> accountRoleList) {
        this.accountRoleList = accountRoleList;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    @Override
    public boolean isNew() {
        return id == null || id == 0;
    }

}
