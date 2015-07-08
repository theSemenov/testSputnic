package ru.sputnic.test.humanmanager;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

public class CharacterEncodingFilter implements Filter {
	
	@Override
	  public void doFilter(ServletRequest request, ServletResponse response,
	      FilterChain filterChain) throws IOException, ServletException {
	    request.setCharacterEncoding("UTF-8");
	    filterChain.doFilter(request, response);
	  }

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		
	}
}
