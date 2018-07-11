package com.lam.StudentManagement3.interceptor;

import com.lam.StudentManagement3.student.Account;
import com.lam.StudentManagement3.util.Util;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class RequiredLoginInterceptor extends HandlerInterceptorAdapter {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        try {

            HttpSession session = request.getSession();
            Account account = (Account) session.getAttribute("account");
            Cookie cookie = Util.getCookie(request, "account");

            if (account != null || cookie != null) {
                return true;
            }
            else {
                response.sendRedirect("/login");
            }


        } catch (Exception e) {

            System.out.println("Exception at RequiredLoginInterceptor");

        }


        return false;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }
}
