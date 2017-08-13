package com.microservice.service;

import com.microservice.domain.User;
import com.microservice.security.CustomUserDetails;
import java.util.List;

public interface UserService {

    /**
     *
     * @param email
     * @param password
     * @return
     */
    User register(String email, String password);

    /**
     *
     * @param userId
     * @param oldPassword
     * @param newPassword
     * @return
     */
    User updatePassword(Long userId, String oldPassword, String newPassword);

    // add more methods here

    /**
     *
     * @return
     */
    List<User> findAllOrderById();

    /**
     *
     * @param email
     * @return
     */
    User findByEmail(String email);

    /**
     *
     * @param id
     * @return
     */
    User findById(Long id);

    /**
     *
     * @param id
     */
    void delete(Long id);

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
     * @return
     */
    CustomUserDetails getCurrentAuthenticatedUser();
}
