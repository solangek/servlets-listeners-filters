package listen;
import db.DBConnectionManager;

import javax.servlet.*;
import javax.servlet.annotation.WebListener;
import java.util.logging.*;


/** This class implements many listeners under one class
 * Note that the listener is using INIT parameters of the CONTEXT
 * that are DEFINED in the web.xml file (the params MyServlet are private to the servlet)
 *  UNCOMMENT THE ANNOTATION @WebListener TO ENABLE THIS CODE
 */
@WebListener
public class MyContextListener implements ServletContextListener{

    // Public constructor is required by servlet spec
    public MyContextListener() {
    }

    /**
     * This method is invoked when the Servlet Context
     * (the Web application) is deployed.
     * @param servletContextEvent
     */
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        System.out.println("MyContextListener contextInitialized!");

        ServletContext ctx = servletContextEvent.getServletContext();

        String driver = ctx.getInitParameter("DBDRIVER-GLOBAL");
        String url = ctx.getInitParameter("DBURL-GLOBAL");

        //create database connection from init parameters and store it in context
        DBConnectionManager dbManager = new DBConnectionManager(driver,url);
        ctx.setAttribute("DBManager", dbManager);
        //ctx.log
        System.out.println(getClass().getName() + ": Database connection initialized for Application.");
    }

    /**
     *       This method is invoked when the Servlet Context
     *          (the Web application) is undeployed or
     *          Application Server shuts down.
     *
     * @param sce
     */
    public void contextDestroyed(ServletContextEvent sce) {

        ServletContext ctx = sce.getServletContext();
        DBConnectionManager dbManager = (DBConnectionManager) ctx.getAttribute("DBManager");
        dbManager.closeConnection();
        System.out.println(this.getClass().getName() + ": Database connection closed for Application.");
    }
}
