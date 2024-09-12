package com.springmaniya.springmaniya.config;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.crypto.SecretKey;
import java.io.IOException;
import java.util.List;


public class JwtTokenValidator extends OncePerRequestFilter {


//    @Autowired
//    Authentication authentication;
    @Override
    // This class extends OncePerRequestFilter and handles JWT token processing
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {

        // Retrieve the JWT token from the request header
        String jwt = request.getHeader(JwtConstant.JWT_HEADER);

        // Check if the JWT is present
        if (jwt != null) {
            // Remove the "Bearer " prefix from the JWT string
            jwt = jwt.substring(7);

            try {
                // Create a SecretKey object using the secret key for HMAC SHA encryption
                SecretKey key = Keys.hmacShaKeyFor(JwtConstant.SECRET_KEY.getBytes());

                // Parse the JWT token to extract claims (data embedded in the token)
                Claims claims = Jwts.parser()         // Create a JWT parser
                        .setSigningKey(key)      // Set the signing key used to sign the JWT
                        .build()                 // Build the parser
                        .parseClaimsJws(jwt)     // Parse the JWT and get the claims
                        .getBody();              // Extract the claims from the JWT token

                // Extract the email and authorities (roles/permissions) from the claims
                String email = String.valueOf(claims.get("email"));
                String authorities = String.valueOf(claims.get("authorities"));

                // Convert the authorities (comma-separated roles/permissions) into GrantedAuthority objects
                List<GrantedAuthority> auths = AuthorityUtils.commaSeparatedStringToAuthorityList(authorities);

                // Create an Authentication object with the user's email and authorities
Authentication authentication  = new UsernamePasswordAuthenticationToken(email, null, auths);

                // Set the Authentication object in the SecurityContext to make the user authenticated
                SecurityContextHolder.getContext().setAuthentication(authentication);
            } catch (Exception e) {
                // If token parsing fails or any other issue occurs, handle the exception
                throw  new BadCredentialsException("invalid token...");

            }
        }

        // Continue the filter chain to the next filter
        filterChain.doFilter(request, response);
    }

}
