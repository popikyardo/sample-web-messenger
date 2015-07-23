package com.intech.controller;

import com.intech.jpa.Message;
import com.intech.services.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
/**
 * Created by popikyardo on 22.07.15.
 */
@Controller
public class MessageController extends BaseController{

    @Autowired
    private MessageService messageService;

    @RequestMapping(value = "/messages", method = RequestMethod.GET)
    public String messages() {
        return "messages/list";
    }

    @RequestMapping(value = "/messages.json", method = RequestMethod.GET, produces = "application/json")
    @ResponseBody
    public Page getMessages(@RequestParam(value = "page") int page,
                            @RequestParam(value = "per_page") int perPage) {

        return messageService.getMessages(getLoggedUser(), new PageRequest(--page, perPage));
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
