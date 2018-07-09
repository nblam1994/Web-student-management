package com.lam.StudentManagement3.util;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

public class Util {

    public static Cookie getCookie(HttpServletRequest request, String name) {

        Cookie[] cookies = request.getCookies();
        for(Cookie cookie : cookies) {
            if(cookie.getName().equals(name)) {
                return cookie;
            }
        }

        return null;
    }
}
