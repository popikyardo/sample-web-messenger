package com.intech.repository;

import com.intech.jpa.Message;
import com.intech.jpa.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.List;

/**
 * Created by popikyardo on 22.07.15.
 */
@Repository
public interface MessageDAO extends JpaRepository<Message, Long> {

    public Page<Message> findByToUserAndDeletedOrderByDateDesc(User user, boolean deleted, Pageable pageable);

    public Page<Message> findByDeletedOrderByDateDesc(boolean deleted, Pageable pageable);

    @Query("SELECT m FROM Message m WHERE m.topic LIKE %?1% AND m.deleted=false ORDER BY m.date DESC")
    public Page<Message> search(String query, Pageable pageable);

    @Query("SELECT m FROM Message m WHERE m.topic LIKE %?1% and m.toUser = ?2 and m.deleted=false ORDER BY m.date DESC")
    public Page<Message> search(String query, User toUser, Pageable pageable);
}
