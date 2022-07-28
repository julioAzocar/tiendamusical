package com.debpredator.tiendamusicalweb.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.debpredator.tiendamusicalweb.session.SessionBean;
import com.debpredator.tiendamusicalweb.utils.CommonsUtils;
import com.devpredator.tiendamusicalentities.entities.Persona;


//clase implementa filtro para sesion de usuario

@WebFilter
public class LoginFilter implements Filter {

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		// TODO Auto-generated method stub

	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
	
		HttpServletRequest httpServletRequest = (HttpServletRequest) request;
		HttpServletResponse httpServletResponse = (HttpServletResponse) response;
		HttpSession httpSession = httpServletRequest.getSession();
		
		if (httpSession == null || httpSession.getAttribute("sessionBean") == null) {
			httpServletResponse.sendRedirect(httpServletRequest.getContextPath() + "/login.xhtml");
		}else {
			if (httpSession.getAttribute("sessionBean") != null) {
				SessionBean sessionBean = (SessionBean) httpSession.getAttribute("sessionBean"); 
				Persona persona = sessionBean.getPersona();
				
				if(persona == null) {
					httpServletResponse.sendRedirect(httpServletRequest.getContextPath() + "/login.xhtml");
					return;
				}
			}
			
			chain.doFilter(httpServletRequest, httpServletResponse);
		}
		
		

	}

	@Override
	public void destroy() {
		// TODO Auto-generated method stub

	}

}
