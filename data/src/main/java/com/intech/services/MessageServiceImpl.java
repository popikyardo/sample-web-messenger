package com.intech.services;

import com.intech.dto.PagingCriteria;
import com.intech.jpa.Message;
import com.intech.jpa.User;
import com.intech.repository.MessageDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
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
    public Page<Message> getMessages(User User, Pageable pageable) {
        return messageDAO.findByToUserAndDeletedOrderByDateDesc(User, false, pageable);
    }

    @Override
    public void saveAndFlush(Message message) {
        messageDAO.saveAndFlush(message);
    }

    @Override
    public Page<Message> search(PagingCriteria criteria, User userTo) {
        Pageable pageable;
        if(criteria.getSortFields()!=null && !criteria.getSortFields().isEmpty()){
            pageable = new PageRequest(
                    criteria.getDisplayStart(),
                    criteria.getDisplaySize(),
                    Sort.Direction.valueOf(criteria.getSortFields().get(0).getDirection().name()),
                    criteria.getSortFields().get(0).getField());
        } else {
            pageable = new PageRequest(
                    criteria.getDisplayStart(),
                    criteria.getDisplaySize());
        }
        if(criteria.getSearch()!=null && !criteria.getSearch().isEmpty()) {
            if(userTo!=null) {
                return messageDAO.search(criteria.getSearch(), userTo, pageable);
            } else {
                return messageDAO.search(criteria.getSearch(), pageable);
            }
        }
        if(userTo!=null) {
            return messageDAO.findByToUserAndDeletedOrderByDateDesc(userTo, false, pageable);
        }
        return messageDAO.findByDeletedOrderByDateDesc(false, pageable);
    }
}