package com.example.class03response.response;

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
 * @description: TODO
 * @date 2024/9/28 14:54
 */

@WebServlet("/res")
public class ResponseTypeDemo extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String type = req.getParameter("type");
        switch (type) {
            case "img":
                getImage(req,resp);
                break;
            case "pdf":
                getPdf(req,resp);
            case "txt":
                getText(req,resp);
            default:
                break;
        }
    }


    private void getImage(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("image/jpg");
        String realPath = request.getServletContext().getRealPath("/1/image.jpg");
        File flie = new File(realPath);
        InputStream in = new FileInputStream(flie);
        int read = 0;
        ServletOutputStream out = response.getOutputStream();
        while ((read = in.read()) != -1) {
            out.write(read);
        }
        out.flush();
        out.close();
    }

    private void getPdf(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("application/pdf");
        String realPath = request.getServletContext().getRealPath("/1/Java2实用教程（第5版）.pdf");
        File file = new File(realPath);
        InputStream in = new FileInputStream(file);
        int read = 0;
        ServletOutputStream out = response.getOutputStream();
        while ((read = in.read()) != -1) {
            out.write(read);
        }
        out.flush();
        out.close();
    }
    private void getText(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/txt");
        String realPath = request.getServletContext().getRealPath("/1/02.txt");
        File file = new File(realPath);
        InputStream in = new FileInputStream(file);
        int read = 0;
        ServletOutputStream out = response.getOutputStream();
        while ((read = in.read()) != -1) {
            out.write(read);
        }
        out.flush();
        out.close();
    }
}
