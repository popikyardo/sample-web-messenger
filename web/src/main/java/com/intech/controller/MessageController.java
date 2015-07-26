package com.intech.controller;

import com.fasterxml.jackson.annotation.JsonView;
import com.intech.annotations.TableParam;
import com.intech.dto.DataTablesResponse;
import com.intech.dto.PagingCriteria;
import com.intech.jpa.Message;
import com.intech.jpa.User;
import com.intech.services.MessageService;
import com.intech.views.View;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;

/**
 * Created by popikyardo on 22.07.15.
 */
@Controller
@RequestMapping("/messages")
public class MessageController extends BaseController{

    @Autowired
    private MessageService messageService;

    @JsonView(View.MessageDataTable.class)
    @RequestMapping(value = "/data", method = RequestMethod.GET)
    @ResponseBody
    public DataTablesResponse<Message> getData(@TableParam PagingCriteria criteria, HttpServletRequest request) {
        User userTo = null;
        if(!request.isUserInRole("ROLE_ADMIN")) {
           userTo = getLoggedUser();
        }

        DataTablesResponse resp = new DataTablesResponse<>(criteria.getEcho());
        Page<Message> page = messageService.search(criteria, userTo);
        resp.setITotalRecords(page.getTotalPages());
        resp.setAaData(page.getContent());
        return resp;
    }


    @RequestMapping(value = "/send", method = RequestMethod.POST, produces = "application/json")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @ResponseBody
    public void sendMessage(@RequestParam Long contactId,
                            @RequestParam String subject,
                            @RequestParam String message) {
        User toUser = new User();
        toUser.setId(contactId);

        Message msg = new Message();
        msg.setSubject(subject);
        msg.setMessage(message);
        msg.setToUser(toUser);
        msg.setFromUser(getLoggedUser());
        msg.setDate(new Date());
        msg.setDeleted(false);

        messageService.save(msg);
    }

}
