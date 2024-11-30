package com.example.class02request;

import jakarta.servlet.ServletContext;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/requestDemo07")
public class requestDemo07 extends HelloServlet{
    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse response) throws IOException {
        ServletContext servletContext = req.getServletContext();
        String info = String.valueOf(servletContext.getAttribute("info"));
        System.out.println(info);
    }
}
