package superlistener;

import javax.servlet.*;
import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSessionAttributeListener;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;
import javax.servlet.http.HttpSessionBindingEvent;

/** This class implements many listeners under one class
 * Note that the listener is using INIT parameters of the CONTEXT
 * that are DEFINED in the web.xml file (the params MyServlet are private to the servlet)
 * UNCOMMENT THE ANNOTATION @WebListener TO ENABLE THIS CODE
 */
//@WebListener()
public class MySuperListener implements ServletContextListener,
        HttpSessionListener, HttpSessionAttributeListener, ServletRequestListener {

    private int counter = 0;
    // Public constructor is required by servlet spec
    public MySuperListener() {
    }

    public void contextInitialized(ServletContextEvent servletContextEvent) {
      /* This method is called when the servlet context is
         initialized(when the Web application is deployed). 
         You can initialize servlet context related data here.
      */

        System.out.println("MySuperListener.contextInitialized executed.");
    }

    /**
     *       This method is invoked when the Servlet Context
     *          (the Web application) is undeployed or
     *          Application Server shuts down.
     *
     * @param sce
     */
    public void contextDestroyed(ServletContextEvent sce) {
        System.out.println("MySuperListener.contextDestroyed executed.");
    }

    public void sessionCreated(HttpSessionEvent sessionEvent) {
        System.out.println("MySuperListener Session Created:: ID=" + sessionEvent.getSession().getId());
    }

    public void sessionDestroyed(HttpSessionEvent sessionEvent) {
        System.out.println("MySuperListener Session Destroyed:: ID=" + sessionEvent.getSession().getId());
        System.out.println ("MySuperListener session var:" + sessionEvent.getSession().getAttribute("somevariable"));
    }

    // -------------------------------------------------------
    // HttpSessionAttributeListener implementation
    // -------------------------------------------------------

    public void attributeAdded(HttpSessionBindingEvent sbe) {
      /* This method is called when an attribute 
         is added to a session.
      */
        sbe.getSession().getServletContext().log("MySuperListener ServletContext attribute added::{" + sbe.getName() + "," + sbe.getValue() + "}");

    }

    public void attributeRemoved(HttpSessionBindingEvent sbe) {
      /* This method is called when an attribute
         is removed from a session.
      */
        sbe.getSession().getServletContext().log("MySuperListener ServletContext attribute removed::{" + sbe.getName() + "," + sbe.getValue() + "}");

    }

    public void attributeReplaced(HttpSessionBindingEvent sbe) {
      /* This method is invoked when an attibute
         is replaced in a session.
      */
        sbe.getSession().getServletContext().log("MySuperListenerServletContext attribute replaced::{" + sbe.getName() + "," + sbe.getValue() + "}");

    }
    public void requestDestroyed(ServletRequestEvent servletRequestEvent) {
        ServletRequest servletRequest = servletRequestEvent.getServletRequest();
        servletRequest.getServletContext().log("MySuperListener ServletRequest destroyed. Remote IP=" + servletRequest.getRemoteAddr());
    }

    public void requestInitialized(ServletRequestEvent servletRequestEvent) {
        counter++;
        ServletRequest servletRequest = servletRequestEvent.getServletRequest();
        servletRequest.getServletContext().log("MySuperListener ServletRequest initialized. Remote IP=" + servletRequest.getRemoteAddr());
        servletRequest.getServletContext().log("MySuperListener total requests count = " + counter);
    }
}
