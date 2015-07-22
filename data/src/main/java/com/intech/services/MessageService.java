package com.intech.services;

import com.intech.jpa.Message;
import com.intech.jpa.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * Created by popikyardo on 22.07.15.
 */
public interface MessageService extends GenericService<Message, Long> {

    public List<Message> getNewMessages(User user);

    public Page<Message> getMessages(User user, Pageable pageable);

    public void saveAndFlush(Message message);
}
