package com.intech.services;

import com.intech.dto.PagingCriteria;
import com.intech.jpa.User;
import org.springframework.data.domain.Page;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface UserService extends UserDetailsService, GenericService<User, Long> {

    public User findOne(String email);

    public List<User> search(String query);

    public Page<User> search(PagingCriteria criteria);
}
