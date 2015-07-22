package com.intech.repository;

import com.intech.jpa.Message;
import com.intech.jpa.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

/**
 * Created by popikyardo on 22.07.15.
 */
@Repository
public interface MessageDAO extends JpaRepository<Message, Long> {

    public List<Message> findByToUserAndStatus(User user, Message.MessageStatus status);

    public Page<Message> findByToUserAndDeletedOrderByDateDesc(User user, boolean deleted, Pageable pageable);

}
