//package com.minesweeper.security;
//
//import io.jsonwebtoken.Claims;
//import io.jsonwebtoken.Jwts;
//import io.jsonwebtoken.SignatureAlgorithm;
//import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
//import org.springframework.security.core.Authentication;
//import org.springframework.security.core.GrantedAuthority;
//import org.springframework.stereotype.Component;
//
//import java.io.Serializable;
//import java.util.ArrayList;
//import java.util.Base64;
//import java.util.Date;
//import java.util.function.Function;
//import java.util.stream.Collectors;
//
//@Component
//public class JwtTokenProvider implements Serializable {
//
//    private static final String SIGNING_KEY = "myPrivateKeyWasTooShortForTheAlgorithm";
//    private static final String AUTHORITIES_KEY = "roles";
//    private static final long ACCESS_TOKEN_VALIDITY_SECONDS = 5*60*60;
//
//    public String getUsernameFromToken(String token) {
//        return getClaimFromToken(token, Claims::getSubject);
//    }
//
//    public Date getExpirationDateFromToken(String token) {
//        return getClaimFromToken(token, Claims::getExpiration);
//    }
//
//    public String getIssuerFromToken(String token) {
//        return getClaimFromToken(token, Claims::getIssuer);
//    }
//
//    public <T> T getClaimFromToken(String token, Function<Claims, T> claimsResolver) {
//        final Claims claims = getAllClaimsFromToken(token);
//        return claimsResolver.apply(claims);
//    }
//
//    private Claims getAllClaimsFromToken(String token) {
//        return Jwts.parser()
//                .setSigningKey(Base64.getEncoder().encode(SIGNING_KEY.getBytes()))
//                .parseClaimsJws(token)
//                .getBody();
//    }
//
//    private Boolean isTokenExpired(String token) {
//        final Date expiration = getExpirationDateFromToken(token);
//        return expiration.before(new Date());
//    }
//
//    public Boolean validateToken(String token) {
//        final String username = getUsernameFromToken(token);
//        final String issuer = getIssuerFromToken(token);
//        return username != null && issuer.equals("localhost:8090") && !isTokenExpired(token);
//    }
//
//    public UsernamePasswordAuthenticationToken getAuthentication(final String token) {
//        return new UsernamePasswordAuthenticationToken(getUsernameFromToken(token), null, new ArrayList<>());
//
//
////        final Jws<Claims> claimsJws = jwtParser.parseClaimsJws(token);
////        final Claims claims = claimsJws.getBody();
////        final Collection<? extends GrantedAuthority> authorities =
////                Arrays.stream(claims.get(AUTHORITIES_KEY).toString().split(","))
////                        .map(SimpleGrantedAuthority::new)
////                        .collect(Collectors.toList());
//
////        return new UsernamePasswordAuthenticationToken(userDetails, "", authorities);
//    }
//
//}
