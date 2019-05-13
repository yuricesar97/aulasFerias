package com.yuri.aulas.security;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

public class JWTAuthorizationFilter extends BasicAuthenticationFilter{

	
	private JWTUtil jwtUtil;
	private UserDetailsService userDetailsService;
	
	
	public JWTAuthorizationFilter(AuthenticationManager authenticationManager,JWTUtil jwtUtil, UserDetailsService userDetailsService) {
		super(authenticationManager);
		
		this.jwtUtil = jwtUtil;
		this.userDetailsService = userDetailsService; // necessario para analisar se o token é valido para permissão
	}
	
	@Override
	protected void doFilterInternal (HttpServletRequest request, HttpServletResponse response,
			                         FilterChain chain) throws IOException, ServletException { // metado padrão que executa filtra antes de deixar reequisição continuar
		
		String header = request.getHeader("Authorization");
		
		if(header != null && header.startsWith("Bearer ")) { //startsWith verifica se começa com a letra especificada
		  UsernamePasswordAuthenticationToken auth = getAuthentication(header.substring(7)); // substring retira os 7 primerios campos do header
		  if(auth != null ) {
			  SecurityContextHolder.getContext().setAuthentication(auth); // libera autorização do token
		  }
		
		}
		chain.doFilter(request, response); // depois de fazer a autorização continuar o fluxo
	}

	private UsernamePasswordAuthenticationToken getAuthentication(String token) {
		
		if(jwtUtil.tokenValido(token)) {
			String username = jwtUtil.getUsername(token);
			UserDetails user = userDetailsService.loadUserByUsername(username); // buscando no banco de dados
			return new UsernamePasswordAuthenticationToken(username, null, user.getAuthorities());
		}
		
		return null;
	}

}
