package com.example.class03response.pratice;


import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random;
import java.util.concurrent.Callable;

/**
 * @author
 * @description: TODO
 * @date 2024/9/28 16:07
 */

@WebServlet("/verifyCode")
public class VerifyCode extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int width =160;
        int height =45;
        //1.创建验证码图片对象
        BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        //2.创建画笔对象
        Graphics g = image.getGraphics();
        //设置画笔颜色
        g.setColor(Color.pink);
        g.fillRect(0, 0, width, height);
        String str ="cr623evt6gyuegfw";
        //生成随机角标
        Random random = new Random();
        for(int i=0;i<=5;i++){
            int index = random.nextInt(str.length());
            char c=str.charAt(index);
            //获取随机字符
            Color color = new Color(random.nextInt(256),random.nextInt(256),random.nextInt(256));
            g.setColor(color);
            //填写验证码
            g.drawString(String.valueOf(c), width / 5 *  i, height / 2);
        }

        Random random1 = new Random();
        for (int i = 0; i <50; i++) {
            int x = random1.nextInt(width);
            int y = random1.nextInt(height);
            g.setColor(new Color(random.nextInt(256), random.nextInt(256), random.nextInt(256)));
            g.drawRect(x, y, 1, 1);
        }

        //生成干扰线
        for(int i=0;i<=10;i++){
            int x1= random.nextInt(width);
            int x2= random.nextInt(width);
            int y1= random.nextInt(height);
            int y2= random.nextInt(height);
            Color color = new Color(random.nextInt(256),random.nextInt(256),random.nextInt(256));
            g.setColor(color.yellow);
            g.drawLine(x1,y1,x1,y2);
                }
        ImageIO.write(image,"jpg",resp.getOutputStream());
            }
        }