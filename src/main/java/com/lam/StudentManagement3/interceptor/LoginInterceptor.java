package com.lam.StudentManagement3.interceptor;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.lam.StudentManagement3.student.Account;
import com.lam.StudentManagement3.util.Util;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.HandlerMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.Map;

public class LoginInterceptor extends HandlerInterceptorAdapter{

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {

        try {

            Map pathVariables = (Map) request.getAttribute(HandlerMapping.URI_TEMPLATE_VARIABLES_ATTRIBUTE);
            String id = (String) pathVariables.get("id");
            Cookie cookie = Util.getCookie(request, "account");
            Account account  = null;


            if(cookie != null) {
                ObjectMapper mapper = new ObjectMapper();
                account = mapper.readValue(URLDecoder.decode(cookie.getValue()),
                        Account.class);
            }


            if (account != null && account.getId().equals(id))  {

                return true;
            }
            else {

               response.sendRedirect("/login");
            }


        } catch (Exception e) {

            e.printStackTrace();
            System.out.println("Exception at Inteceptor");
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
