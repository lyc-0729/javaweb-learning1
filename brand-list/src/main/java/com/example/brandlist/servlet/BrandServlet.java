package com.example.brandlist.servlet;


import com.alibaba.fastjson.JSON;
import com.example.brandlist.entity.Brand;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

/**
 * @author BrandServlet
 * @description: TODO
 * @date 2024/11/9 16:12
 */

@WebServlet(name = "BrandServlet", urlPatterns = {"/brand", "/brand/*"})
public class BrandServlet extends HttpServlet {
    private List<Brand> getBrandList() {
        return List.of(
                Brand.builder().id(101).companyName("apple").brandName("iPhone16").description("苹果-iPhone16").ordered(1).build(),
                Brand.builder().id(102).companyName("huawei").brandName("mate60").description("华为-mate60").ordered(2).build(),
                Brand.builder().id(103).companyName("benz").brandName("mercedes").description("奔驰-梅赛德斯").ordered(3).build());
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("application/json; charset=utf-8");
        ServletContext servletContext = req.getServletContext();
        Object brands = servletContext.getAttribute("brands");
        List<Brand> brandList = null;
        if (brands instanceof List) {
            brandList = (List<Brand>) brands;
        }
        brandList = brandList == null ? getBrandList() : brandList;
        servletContext.setAttribute("brands", brandList);
        req.getServletContext().setAttribute("brands", brandList);
        String jsonString = JSON.toJSONString(brandList);
        resp.getWriter().write(jsonString);
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String pathInfo = req.getPathInfo();
        if (pathInfo == null || pathInfo.isEmpty()) {
            resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "Brand ID is required");
            return;
        }
        int id = Integer.parseInt(pathInfo.substring(1)); // 从路径中提取ID
        ServletContext servletContext = req.getServletContext();
        List<Brand> brandList = (List<Brand>) servletContext.getAttribute("brands");
        if (brandList != null) {
            brandList.removeIf(brand -> brand.getId() == id);
            servletContext.setAttribute("brands", brandList);
            resp.setStatus(HttpServletResponse.SC_OK);
        } else {
            resp.sendError(HttpServletResponse.SC_NOT_FOUND, "Brand list not found");
        }
    }
}
