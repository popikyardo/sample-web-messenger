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

/**
 * Created by popikyardo on 22.07.15.
 */
@Controller
public class MessageController extends BaseController{

    @Autowired
    private MessageService messageService;

    @JsonView(View.MessageDataTable.class)
    @RequestMapping(value = "/messages/data", method = RequestMethod.GET)
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


    @RequestMapping(value = "/message.json", method = RequestMethod.PUT, produces = "application/json")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @ResponseBody
    public void updateMessage(@RequestBody Message message) {
        Message updateMessage = messageService.findOne(message.getId());
        updateMessage.setStatus(message.getStatus());
        messageService.saveAndFlush(updateMessage);
    }

}
