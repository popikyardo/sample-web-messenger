package com.intech.repository;

import com.intech.jpa.SystemRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by popikyardo on 22.07.15.
 */
@Repository
public interface SystemRoleDAO extends JpaRepository<SystemRole, Integer> {

    public SystemRole findByName(String login);
}
