package com.example.boxanime.controller;

import com.example.boxanime.model.User;
import com.example.boxanime.service.login_logout.JDBCLogin;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "Login_Logout_Servlet", value = "login")
public class Login_Logout_Servlet extends HttpServlet {
    public JDBCLogin jdbcLogin;

    @Override
    public void init() throws ServletException {
        jdbcLogin = new JDBCLogin();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html;charset=UTF-8");
        String gmail = req.getParameter("gmail");
        String password = req.getParameter("password");
        User user = new User(gmail, password);
        if (jdbcLogin.checkAccountUser(user)) {
            resp.sendRedirect("/anime?action=anime");
        } else {
            resp.sendRedirect("/anime?action=create");
        }
    }
}
