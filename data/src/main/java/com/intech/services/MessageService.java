package com.intech.services;

import com.intech.dto.PagingCriteria;
import com.intech.jpa.Message;
import com.intech.jpa.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * Created by popikyardo on 22.07.15.
 */
public interface MessageService extends GenericService<Message, Long> {

    public Page<Message> getMessages(User user, Pageable pageable);

    public void saveAndFlush(Message message);

    public Page<Message> search(PagingCriteria criteria, User userTo);
}
