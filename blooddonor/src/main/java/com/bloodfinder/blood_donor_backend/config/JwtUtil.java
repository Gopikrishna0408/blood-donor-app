package com.bloodfinder.blood_donor_backend.config;

import org.springframework.stereotype.Component;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import java.security.Key;
import java.util.Date;

@Component
public class JwtUtil {
	
	private final String SECRET="mysecretkeymysecretkeymysecretkey12345";
	
	private final Key key = Keys.hmacShaKeyFor(SECRET.getBytes());
	
	public String generateToken(String email) {
		
		return Jwts.builder().setSubject(email)
				.setIssuedAt(new Date())
				.setExpiration(new Date(System.currentTimeMillis()+1000*60*5))
				.signWith(key,SignatureAlgorithm.HS256)
				.compact();
	}
	
	public String extractUserName(String token) {
		return Jwts.parserBuilder()
				.setSigningKey(key)
				.build()
				.parseClaimsJws(token)
				.getBody()
				.getSubject();
	}
	
	public boolean validate(String token) {
        try {
            Jwts.parserBuilder()
                    .setSigningKey(key)
                    .build()
                    .parseClaimsJws(token);
            return true;
        } catch (Exception e) {
            return false;
        }

	}

}
