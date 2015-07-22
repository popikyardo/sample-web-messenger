package com.intech.jpa;

import com.intech.repository.SystemRoleDAO;
import com.intech.repository.UserDAO;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import static org.junit.Assert.*;

/**
 * Created by popikyardo on 22.07.15.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath*:/spring/applicationContext-*.xml"})
@Ignore
public class TestUserDAO {

    @Autowired
    private UserDAO userDAO;

    @Autowired
    private SystemRoleDAO systemRoleDAO;

    // important!
    // this test is written for in-memory database. We suppose that none of data is present in db at the beginning of the test

    @Before
    public void init() {
        SystemRole sr = new SystemRole();
        sr.setName("ROLE_ADMIN");

        systemRoleDAO.save(sr);
    }


    @Test
    @Transactional
    public void testCreateUser() {
        User user = new User();

        user.setFirstName("Test");
        user.setLastName("Testovich");
        user.setEmail("admin@distant.ru");
        user.setPassword(bcrypt("123456"));

        //userDAO.save(user);

        SystemRole adminRole = systemRoleDAO.findByName("ROLE_ADMIN");
        List<SystemRole> roles = new ArrayList<>();
        roles.add(adminRole);
        user.setUserRole(roles);

        userDAO.save(user);

        User u = userDAO.findByEmail("admin@distant.ru");

        assertEquals(u.getFirstName(), user.getFirstName());
        assertEquals(u.getLastName(), user.getLastName());
    }

    private String bcrypt(final String secret) {
        return BCrypt.hashpw(secret, BCrypt.gensalt());
    }
}
