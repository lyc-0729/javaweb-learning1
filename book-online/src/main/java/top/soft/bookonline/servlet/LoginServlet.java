package top.soft.bookonline.servlet;


import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import top.soft.bookonline.entity.User;
import top.soft.bookonline.service.UserService;
import top.soft.bookonline.service.impl.UserServiceImpl;

import java.io.IOException;

/**
 * @author LoginServlet
 * @description: TODO
 * @date 2024/10/26 16:42
 */

@WebServlet("/login")
public class LoginServlet extends HttpServlet {
    private UserService userService;
    @Override
    public void init(ServletConfig config) throws ServletException {
        userService = new UserServiceImpl();
    }
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String account = req.getParameter("account");
        String password = req.getParameter("password");
        String remember = req.getParameter("remember");
        User user =userService.signIn(account, password);
        if (user != null) {
            req.getSession().setAttribute("user", user);
            if(remember!=null){
                Cookie usernameCookie=new Cookie("username",account);
                Cookie passwordCookie=new Cookie("password",password);
                usernameCookie.setMaxAge(60*60*24*7);
                passwordCookie.setMaxAge(60*60*24*7);
                resp.addCookie(usernameCookie);
                resp.addCookie(passwordCookie);
            }
            HttpSession session = req.getSession();
            session.setAttribute("user", user);
            resp.sendRedirect("/index");
        }else{
            resp.setContentType("text/html;charset=utf-8");
            resp.setCharacterEncoding("UTF-8");
            resp.getWriter().write("<script>alert('账号或密码错误');location.href='/';</script>");
        }
    }
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doPost(req, resp);
    }
}
