package filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/*
In the example below we are using the @WebFilter annotation to
redirect any unauthorized access to the login page
Notice the URL pattern: we catch any URL of the form /account/*

uncomment the @WebFilter annotation to see the effect
 */

//@WebFilter(
//        urlPatterns = "/account/*",
//        filterName = "Account Filter",
//        description = "Filter all account transaction URLs")
public class AccountFilter implements javax.servlet.Filter {

    public void init(FilterConfig filterConfig) throws ServletException {
    }

    public void doFilter(
            ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse res = (HttpServletResponse) response;

        System.out.println("LoginFilter: Inside AccountFilter! Remote IP=" + request.getRemoteAddr());
        // check if permissions.... for example some flag in session scope
        // we pass the request to next filter if any
        chain.doFilter(request, response);
        
        // here we can write code for handling the response
    }

    public void destroy() {
    }

}