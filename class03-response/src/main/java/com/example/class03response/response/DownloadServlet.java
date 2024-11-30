package response;

import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.FileInputStream;
import java.io.IOException;

/**
 * @author
 * @description: TODO
 * @date 2024/9/28 19:00
 */
@WebServlet("/download")
public class DownloadServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String filename = req.getParameter("filename");
        ServletContext servletContext=this.getServletContext();
        String realPath="D:\\soft\\javaweb-learning\\class03-response\\src\\main\\java\\com\\example\\class03response\\flie\\b.txt";
        FileInputStream fis=new FileInputStream(realPath);
        String mimeType=servletContext.getMimeType(filename);
        resp.setHeader("content-type",mimeType);
        resp.setHeader("content-disposition","attachment; filename=  "+filename);
        ServletOutputStream sos=resp.getOutputStream();
        byte[] buffer=new byte[1024*8];
        int len;
        while((len=fis.read(buffer))!=-1){
            sos.write(buffer,0,len);
        }
        fis.close();

    }
}