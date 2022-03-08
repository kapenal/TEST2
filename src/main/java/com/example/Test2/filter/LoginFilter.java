package com.example.Test2.filter;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import ch.qos.logback.core.recovery.ResilientSyslogOutputStream;

@WebFilter("/LOGIN/*")
public class LoginFilter extends HttpFilter implements Filter {
	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		HttpSession session = ((HttpServletRequest)request).getSession();
		// session loginId가 null이면 로그인이 안된 상태이기때문에 Page.jsp 이동 필터가 실행되어서 로그인화면으로 이동
		if(session.getAttribute("loginId") == null) {
			((HttpServletResponse)response).sendRedirect(((HttpServletRequest)request).getContextPath()+"/NotLogin/getLogin");
			return;
		}
		
		super.doFilter(request, response, chain);
	}
}