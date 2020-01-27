package io.isamm.projectsmanagement.components;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.core.annotation.Order;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import io.isamm.projectsmanagement.utils.JwtUtil;


@Component
@Order(2)
public class JwtAuthenticationFilter extends OncePerRequestFilter {
	
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
		
		AuthenticatedToken authenticatedToken = null;
		SecurityContextHolder.clearContext();
		
		String authorization = request.getHeader("Authorization");
	    if (authorization != null) {
	    	String token = authorization.replaceAll("Bearer ", "");
	        if (JwtUtil.verifyToken(token))
	        	authenticatedToken = new AuthenticatedToken(token);
	    }
	    
	    SecurityContextHolder.getContext().setAuthentication(authenticatedToken);
	    filterChain.doFilter(request, response);
		
	}

}
