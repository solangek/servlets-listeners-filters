package bank;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
/*
When we want to specify the security model for our servlet, including roles, access control
and authentication requirements we use the annotation @ServletSecurity.

In this example we will restrict access to our AccountServlet using the @ServletSecurity annotation:
In this case, when invoking the AccountServlet, the browser pops up a login screen for the user
to enter a valid username and password.

We can use @HttpConstraint and @HttpMethodConstraint annotations to specify values for the attributes
value and httpMethodConstraints, of @ServletSecurity annotation.

@HttpConstraint annotation applies to all HTTP methods. In other words, it specifies the default security constraint.

@HttpConstraint has three attributes:
value
rolesAllowed
transportGuarantee

Out of these attributes, the most commonly used attribute is rolesAllowed.
In the example code snippet above, users who belong to the role Member are allowed to invoke all HTTP methods.

@HttpMethodConstraint annotation allows us to specify the security constraints of a particular HTTP method.

@HttpMethodConstraint has the following attributes:
value
emptyRoleSemantic
rolesAllowed
transportGuarantee

In the example code snippet below, it shows how the doPost method is restricted only for users who belong
to the Admin role, allowing the deposit function to be done only by an Admin user.

more details and how to define roles:  https://javaee.github.io/tutorial/security-webtier003.html.
 */
@WebServlet(
        name = "BankAccountServlet",
        description = "Represents a Bank Account and it's transactions",
        urlPatterns = {"/account", "/bankAccount" },
        initParams = { @WebInitParam(name = "type", value = "savings")})
public class AccountServlet extends javax.servlet.http.HttpServlet {

    String accountType = null;

    public void init(ServletConfig config) throws ServletException {
        accountType = config.getInitParameter("type");
    }


    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {

        PrintWriter writer = response.getWriter();

        writer.println("<html>Hello, I am an AccountServlet!" +
                "<form action='/account' method='post'>Your deposit: <input type='text' name='dep'>" +
                "<input type='submit'></html>");
        writer.flush();
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {

        String paramDepositAmt = request.getParameter("dep");
        if (paramDepositAmt == null)
            response.sendRedirect("/account");

        Double depositAmt = Double.parseDouble(paramDepositAmt);

        Account acc = (Account) request.getSession().getAttribute("account");
        // this is what we would need to do if we didn't use session listeners
//        if (acc == null) {
//            acc = new Account();
//            request.getSession().setAttribute("account", acc);
//        }
        acc.deposit(depositAmt);


        PrintWriter writer = response.getWriter();
        writer.println("<html> Balance of " + accountType + " account is: " + acc.getBalance() + "<br/><a href='/account'>back</a></html>");
        writer.flush();
    }
}