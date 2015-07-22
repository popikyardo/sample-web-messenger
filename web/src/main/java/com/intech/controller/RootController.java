package com.intech.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.security.Principal;
import java.util.Locale;

/**
 * Created by popikyardo on 22.07.15.
 */
@Controller
public class RootController extends BaseController {
    
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Principal principal) {
        if(principal==null) {
            return "login";
        }
		return "user/dashboard";
	}

    @RequestMapping("/login")
    public String login() {
        return "login";
    }

    @RequestMapping("/register")
    public String register() {
        return "register";
    }

    @RequestMapping("/403")
    public String _403() {
        return "403";
    }
    
    @RequestMapping("/404")
    public String _404() {
        return "404";
    }

}
