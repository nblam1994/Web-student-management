package com.lam.StudentManagement3.controller;
import com.lam.StudentManagement3.student.Account;
import com.sun.deploy.net.HttpResponse;
import org.springframework.http.HttpRequest;
import org.springframework.web.bind.annotation.PathVariable;
import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebFilter(urlPatterns = "/*")
public class LoginRequiredFiler implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) {


        try {

            HttpServletRequest request = (HttpServletRequest) servletRequest;
            HttpServletResponse response = (HttpServletResponse) servletResponse;

            Account account = (Account) request.getSession().getAttribute("account");
            String id = (String) request.getAttribute("id");

            System.out.println(account);
            System.out.println(id);

            if(account != null && account.getId().equals(id)) {
                filterChain.doFilter(servletRequest, servletResponse);
            }
            else {
                request.getRequestDispatcher("login").forward(request, response);
            }

        }
        catch (IOException e) {

            e.printStackTrace();
        }
        catch (ServletException s) {

            s.printStackTrace();
        }

    }

    @Override
    public void destroy() {

    }
}
