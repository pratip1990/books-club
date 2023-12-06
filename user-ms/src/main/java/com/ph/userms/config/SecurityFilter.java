/**
 * 
 */
package com.ph.userms.config;

import java.io.IOException;

import org.springframework.web.filter.OncePerRequestFilter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;

/**
 * 
 */
@Slf4j
public class SecurityFilter extends OncePerRequestFilter {

	private static final String API_KEY = "AXDOPS32153XSDS";

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {

		if (!isValidateRequests(request)) {
			response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
			return;
		}

		log.info("Security Filter Invoked...");
		filterChain.doFilter(request, response);

	}

	private boolean isValidateRequests(HttpServletRequest request) {
		String requestUrI = request.getRequestURI();
		log.info("requestUrI :: {}", requestUrI);
		// app-name, api-key
		String headerAppName = request.getHeader("app-name");
		String headerAppKey = request.getHeader("app-Key");

		return (("GATEWAY-MS".equalsIgnoreCase(headerAppName) && API_KEY.equalsIgnoreCase(headerAppKey))
				|| (requestUrI.contains("/auth/verify") || requestUrI.contains("/auth/get-user-username")));
			
	}

}
