package io.isamm.projectsmanagement.utils;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.InvalidPathException;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.Date;

import org.springframework.util.ResourceUtils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.SignatureException;
import io.jsonwebtoken.UnsupportedJwtException;


public class JwtUtil {
	
	private JwtUtil() {
		
	}
	
	public static boolean verifyToken(String token) {
		
		try {
			Jwts.parser().setSigningKey(getSigningKey()).parse(token);
			return true;
		} catch (MalformedJwtException | SignatureException | ExpiredJwtException | IllegalArgumentException ex) {
			return false;
		}

	}
	
	public static String getSubject(String token) {
		
		try {
        	Jws<Claims> claims = Jwts.parser().setSigningKey(getSigningKey()).parseClaimsJws(token);
        	return claims.getBody().getSubject();
		} catch (UnsupportedJwtException | MalformedJwtException | SignatureException | ExpiredJwtException | IllegalArgumentException ex) {
			return null;
		}
        
	}
	
	public static String createToken(String email) {
		
        return Jwts.builder()
        		   .setIssuer("projects-management")
                   .setSubject(email)
                   .setExpiration(Date.from(LocalDateTime.now().plusMinutes(24*60).toInstant(ZoneOffset.UTC)))
                   .setIssuedAt(Date.from(LocalDateTime.now().toInstant(ZoneOffset.UTC)))
                   .signWith(SignatureAlgorithm.HS512, getSigningKey())
                   .compact();
        
	}
	
	private static byte[] getSigningKey() {
		
		try {
			return Files.readAllBytes(ResourceUtils.getFile("classpath:jwt.key").toPath());
		} catch (IOException | InvalidPathException e) {
			e.printStackTrace();
			throw new RuntimeException();
		}
		
	}

}
