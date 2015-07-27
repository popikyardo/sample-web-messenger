package com.intech.controller;

import com.intech.jpa.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
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
    public String register(Model model) {
        User userCreateForm = new User();
        model.addAttribute("user", userCreateForm);
        return "register";
    }

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public String create(@ModelAttribute @Valid User userCreateForm,
                         BindingResult bindingResult,
                         Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("user", userCreateForm);
            return "register";
        }
        if(userService.findOne(userCreateForm.getEmail())==null) {
            userService.save(userCreateForm);
         }

        return "redirect:/";
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
