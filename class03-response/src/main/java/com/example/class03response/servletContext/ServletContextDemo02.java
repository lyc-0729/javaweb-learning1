package com.example.class03response.servletContext;


import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * @author
 * @description: 通过ServletContext获取文件内容
 * @date 2024/9/28 16:42
 */

@WebServlet("/servletContextDemo02")
public class ServletContextDemo02 extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ServletContext servletContext = req.getServletContext();
        resp.setContentType("text/html;charset=utf-8");

        String arealPath = servletContext.getRealPath("/a.txt");
        System.out.println(arealPath);

        String brealPath = servletContext.getRealPath("/WEB-INF/b.txt");
        System.out.println(brealPath);

        String crealPath = servletContext.getRealPath("/resources/c.txt");
        System.out.println(crealPath);

        File file = new File(arealPath);
        InputStream in = new FileInputStream(file);
        int read =0;
        ServletOutputStream out = resp.getOutputStream();
        while((read=in.read())!=-1){
            out.write(read);
        }
        in.close();
        out.flush();
        out.close();
    }
}
