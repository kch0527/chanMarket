package com.example.market.filter;

import javax.servlet.*;
import java.io.IOException;

//필터 인터페이스를 구현,등록하면 서블릿 컨테이너가 필터를 싱글톤 객체로 생성하고 관리
public interface Filter {
    //필터 초기화 메서드, 서블릿 컨테이너가 생성될 때 호출
    public default void init(FilterConfig filterConfig) throws ServletException{}

    //고객의 요청이 올 때 마다 해당 메서드 호출, 필터의 로직 구현
    public void doFilter(ServletRequest request, ServletResponse response,
                         FilterChain chain) throws IOException, ServletException;

    //필터 종료 메서드, 서블릿 컨테이너가 종료될 때 호출출
   public default void destroy(){}
}
