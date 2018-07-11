package com.lam.StudentManagement3.util;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

public class Util {

    public static Cookie getCookie(HttpServletRequest request, String id) {

        Cookie[] cookies = request.getCookies();
        for(Cookie cookie : cookies) {
            if(cookie.getName().equals(id)) {
                return cookie;
            }
        }

        return null;
    }
}
