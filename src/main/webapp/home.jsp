<%@ page import="it.michaelsaccone.sudoku.Beans.User" %><%--
  Created by IntelliJ IDEA.
  User: jason
  Date: 1/10/23
  Time: 5:00 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<jsp:include page="header.html"></jsp:include>

<script src="script/game.js"></script>

<jsp:useBean id="user" class="it.michaelsaccone.sudoku.Beans.User" scope="session"/>
<jsp:useBean id="matrix" class="it.michaelsaccone.sudoku.Matrix" scope="session" />
<div class="container">
    <div class="row justify-content-start">

        <table class="table">
            <thead>
            <tr>
                <th scope="col">Utente</th>
                <th scope="col">Indirizzo IP</th>
                <th scope="col">Browser</th>
                <th scope="col">Max Score</th>
            </tr>
            </thead>
            <tbody>
            <tr>
                <th scope="row"><%= user.getName() + " " + user.getSurname() %></th>
                <td><%= request.getRemoteAddr() %></td>
                <td><%= request.getHeader("user-agent") %></td>
                <td><%= user.getMaxScore() %></td>
            </tr>

            </tbody>
        </table>

    </div>
</div>

<div class="container mt-5" id="game">
    <div class="row justify-content-center">
        <div class="text-center col-6 border-1 border-dark" id="matrix">
            <%= matrix.printMatrix().replaceAll("\n", "<br/>")%>
        </div>
    </div>
    <div class="row justify-content-center">
        <div class="col-12 text-center">
            <button id="up-arrow" onclick="clickButton('U')">&#11014;</button>
            <button id="down-arrow" onclick="clickButton('D')">DOWN</button>
            <button id="left-arrow" onclick="clickButton('L')">LEFT</button>
            <button id="right-arrow" onclick="clickButton('R')">RIGHT</button>
        </div>
    </div>
</div>




<jsp:include page="footer.html"></jsp:include>