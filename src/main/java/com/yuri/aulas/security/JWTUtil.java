package com.yuri.aulas.security;

import java.util.Date;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

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
}
