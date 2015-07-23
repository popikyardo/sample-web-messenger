package com.intech.services;

import com.intech.jpa.SystemRole;
import com.intech.repository.SystemRoleDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

/**
 * Created by popikyardo on 23.07.15.
 */
@Service
public class RolesServiceImpl extends GenericServiceImpl<SystemRole, Integer> implements RolesService {

    private SystemRoleDAO systemRoleDAO;

    @Autowired
    public RolesServiceImpl(
            @Qualifier("systemRoleDAO") JpaRepository<SystemRole, Integer> genericDao) {
        super(genericDao);
        this.systemRoleDAO = (SystemRoleDAO) genericDao;
    }
}
