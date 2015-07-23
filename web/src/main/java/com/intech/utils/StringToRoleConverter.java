package com.intech.utils;

import com.intech.jpa.SystemRole;
import com.intech.repository.SystemRoleDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;

/**
 * Created by popikyardo on 22.07.15.
 */
public class StringToRoleConverter implements Converter<String, SystemRole> {

    @Autowired
    private SystemRoleDAO roleDAO;

    @Override
    public SystemRole convert(String s) {
        return roleDAO.findOne(Integer.valueOf(s));
    }
}
