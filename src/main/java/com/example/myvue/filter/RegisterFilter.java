package com.example.myvue.filter;

import org.springframework.core.annotation.Order;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
@Order(1)
//重点
@WebFilter(filterName = "registrFilter", urlPatterns = "/calCenter")
public class RegisterFilter  extends HttpServlet implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        System.out.println("验证是否注册！");
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        // 将请求转换成HttpServletRequest 请求
        HttpServletRequest req = (HttpServletRequest) servletRequest;
        HttpServletResponse resp = (HttpServletResponse) servletResponse;
        String tokenStr = (String) req.getParameter("token");

            // Filter 只是链式处理，请求依然转发到目的地址。
            filterChain.doFilter(req, resp);

    }
}
