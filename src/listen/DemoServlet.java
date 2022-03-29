package listen;

import db.DBConnectionManager;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.io.IOException;
import java.io.PrintWriter;

/**
 * InitParam DBURL and DBDRIVER are servlet initialization parameters.
 */
@WebServlet(
        name = "DemoServlet",
        description = "A simple Servlet with Listeners - notice the output trace in the console",
        urlPatterns = {"/DemoServlet"},
        initParams = {
                @WebInitParam(name = "DBURL", value = "jdbc:mysql://localhost:3306/ex3?user=root&amp;password=12345678"),
                @WebInitParam(name = "DBDRIVER", value = "com.mysql.jdbc.Driver")})
public class DemoServlet extends HttpServlet {

    private DBConnectionManager db;

    /**
     * we read some init param, for example DB params
     * @param config
     * @throws ServletException
     */
    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init();
        // read some params
        this.db = new DBConnectionManager(config.getInitParameter("DBDRIVER"), config.getInitParameter("DBURL"));
    }

    @Override
    public void destroy() {
        // close db connection
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setStatus(HttpServletResponse.SC_NOT_FOUND);
    }

    /**
     * This servlet triggers all kind of listeners to demonstrate the various event that
     * can be tracked with a listener: request received, set/remove attribute, set session variable
     *
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ServletContext ctx = request.getServletContext();

        ctx.setAttribute("User", "some user");
        String user = (String) ctx.getAttribute("User");
        ctx.removeAttribute("User");

        // the minute we access the session, we trigger the session listener upon creation
        HttpSession session = request.getSession();
        session.setAttribute("somevariable", "a session value");

        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        out.write("This servlet does nothing but trigger its listeners:<br/>Hi " + user);
        out.close();

    }
}
