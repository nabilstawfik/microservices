package com.microservice.rest;

import com.microservice.domain.User;
import com.microservice.dto.UserDto;
import com.microservice.service.UserService;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import static java.util.stream.Collectors.toList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/rest/user-service")
public class UserController {

    private static final Logger LOGGER = Logger.getLogger(UserController.class.getName());

    @Autowired
    private UserService userService;

    /**
     * List all users ordered by id asc.
     * <p>
     * <b>Service URL:</b> /rest/user-service/list-all</p>
     * <p>
     * <b>Sample call:</b><br/>
     * $.ajax({ type: "GET", url: "/rest/user-service/list-all", success:
     * function (data){}, error: function (XMLHttpRequest, textStatus,
     * errorThrown) {} });</p>
     * <p>
     * <b>Success Response:</b> Code = 200</p>
     * <p>
     * <b>Error Response:</b> Code = 500</p>
     *
     * <p>
     * <b>Sample Response:</b> { id : [long], email: [string]}</p>
     *
     * @return Json object contains id and email.
     */
    @GetMapping(value = "/list-all", produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    public ResponseEntity<List<UserDto>> listUsers() {
        try {
            List<UserDto> userDtos = userService.findAllOrderById().stream().map(user -> new UserDto(user.getId(), user.getEmail(), null)).collect(toList());
            return new ResponseEntity(userDtos, HttpStatus.OK);
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, null, e);
        }
        return new ResponseEntity(null, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    /**
     * Add new user using email and password.
     * <p>
     * <b>Service URL:</b> /rest/user-service/add-user</p>
     * <p>
     * <b>Sample call:</b></p>
     * <p>
     * var userDto = {email: [string], password: [string]};<br/>
     * $.ajax({ type: "POST", url: "/rest/user-service/add-user", contentType:
     * "application/json", data: JSON.stringify(userDto), success: function
     * (data){}, error: function (XMLHttpRequest, textStatus, errorThrown) {}
     * });</p>
     * <p>
     * <b>Success Response:</b> Code = 200</p>
     * <p>
     * <b>Error Response:</b> Code = 500</p>
     * <p>
     * <b>Sample Response:</b> { id : [long], email: [string]}</p>
     *
     * @param userDto Json object contains id, email and password.
     * @return Json object contains id and email.      *
     */
    @PostMapping(value = "/add-user", produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    public ResponseEntity<UserDto> addUser(@RequestBody UserDto userDto) {
        try {
            User user = userService.register(userDto.getEmail(), userDto.getPassword());
            return new ResponseEntity(new UserDto(user.getId(), user.getEmail(), null), HttpStatus.OK);
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, null, e);
        }
        return new ResponseEntity(null, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    /**
     * Load user by given user id.
     * <p>
     * <b>Service URL:</b> /rest/user-service/show-user/{userId}</p>
     * <p>
     * <b>Sample call:</b></p>
     * <p>
     * $.ajax({ type: "GET", url: "/rest/user-service/show-user/{userId}", success:
     * function (data){}, error: function (XMLHttpRequest, textStatus,
     * errorThrown) {} });</p>
     * <p>
     * <b>Success Response:</b> Code = 200</p>
     * <p>
     * <b>Error Response:</b> Code = 500</p>
     * <p>
     * <b>Sample Response:</b> { id : [long], email: [string]}</p>
     *
     * @param userId user id of the required user to show.
     * @return Json object contains id and email.      *
     */
    @GetMapping(value = "/show-user/{userId}", produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    public ResponseEntity<UserDto> showUser(@PathVariable("userId") Long userId) {
        try {
            User user = userService.findById(userId);
            return new ResponseEntity(new UserDto(userId, user.getEmail(), null), HttpStatus.OK);
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, null, e);
        }
        return new ResponseEntity(null, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    /**
     * Update already existing user.
     * <p>
     * <b>Service URL:</b> /rest/user-service/update-user{userId}</p>
     * <p>
     * <b>Sample call:</b></p>
     * <p>
     * var userDto = {email: [string], password: [string], oldPassword: [string]};<br/>
     * $.ajax({ 
     * type: "PUT", 
     * url: "/rest/user-service/update-user/{userId}",
     * contentType: "application/json", 
     * data: JSON.stringify(userDto), 
     * success:function (data){}, 
     * error: function (XMLHttpRequest, textStatus,errorThrown) {} });</p>
     * <p>
     * <b>Success Response:</b> Code = 200</p>
     * <p><b>Error Response:</b> Code = 500</p>
     * <p><b>Sample Response:</b> { id : [long], email: [string]}</p>
     *
     * @param userId id of user to update.
     * @param userDto Json object contains email, password and oldPassword.
     * 
     * @return Json object contains id and email.      *
     */
    @PutMapping("/update-user/{userId}")
    @ResponseBody
    public ResponseEntity updateUser(@PathVariable("userId") Long userId, @RequestBody UserDto userDto) {
        try {
            User user;
            if (!StringUtils.isEmpty(userDto.getPassword()) && !StringUtils.isEmpty(userDto.getOldPassword())) {
                user = userService.updatePassword(userId, userDto.getOldPassword(), userDto.getPassword());
            } else {
                user = userService.findById(userId);
            }
            user.setEmail(userDto.getEmail());
            user = userService.update(user);
            return new ResponseEntity(new UserDto(user.getId(), user.getEmail(), null), HttpStatus.OK);
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, null, e);
        }
        return new ResponseEntity(null, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    /**
     * Delete user by given user id.
     * <p>
     * <b>Service URL:</b> /rest/user-service/delete-user/{userId}</p>
     * <p>
     * <b>Sample call:</b></p>
     * <p>
     * $.ajax({ type: "DELETE", url: "/rest/user-service/delete-user/{userId}", success:
     * function (data){}, error: function (XMLHttpRequest, textStatus,
     * errorThrown) {} });</p>
     * <p><b>Success Response:</b> Code = 200</p>
     * <p><b>Error Response:</b> Code = 500</p>
     * 
     * @param userId user id of the required user to delete.
     * @return 
     */
    @DeleteMapping("/delete-user/{userId}")
    @ResponseBody
    public ResponseEntity deleteUser(@PathVariable("userId") Long userId) {
        try {
            userService.delete(userId);
            return new ResponseEntity(null, HttpStatus.OK);
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, null, e);
        }
        return new ResponseEntity(null, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
