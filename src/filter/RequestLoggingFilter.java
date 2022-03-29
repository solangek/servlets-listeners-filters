package filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.Enumeration;

/**
 * this filter intercepts ALL requests and prints them in the log
 * uncomment the @WebFilter annotation to enable
 */
@WebFilter(urlPatterns = "*", filterName = "logging", description = "log all requests")
public class RequestLoggingFilter implements Filter {
    private ServletContext context;

    public void init(FilterConfig fConfig)  {
        this.context = fConfig.getServletContext();
        System.out.println("# RequestLoggingFilter initialized");
    }

    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {

        HttpServletRequest req = (HttpServletRequest) request;
        Enumeration<String> params = req.getParameterNames();
        while (params.hasMoreElements()) {
            String name = params.nextElement();
            String value = request.getParameter(name);
            System.out.println(req.getRemoteAddr() + "::RequestLoggingFilter Request Params::{" + name + "=" + value + "}");
        }
        Cookie[] cookies = req.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                System.out.println(req.getRemoteAddr()  + "::RequestLoggingFilter Cookie::{" + cookie.getName() + "," + cookie.getValue() + "}");
            }
        }
        // pass the request along the filter chain
        chain.doFilter(request, response);

        // here we can write code for handling the response
        System.out.println("handling the Response inside RequestLoggingFilter");
    }

    public void destroy() { //we can close resources here }
    }
}