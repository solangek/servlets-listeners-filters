package listen;

import javax.servlet.*;


/**
 * Note that the listener is using INIT parameters of the CONTEXT
 * that are DEFINED in the web.xml file (the params MyServlet are private to the servlet)
 * UNCOMMENT THE ANNOTATION @WebListener TO ENABLE THIS CODE
 */
//@WebListener()
public class RequestCounterListener implements ServletRequestListener {

    private int counter = 0;
    public RequestCounterListener() {
    }

    public void requestDestroyed(ServletRequestEvent servletRequestEvent) {
        ServletRequest servletRequest = servletRequestEvent.getServletRequest();
        System.out.println(this.getClass().getName() + ": MyRequestListener ServletRequest destroyed. Remote IP=" + servletRequest.getRemoteAddr());
    }

    public void requestInitialized(ServletRequestEvent servletRequestEvent) {
        counter++;
        ServletRequest servletRequest = servletRequestEvent.getServletRequest();
        System.out.println(this.getClass().getName() + ": MyRequestListener ServletRequest initialized. Remote IP=" + servletRequest.getRemoteAddr());
        System.out.println(this.getClass().getName() + ": total requests count = " + counter);
    }
}
