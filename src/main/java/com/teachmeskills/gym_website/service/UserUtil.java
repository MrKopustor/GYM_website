package com.teachmeskills.gym_website.service;

import org.springframework.security.core.context.SecurityContextHolder;

import static com.teachmeskills.gym_website.request.dataBase.request.general.GeneralRequest.findUserByUsername;

public class UserUtil {

    public static long getID() {
        return findUserByUsername(SecurityContextHolder.getContext().getAuthentication().getName()).getId();
    }
}
