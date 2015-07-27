package com.intech.controller;

import com.intech.jpa.User;
import com.intech.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;

/**
 * Created by popikyardo on 22.07.15.
 */
@Controller
public class BaseController {

    @Autowired
    UserService userService;

    @ModelAttribute("currentUser")
    public User getLoggedUser() {
        try {
            final org.springframework.security.core.userdetails.User principal = (org.springframework.security.core.userdetails.User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            return userService.findOne(principal.getUsername());
        } catch(Exception ex) {
            return null;
        }
    }
}
