package com.intech.controller;

import com.docuverse.identicon.IdenticonRenderer;
import com.docuverse.identicon.NineBlockIdenticonRenderer2;
import com.fasterxml.jackson.annotation.JsonView;
import com.intech.annotations.TableParam;
import com.intech.dto.DataTablesResponse;
import com.intech.dto.PagingCriteria;
import com.intech.jpa.User;
import com.intech.services.RolesService;
import com.intech.services.UserService;
import com.intech.utils.ImgUtils;
import com.intech.views.View;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.io.IOException;
import java.util.List;
import java.util.Locale;
/**
 * Created by popikyardo on 23.07.15.
 */
@Controller
@RequestMapping("/user")
public class UserController extends BaseController {

    private final static Logger LOGGER = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private UserService userService;
    @Autowired
    private RolesService rolesService;
    @Autowired
    private MessageSource messageSource;

    private IdenticonRenderer renderer = new NineBlockIdenticonRenderer2();

    @RequestMapping(value = "/create", method = RequestMethod.GET)
    public String createPage(Model model, Locale locale) {
        User userCreateForm = new User();
        model.addAttribute("user", userCreateForm);
        model.addAttribute("roles", rolesService.findAll());
        return "user/edit";
    }

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public String create(@ModelAttribute User userCreateForm,
                         BindingResult bindingResult,
                         Model model,
                         Locale locale,
                         RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("userForm", userCreateForm);
            model.addAttribute("roles", rolesService.findAll());
            return "user/edit";
        }

        userService.save(userCreateForm);
        redirectAttributes.addFlashAttribute("success", messageSource.getMessage("UI.Messages.User.CreatedSuccess", null, locale));
        return "redirect:/";
    }

    @RequestMapping("/edit")
    public String editPageById(@RequestParam(required = false) Long userId, Model model, Locale locale) {
        User user;
        if(userId==null){
            user = getLoggedUser();
        } else {
            user  = userService.findOne(userId);
        }

        if (user == null) {
            return "404";
        }
        user.setPassword("");
        model.addAttribute("roles", rolesService.findAll());
        model.addAttribute("user", user);

        return "user/edit";
    }

    @RequestMapping(value = "/edit", method = RequestMethod.POST)
    public String edit(@Valid User user, BindingResult bindingResult, Model model) {
        if(bindingResult.hasErrors()) {
            model.addAttribute("roles", rolesService.findAll());
            model.addAttribute("user", user);
            return "user/edit";
        } else {
            user = userService.save(user);
            return "redirect:/";
        }
    }

    @JsonView(View.UserDataTable.class)
    @RequestMapping(value = "/data", produces = "application/json")
    @ResponseBody
    public DataTablesResponse<User> getData(@TableParam PagingCriteria criteria) {
        DataTablesResponse resp = new DataTablesResponse<>(criteria.getEcho());
        Page<User> page = userService.search(criteria);
        resp.setITotalRecords(page.getTotalPages());
        resp.setAaData(page.getContent());
        return resp;
    }

    @RequestMapping("/userpic")
    public ResponseEntity<byte[]> testphoto(@RequestParam String email) throws IOException {
        final HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.IMAGE_PNG);
        byte[] imageInByte = ImgUtils.toBytes(renderer.render(email.hashCode(), 64));

        return new ResponseEntity<>(imageInByte, headers, HttpStatus.CREATED);
    }
}
