package listen;

import bank.Account;

import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSessionAttributeListener;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;
import javax.servlet.http.HttpSessionBindingEvent;

/**
 * Note that the listener is using INIT parameters of the CONTEXT
 * that are DEFINED in the web.xml file (the params MyServlet are private to the servlet)
 * UNCOMMENT THE ANNOTATION @WebListener TO ENABLE THIS CODE
 */
@WebListener
public class MySessionListener implements HttpSessionListener, HttpSessionAttributeListener{

    public MySessionListener() {
    }

    public void sessionCreated(HttpSessionEvent sessionEvent) {
        //sessionEvent.getSession().getServletContext().log
        System.out.println(this.getClass().getName() + ": MySessionListener Session Created:: ID=" + sessionEvent.getSession().getId());

        // create the account object
        sessionEvent.getSession().setAttribute("account", new Account());
    }

    public void sessionDestroyed(HttpSessionEvent sessionEvent) {
        System.out.println(this.getClass().getName() + ": MySessionListener Session Destroyed:: ID=" + sessionEvent.getSession().getId());
        System.out.println (this.getClass().getName() + ": MySessionListener session var:" + sessionEvent.getSession().getAttribute("somevariable"));
    }

    public void attributeAdded(HttpSessionBindingEvent sbe) {
        System.out.println(this.getClass().getName() + ": MySessionListener  attribute added::{" + sbe.getName() + "," + sbe.getValue() + "}");
    }

    public void attributeRemoved(HttpSessionBindingEvent sbe) {
        System.out.println(this.getClass().getName() + ": MySessionListener  attribute removed::{" + sbe.getName() + "," + sbe.getValue() + "}");
    }

    public void attributeReplaced(HttpSessionBindingEvent sbe) {
        System.out.println(this.getClass().getName() + ": MySessionListener  attribute replaced::{" + sbe.getName() + "," + sbe.getValue() + "}");
    }
}
