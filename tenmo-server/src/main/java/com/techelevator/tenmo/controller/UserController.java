package com.techelevator.tenmo.controller;
import com.techelevator.tenmo.dao.UserDao;
import com.techelevator.tenmo.model.User;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("users")
public class UserController {

    private UserDao userDao;

    public UserController(UserDao userDao) {
        this.userDao = userDao;
    }

    //find id by username
    @RequestMapping(path = "/id/{username}", method = RequestMethod.GET)
    public int findIdByUsername(@PathVariable String username) {
        return userDao.findIdByUsername(username);
    }

    //find all users
    @RequestMapping(method = RequestMethod.GET)
    public List<User> findAll() {
        return userDao.findAll();
    }

    //find by username
    @RequestMapping(path = "/{username}", method = RequestMethod.GET)
    public User findByUsername(@PathVariable String username) {
        return userDao.findByUsername(username);
    }

}
