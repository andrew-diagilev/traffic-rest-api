/*package com.traffic.api.controllers;

import com.traffic.api.models.users.User;
import com.traffic.api.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@Controller
public class UserController {

    @Autowired
    private UserService userService;

    @CrossOrigin
    @RequestMapping(value = "/api/user/", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public User getById(@RequestParam("id") Integer id) {
        return userService.getById(id);
    }

    @CrossOrigin
    @RequestMapping(value = "/api/user/all", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }

    @CrossOrigin
    @RequestMapping(value = "/api/user/create", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public String createUser (@RequestBody User user) {
        return userService.createUser(user.getUsername(), user.getName(), user.getSurname(), user.getPassword(), user.getRoles(), user.getEmail(), user.getPhone(), user.getTelegram());
    }

    @CrossOrigin
    @RequestMapping(value = "/api/user/delete", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public String deleteUser(@RequestBody Integer id) {
        return userService.deleteUser(id);
    }

    @CrossOrigin
    @RequestMapping(value = "/api/user/edit", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public String editUser(@RequestBody User user) {
        return userService.editUser(user.getId(), user.getPassword(), user.getRoles(), user.getEmail(), user.getPhone(), user.getTelegram());
    }

    @CrossOrigin
    @RequestMapping(value = "/api/user/auth", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public String authUser(@RequestBody User user) {
        System.out.println(user.getUsername());
        System.out.println(user.getPassword());
        return user.getUsername();
    }

}
*/