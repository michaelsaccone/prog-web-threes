package it.michaelsaccone.sudoku;

import it.michaelsaccone.sudoku.Beans.User;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;
import java.util.ArrayList;

@WebServlet(name = "Register", value = "/register")
public class Register extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        var username = request.getParameter("username");
        var name = request.getParameter("name");
        var surname = request.getParameter("surname");

        var users = (ArrayList<User>) this.getServletContext().getAttribute("users");

        synchronized (users) {
            users.add(new User(name, surname, username));
            response.sendRedirect(response.encodeRedirectURL("login.jsp"));
        }


    }
}
