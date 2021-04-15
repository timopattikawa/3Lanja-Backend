package com.Lanja.finnancial.controllers;

import com.Lanja.finnancial.entity.ApplicationUserDetails;
import com.Lanja.finnancial.services.ApplicationUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "api/v1/users")
public class UserController {

    public final ApplicationUserDetailsService userDetailsService;

    @Autowired
    public UserController(ApplicationUserDetailsService userDetailsService) {
        this.userDetailsService = userDetailsService;
    }

    @PostMapping
    public void registerUser(@RequestBody ApplicationUserDetails applicationUserDetails) {
        userDetailsService.registrationUser(applicationUserDetails);
    }

    @PutMapping(path = "/update/{username}")
    public void updateUser(@PathVariable("username") String usernameAsKey,
                           @RequestParam(required = false) String userName,
                           @RequestParam(required = false) String password,
                           @RequestParam(required = false) String role,
                           @RequestParam(required = false) boolean locked,
                           @RequestParam(required = false) boolean enable) {
        userDetailsService.updateUser(usernameAsKey, userName, password, role, locked, enable);
    }

    @DeleteMapping(path = "/delete/{username}")
    public void deleteUser(@PathVariable("username") String userName) {
        userDetailsService.deleteUserByUsername(userName);
    }

}
