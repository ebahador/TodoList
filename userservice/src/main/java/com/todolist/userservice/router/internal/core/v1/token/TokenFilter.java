package com.todolist.userservice.router.internal.core.v1.token;

import com.todolist.userservice.database.Dao;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import org.jetbrains.annotations.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

@Component
public class TokenFilter extends OncePerRequestFilter {

  private final Dao dao;
  private final Logger logger;

  public TokenFilter(Dao dao) {
    this.dao = dao;
    this.logger = LoggerFactory.getLogger(this.getClass());
  }

  @Override
  protected void doFilterInternal(
      HttpServletRequest request,
      @NotNull HttpServletResponse response,
      @NotNull FilterChain filterChain)
      throws ServletException, IOException {

    String requestEndpoint = request.getRequestURI();

    // Exclude this endpoint for Auth check
    if (requestEndpoint.equals("/internal/core/v1/token")) {
      filterChain.doFilter(request, response);
      return;
    }

    String authHeader = request.getHeader("Authorization");

    // Check if Authorization header is missing or doesn't start with "Bearer"
    if (authHeader == null || !authHeader.startsWith("Bearer ")) {
      response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
      logger.warn("Missing or invalid Authorization header");
      return; // Ensure no further processing occurs
    }

    // Extract the token
    String token = authHeader.substring(7);

    // Validate the token
    try {
      if (!dao.isTokenValid(token)) {
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        logger.warn("Invalid or expired token");
        return;
      }
    } catch (Exception e) {
      logger.error("Error validating token: {}", e.getMessage());
      response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
      return;
    }

    // If everything is valid, proceed with the filter chain
    filterChain.doFilter(request, response);
  }
}
