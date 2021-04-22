package com.example.demo;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.http.HttpFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class MyFilter extends HttpFilter {
    public void init(FilterConfig config) throws ServletException{}

    @Override
    public void doFilter(HttpServletRequest servletRequest, HttpServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        System.out.println("Hello from filter");
        servletResponse.addHeader("Access-Control-Allow-Origin", "*");
        servletResponse.addHeader("Access-Control-Allow-Headers", "Content-Type");
        servletResponse.addHeader("Allow", "GET, POST, HEAD, OPTIONS");
        filterChain.doFilter(servletRequest, servletResponse);
    }

    public void destroy(){

    }
}
