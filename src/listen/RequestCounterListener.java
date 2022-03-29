package listen;

import javax.servlet.*;
import javax.servlet.annotation.WebListener;
import java.util.concurrent.atomic.AtomicInteger;


/**
 * Note that the listener is using INIT parameters of the CONTEXT
 * that are DEFINED in the web.xml file (the params MyServlet are private to the servlet)
 * UNCOMMENT THE ANNOTATION @WebListener TO ENABLE THIS CODE
 */
@WebListener()
public class RequestCounterListener implements ServletRequestListener {

    private AtomicInteger counter = new AtomicInteger();
    public RequestCounterListener() {
    }

    public void requestDestroyed(ServletRequestEvent servletRequestEvent) {
        // decrement the counter
        //counter.decrementAndGet();
        ServletRequest servletRequest = servletRequestEvent.getServletRequest();
        System.out.println(this.getClass().getName() + ": MyRequestListener ServletRequest destroyed. Remote IP=" + servletRequest.getRemoteAddr());
    }

    public void requestInitialized(ServletRequestEvent servletRequestEvent) {
        // increment the counter
        counter.incrementAndGet();
        ServletRequest servletRequest = servletRequestEvent.getServletRequest();
        System.out.println(this.getClass().getName() + ": MyRequestListener ServletRequest initialized. Remote IP=" + servletRequest.getRemoteAddr());
        System.out.println(this.getClass().getName() + ": total requests count = " + counter);
    }
}
