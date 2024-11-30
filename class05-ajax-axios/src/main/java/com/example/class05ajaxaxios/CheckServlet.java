package com.example.class05ajaxaxios;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author CheckServlet
 * @description: TODO
 * @date 2024/11/9 14:27
 */

@WebServlet("/check")
public class CheckServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
       String username = req.getParameter("username");
       boolean equals =!"admin".equals(username);
       resp.getWriter().write(String.valueOf(equals));
    }
}
