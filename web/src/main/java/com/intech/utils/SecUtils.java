package com.intech.utils;

import org.springframework.security.crypto.bcrypt.BCrypt;

/**
 * Created by popikyardo on 22.07.15.
 */
public class SecUtils {

    public static String bcrypt(final String secret) {
        return BCrypt.hashpw(secret, BCrypt.gensalt());
    }
}
