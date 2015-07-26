package com.intech.services;

import com.intech.dto.PagingCriteria;
import com.intech.jpa.SystemRole;
import com.intech.jpa.User;
import com.intech.repository.UserDAO;
import com.intech.utils.SecUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;


/**
 * Created by popikyardo on 22.07.15.
 */
@Service("userService")
@Transactional
public class UserServiceImpl extends GenericServiceImpl<User, Long> implements UserService {

    private UserDAO userDAO;

    @Autowired
    public UserServiceImpl(
            @Qualifier("userDAO") JpaRepository<User, Long> genericDao) {
        super(genericDao);
        this.userDAO = (UserDAO) genericDao;
    }

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
        if (StringUtils.isBlank(login)) {
            throw new UsernameNotFoundException("User name is empty");
        }
        final User user = userDAO.findByEmail(login);

        if (user == null) {
            throw new UsernameNotFoundException("No user found");
        }
        userDAO.save(user);

        return new org.springframework.security.core.userdetails.User(
                user.getEmail(),
                user.getPassword(),
                getGrantedAuthorities(user.getRoleNames()));
    }

    public static List<GrantedAuthority> getGrantedAuthorities(String[] roles) {
        List<GrantedAuthority> authorities = new ArrayList<>();
        for (String role : roles) {
            authorities.add(new SimpleGrantedAuthority(role));
        }
        return authorities;
    }

    @Override
    @Transactional(readOnly = true)
    public User findOne(final String login) {
        return userDAO.findByEmail(login);
    }

    @Override
    @Transactional
    public User save(User user) {
        if (user.getId() != null) {
            final User persistentUser = userDAO.findOne(user.getId());

            persistentUser.setEmail(user.getEmail());
            persistentUser.setFirstName(user.getFirstName());
            persistentUser.setLastName(user.getLastName());
            persistentUser.setUserRole(user.getUserRole());

            if (user.getPassword() != null && !user.getPassword().isEmpty()) {
                persistentUser.setPassword(SecUtils.bcrypt(user.getPassword()));
            }

            return userDAO.save(persistentUser);
        } else {
            List<SystemRole> roles = user.getUserRole();
            user.setUserRole(null);
            user.setCreateAt(new Date());
            user.setPassword(SecUtils.bcrypt(user.getPassword()));
            userDAO.save(user);

            user.setUserRole(roles);
            return userDAO.save(user);
        }
    }


    @Override
    @Transactional(readOnly = true)
    public List<User> search(String query) {
        if (StringUtils.isBlank(query)) {
            return Collections.emptyList();
        }
        return userDAO.search(query, null).getContent();
    }

    @Override
    public Page<User> search(PagingCriteria criteria) {
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
            return userDAO.search(criteria.getSearch(), pageable);
        }
        return userDAO.findAll(pageable);
    }

    @Override
    @Transactional(readOnly = true)
    public List<User> getContacts(User u) {
        return userDAO.findContacts(u.getId());
    }

    @Override
    @Transactional
    public void addContact(User owner, User contact) {
        List<User> contacts = userDAO.findContacts(owner.getId());
        contacts.add(contact);
        owner.setContacts(contacts);
        userDAO.save(owner);
    }

    @Override
    @Transactional
    public void deleteContact(User owner, User contact) {
        List<User> contacts = userDAO.findContacts(owner.getId());
        contacts.remove(contact);
        owner.setContacts(contacts);
        userDAO.save(owner);
    }

}
