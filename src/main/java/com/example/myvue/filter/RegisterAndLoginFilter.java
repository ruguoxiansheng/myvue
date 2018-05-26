package com.example.myvue.filter;

import com.alibaba.fastjson.JSONObject;
import com.example.myvue.daoService.UserPersonObjectDaoMapper;
import com.example.myvue.model.UserPersonObject;
import com.example.myvue.myException.DataBaseException;
import com.example.myvue.myException.McException;
import com.example.myvue.myException.Result;
import org.springframework.core.annotation.Order;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.OutputStream;

//@Order(1)
////重点
//@WebFilter(filterName = "registrAndLoginFilter", urlPatterns = "/app/*")
public class RegisterAndLoginFilter extends HttpServlet implements Filter {

    @Resource
    private UserPersonObjectDaoMapper userPersonObjectDaoMapper;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        System.out.println("验证是否注册！");
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        // 将请求转换成HttpServletRequest 请求
        HttpServletRequest req = (HttpServletRequest) servletRequest;
        HttpServletResponse resp = (HttpServletResponse) servletResponse;
        String userId = req.getParameter("userId");
        System.out.println("---------aa-------");
        // 没有，说明需要登陆
        if (userId==null || "".equals(userId) || "null".equals(userId)) {
            //未登陆
            Result<JSONObject> error = new Result();
            error.setStatus("600");
            error.setMsg("未登陆");
           OutputStream out = resp.getOutputStream();
            resp.setHeader("code","700");
            return;
        }else {
            try {
               UserPersonObject upoRecord = userPersonObjectDaoMapper.selectByPrimaryKey(userId);
               if (upoRecord == null) {
                   //未注册
                   resp.setStatus(503);
                   return;
               }
               if (upoRecord.getStatus() == 1) {
                   // 用户已经登陆
                   resp.setStatus(504);
                   return;
               }
                filterChain.doFilter(servletRequest,servletResponse);
            } catch (DataBaseException e) {
                // 数据库查询异常
                resp.setStatus(505);
                return;
            }
        }

    }
}
