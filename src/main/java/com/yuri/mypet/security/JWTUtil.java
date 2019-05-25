package com.yuri.mypet.security;

import java.util.Date;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Component // para ser enjetada em outras classes como componente
public class JWTUtil {

	@Value("${jwt.secret}") // pegando valor do app.properties e jogando na variavel
	private String secret;

	@Value("${jwt.expiration}")
	private Long expiration;

	public String generateToken(String username) {
		return Jwts.builder() // gera o token
				.setSubject(username) // pega o usuario
				.setExpiration(new Date(System.currentTimeMillis() + expiration)) // pega o horario atual + a expiração do token para validar vencimento do token
																					
																				
				.signWith(SignatureAlgorithm.HS512, secret.getBytes()).compact(); // como assinar o token, com o algoritmo que utilazamos(HS512) e o secredo(SECRET).
																				
																				

	}

	public boolean tokenValido(String token) {
		Claims claims = getClaims(token); // armaneza as reinvidicações do token (no caso seria usuario e tempo de expiração)
		if(claims != null ) {
			String username = claims.getSubject(); // retorna um usuario
			Date expirationDate = claims.getExpiration(); // data de expiração
			Date now = new Date(System.currentTimeMillis()); //data atual
			
			if(username != null && expirationDate != null && now.before(expirationDate)) {
				return true; // token valido
			}
		}
	    return false;
	}
	
	public String getUsername (String token) {
		Claims claims = getClaims(token); // armaneza as reinvidicações do token (no caso seria usuario e tempo de expiração)
		if(claims != null ) {
	  return claims.getSubject(); // retorna um usuario
		}
		return null; // usuario null
	}
	private Claims getClaims(String token) {
		try {
			return Jwts.parser().setSigningKey(secret.getBytes()).parseClaimsJws(token).getBody(); // recupera os claims a partir de uma  token
																									
		} catch (Exception e) {
			return null; // caso retorne um token invalido
		}
	}
}
