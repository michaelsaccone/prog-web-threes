package it.michaelsaccone.sudoku;

import it.michaelsaccone.sudoku.Beans.User;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;
import java.util.ArrayList;

@WebServlet(name = "Login", value = "/login")
public class Login extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        var username = request.getParameter("username");

        var users = (ArrayList<User>) this.getServletContext().getAttribute("users");

        synchronized (users) {
            try {
                var user = users.stream().filter(user1 -> user1.getUsername().equals(username))
                                .findFirst().orElseThrow(() -> new Exception("Login failed"));
                System.out.println("Utente loggato: " + user.getUsername());
                var session = request.getSession();

                // Avvio gioco matrice
                Matrix sessionMatrix = new Matrix();
                sessionMatrix.initializeMatrix();

                session.setAttribute("user", user);
                session.setAttribute("matrix", sessionMatrix);

                // se c'e' bisogno (se i cookie sono bloccati e la sessione non si puo' mantenere via cookie)
                // viene inserito l'id della sessione nell'url di redirect per mantenere questa in ogni caso.
                // java verifica autonomamente se e' il caso di usare l'url con session ID oppure no.
                response.sendRedirect(response.encodeRedirectURL("home.jsp"));
            } catch (Exception e) {
                response.sendRedirect(response.encodeRedirectURL("register.jsp"));
            }
        }


//        request.getRequestDispatcher("home.jsp").forward(request, response);
//        response.sendRedirect("home.jsp");
    }
}
