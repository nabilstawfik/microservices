package com.microservice.dao;

import com.microservice.domain.User;
import java.util.List;

public interface UserDAO {

    /**
     *
     * @param user
     */
    void save(User user);

    /**
     *
     * @param user
     * @return
     */
    User update(User user);

    /**
     *
     * @param user
     */
    void delete(User user);

    /**
     *
     * @param id
     * @return
     */
    User findById(Long id);

    /**
     *
     * @param email
     * @return
     */
    User findByEmail(String email);

    /**
     *
     * @return
     */
    List<User> findAllOrderById();

}
