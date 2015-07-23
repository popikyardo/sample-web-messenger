package com.intech.repository;

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
public interface UserDAO extends JpaRepository<User, Long> {

    public User findByEmail(String login);

    @Query("SELECT u FROM User u WHERE u.firstName LIKE %?1% OR u.lastName LIKE %?1% or u.email LIKE %?1%")
    public Page<User> search(String query, Pageable pageable);
}
