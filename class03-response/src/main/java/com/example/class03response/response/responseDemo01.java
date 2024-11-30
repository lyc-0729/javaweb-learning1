package response;


import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

/**
 * @author Lyc
 * @description: Response
 * @date 2024/9/28 13:57
 */

@WebServlet("/responseDemo01")
public class responseDemo01 extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
        String username = req.getParameter("username");
        System.out.println("responseDemo01 被请求了......");
        if(username.equals("Zhangsan")){
            resp.setStatus(200);
        }else {
            resp.setStatus(404);
        }
    }
}