package com.traffic.api.controllers;

import com.traffic.api.models.User;
import com.traffic.api.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;


@Controller
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/user/", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public User getById(@RequestParam("id") Integer id) {
        return userService.getById(id);
    }

    @RequestMapping(value = "/user/all", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }

    @RequestMapping(value = "/user/create", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public String createUser(@RequestParam("username") String username, @RequestParam("password") String password, @RequestParam("role") String role, @RequestParam("email") String email, @RequestParam("phone") String phone, @RequestParam("telegram") boolean telegram) {
        return userService.createUser(username, password, role, email, phone, telegram);
    }

    @RequestMapping(value = "/user/delete", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public String deleteUser(@RequestParam("id") Integer id) {
        return userService.deleteUser(id);
    }

    @RequestMapping(value = "/user/edit", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public String editUser(@RequestParam("id") Integer id, @RequestParam("password") String password, @RequestParam("role") String role, @RequestParam("email") String email, @RequestParam("phone") String phone, @RequestParam("telegram") boolean telegram) {
        return userService.editUser(id, password, role, email, phone, telegram);
    }
}
