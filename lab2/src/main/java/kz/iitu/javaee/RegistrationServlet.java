package kz.iitu.javaee;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class RegistrationServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("registration.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        System.out.println("Registration POST");

        String username = req.getParameter("username");
        String password = req.getParameter("password");
        String status = "registration";

        req.setAttribute("username", username);
        req.setAttribute("password", password);
        req.setAttribute("status", status);

//        System.out.println("GO MAIN PAGE");
        RequestDispatcher view = req.getRequestDispatcher("index.jsp");
        view.forward(req, resp);
    }
}
