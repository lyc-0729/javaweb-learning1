package com.example.brandlist.entity;

import jakarta.servlet.annotation.WebServlet;
import jdk.jfr.DataAmount;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author 27596
 * @description: TODO
 * @date 2024/11/9 16:08
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Brand {
    private Integer id;
    private String companyName;
    private String brandName;
    private Integer ordered;
    private String description;
}