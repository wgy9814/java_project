package com.itheima.web.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import java.io.IOException;


//@WebFilter("/hello.jsp")
@WebFilter("/*")
public class FilterDemo2 implements Filter {


    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {

        //1. 放行前，对 request数据进行处理
        System.out.println("2.FilterDemo...");

        //放行
        chain.doFilter(request,response);
        //2. 放行后，对Response 数据进行处理
        System.out.println("4.FilterDemo...");
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }



    @Override
    public void destroy() {

    }
}
