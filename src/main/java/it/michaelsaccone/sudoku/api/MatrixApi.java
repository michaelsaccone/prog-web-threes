package it.michaelsaccone.sudoku.api;

import it.michaelsaccone.sudoku.Matrix;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;

@WebServlet(name = "MatrixApi", value = "/api/matrix/move")
public class MatrixApi extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("ricevuta action: " + request.getParameter("action"));
        var session = request.getSession();
        var matrix = (Matrix) session.getAttribute("matrix");
        matrix.performAction(request.getParameter("action"));
        var writer = response.getWriter();
        writer.write(matrix.printMatrix());
    }
}
