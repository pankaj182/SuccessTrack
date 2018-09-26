package com.sf.authentication;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


public class NoCacheFilter implements Filter {

    public NoCacheFilter() {
    }
	public void destroy() {
	}
	public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException, ServletException {
		
				HttpServletRequest request = (HttpServletRequest) req;
				HttpServletResponse response = (HttpServletResponse) res;
				response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate"); // HTTP 1.1.
				response.setHeader("Pragma", "no-cache"); // HTTP 1.0.
				response.setDateHeader("Expires", 0); // Proxies.
		
				HttpSession session = request.getSession(false);
				if (session.getAttribute("user") == null) {   //checking whether the session exists
		            response.sendRedirect("index.jsp");
		        } else {
		            // pass the request along the filter chain
		            chain.doFilter(request, response);
		        }
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

}
