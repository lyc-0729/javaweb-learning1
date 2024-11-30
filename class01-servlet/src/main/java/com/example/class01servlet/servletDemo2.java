package com.example.class01servlet;

import jakarta.servlet.*;

import java.io.IOException;

public class servletDemo2 implements Servlet {
    public servletDemo2() {
        super();
    }

    /**
     * Servlet 初始化
     * @param servletConfig
     * @throws ServletException
     */

    @Override
    public void init(ServletConfig servletConfig) throws ServletException {
        System.out.println("servletDemo2 初始化");

    }

    /**
     * 获取servlet配置
     * @return
     */

    @Override
    public ServletConfig getServletConfig() {
        return null;
    }

    /**
     * @param servletRequest
     * @param servletResponse
     * @throws ServletException
     * @throws IOException
     */

    @Override
    public void service(ServletRequest servletRequest, ServletResponse servletResponse) throws ServletException, IOException {
        servletResponse.getWriter().write("servletDemo2servletDemo2 执行");
        System.out.println("service 执行");

    }

    @Override
    public String getServletInfo() {
        return "";
    }

    @Override
    public void destroy() {
        System.out.println("销毁方法");
    }
}
