package com.intech.services;

import com.intech.jpa.Message;
import com.intech.jpa.User;
import com.intech.repository.MessageDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;
import java.util.List;

/**
 * Created by popikyardo on 22.07.15.
 */
@Service
public class MessageServiceImpl extends GenericServiceImpl<Message, Long> implements MessageService {

    private MessageDAO messageDAO;

    @Autowired
    public MessageServiceImpl(
            @Qualifier("messageDAO") JpaRepository<Message, Long> genericDao) {
        super(genericDao);
        this.messageDAO = (MessageDAO) genericDao;
    }

    @Override
    public List<Message> getNewMessages(User User) {
        return messageDAO.findByToUserAndStatus(User, Message.MessageStatus.NEW);
    }

    @Override
    public Page<Message> getMessages(User User, Pageable pageable) {
        return messageDAO.findByToUserAndDeletedOrderByDateDesc(User, false, pageable);
    }

    @Override
    public void saveAndFlush(Message message) {
        messageDAO.saveAndFlush(message);
    }
}