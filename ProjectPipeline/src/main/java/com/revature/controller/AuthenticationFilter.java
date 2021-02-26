package com.revature.controller;

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

/**
 * Servlet Filter implementation class FrontendAuthenticationFilter
 */

//@WebFilter("/*")
public class AuthenticationFilter implements Filter {
    private static final String[] resShared = {
            "images", "scripts", "styles"
    };

    /**
     * Default constructor. 
     */
    public AuthenticationFilter() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
		// TODO Auto-generated method stub
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		// TODO Auto-generated method stub
		// place your code here
		HttpServletRequest httpRequest = (HttpServletRequest) request;
		HttpServletResponse httpResponse = (HttpServletResponse) response;		
		HttpSession session = httpRequest.getSession(false);
		
		final String URI = httpRequest.getRequestURI();
		final String RESOURCE = URI.replace("/ProjectPipeline/", "");
		
		System.out.println("resource in filter :"+ RESOURCE);
		
		if(session.getAttribute("employee") != null && RESOURCE.startsWith("webpages/employee")){
			//httpRequest.getRequestDispatcher("/webpages/employee/").forward(request, response);
			//pass the request along the filter chain
			chain.doFilter(request, response);
		}else if(session.getAttribute("manager") != null && RESOURCE.startsWith("webpages/manager")) {
			//httpRequest.getRequestDispatcher("/webpages/manager/").forward(request, response);
			//pass the request along the filter chain
			chain.doFilter(request, response);			
		}else if(RESOURCE.equals("index.html")){
			httpResponse.sendRedirect("/ProjectPipeline/index.html");
		}else {
			httpResponse.setStatus(404);
		}
		
		/*
		if(session == null ||RESOURCE.equals("index.html")) {
			httpResponse.sendRedirect("/ProjectPipeline/index.html");
		}else if(session.getAttribute("employee") != null ){
			  httpRequest.getRequestDispatcher("/webpages/employee/").forward(request, response);
			  
		}else if(session.getAttribute("manager") != null) {
			  httpRequest.getRequestDispatcher("/webpages/manager/").forward(request, response);			
		}		
		// pass the request along the filter chain
		else chain.doFilter(request, response);	
		*/
	}
	
    private boolean isResShared(String res) {
        for (String share : resShared) {
    		System.out.println("resource: " +res+" in filter :"+ share);        	
        	if(res.startsWith(share))
        		return true;
        }
        return false;
    }
    
	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

}
