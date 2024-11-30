package top.soft.bookonline.servlet;
import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.RequestDispatcher;
import top.soft.bookonline.service.UserService;
import top.soft.bookonline.service.impl.UserServiceImpl;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Random;

@WebServlet("/register")
public class RegisterServlet extends HttpServlet {
    private UserService userService;

    @Override
    public void init(ServletConfig config) throws ServletException {
        userService = new UserServiceImpl();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setContentType("text/html;charset=UTF-8");

        String account = req.getParameter("account");
        String password = req.getParameter("password");
        String inputCaptcha = req.getParameter("captcha");

        // 验证验证码
        String sessionCaptcha = (String) req.getSession().getAttribute("captcha");
        if (inputCaptcha == null || !inputCaptcha.equalsIgnoreCase(sessionCaptcha)) {
            req.setAttribute("errorMessage", "验证码错误");
            RequestDispatcher dispatcher = req.getRequestDispatcher("/register.jsp");
            dispatcher.forward(req, resp);
            return;
        }

        try {
            userService.signUp(account, password);
            resp.sendRedirect(req.getContextPath() + "/login.html"); // 注册成功，重定向到登录界面内
        } catch (IllegalArgumentException e) {
            req.setAttribute("errorMessage", "账号已存在，请使用其他账号注册");
            RequestDispatcher dispatcher = req.getRequestDispatcher("/register.jsp");
            dispatcher.forward(req, resp); // 注册失败，转发回注册页面
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        if ("captcha".equals(action)) {
            generateCaptcha(req, resp);
        } else {
            doPost(req, resp);
        }
    }

    private void generateCaptcha(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String captcha = generateCaptcha();
        writeCaptchaImage(captcha, req, resp);
        req.getSession().setAttribute("captcha", captcha);
    }

    private String generateCaptcha() {
        Random random = new Random();
        StringBuilder captchaStringBuilder = new StringBuilder();
        for (int i = 0; i < 6; i++) {
            captchaStringBuilder.append(random.nextInt(10));
        }
        return captchaStringBuilder.toString();
    }

    private void writeCaptchaImage(String captcha, HttpServletRequest request, HttpServletResponse response) throws IOException {
        int width = 160;
        int height = 40;
        response.setContentType("image/jpeg");

        BufferedImage bufferedImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        Graphics2D g = bufferedImage.createGraphics();

        // 设置背景色
        g.setColor(Color.WHITE);
        g.fillRect(0, 0, width, height);

        // 设置字体
        Font font = new Font("Fixedsys", Font.BOLD, 20);
        g.setFont(font);

        Random random = new Random();

        // 添加干扰线
        for (int i = 0; i < 20; i++) {
            int xs = random.nextInt(width);
            int ys = random.nextInt(height);
            int xe = xs + random.nextInt(width / 8);
            int ye = ys + random.nextInt(height / 8);
            g.setColor(getRandomColor());
            g.drawLine(xs, ys, xe, ye);
        }

        // 添加验证码
        for (int i = 0; i < captcha.length(); i++) {
            String strRand = String.valueOf(captcha.charAt(i));
            g.setColor(getRandomColor());
            g.drawString(strRand, 15 * i + 6, 24);
        }

        g.dispose();

        ByteArrayOutputStream bao = new ByteArrayOutputStream();
        ImageIO.write(bufferedImage, "jpg", bao);
        bao.writeTo(response.getOutputStream());
    }

    private Color getRandomColor() {
        Random random = new Random();
        float r = random.nextFloat();
        float g = random.nextFloat();
        float b = random.nextFloat();
        return new Color(r, g, b);
    }
}