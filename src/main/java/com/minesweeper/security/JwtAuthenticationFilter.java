//package com.minesweeper.security;
//
//import java.io.IOException;
//import java.util.ArrayList;
//
//import javax.servlet.FilterChain;
//import javax.servlet.ServletException;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
//import org.springframework.security.core.context.SecurityContextHolder;
//import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
//import org.springframework.web.filter.OncePerRequestFilter;
//
//import io.jsonwebtoken.ExpiredJwtException;
//
//public class JwtAuthenticationFilter extends OncePerRequestFilter {
//
//    @Autowired
//    private JwtTokenProvider jwtTokenProvider;
//
//    private static final String TOKEN_PREFIX = "Bearer ";
//    private static final String HEADER_STRING = "Authorization";
//
//    @Override
//    protected void doFilterInternal(HttpServletRequest req, HttpServletResponse res, FilterChain chain) throws IOException, ServletException {
//        String header = req.getHeader(HEADER_STRING);
//        String authToken = null;
//        String userName = null;
//        if (header != null && header.startsWith(TOKEN_PREFIX)) {
//            authToken = header.replace(TOKEN_PREFIX,"");
//            try {
//                jwtTokenProvider.validateToken(authToken);
//                userName = jwtTokenProvider.getUsernameFromToken(authToken);
//            } catch (IllegalArgumentException e) {
//                logger.error("An error occurred during the validation of the token", e);
//            } catch (ExpiredJwtException e) {
//                logger.warn("The token is expired and not valid anymore", e);
//            } catch (Exception e){
//                logger.error("The provided token is invalid", e);
//            }
//        } else {
//            logger.warn("couldn't find bearer string, will ignore the header");
//        }
//        if (userName != null && SecurityContextHolder.getContext().getAuthentication() == null) {
//            UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(userName, null, new ArrayList<>());
//            authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(req));
//            logger.info("authenticated user " + userName + ", setting security context");
//            SecurityContextHolder.getContext().setAuthentication(authentication);
//        }
//        chain.doFilter(req, res);
//    }
//
//}
