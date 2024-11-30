package com.example.class04cookiesession.cookie;


import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;

/**
 * @author CookieDemo02
 * @description: TODO
 * @date 2024/10/19 14:02
 */

@WebServlet("/cookieDemo04")
public class CookieDemo04 extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
        Cookie[] cookies = req.getCookies();
        for (Cookie cookie : cookies) {
            String name = cookie.getName();
            if ("chineseName".equals(name)) {
                String value = cookie.getValue();
                String decode = URLDecoder.decode(value, StandardCharsets.UTF_8);
                System.out.println("用户名："+decode);
                break;
            }
        }
    }
}
