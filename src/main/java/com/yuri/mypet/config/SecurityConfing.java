package com.yuri.mypet.config;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import com.yuri.mypet.security.JWTAuthenticationFilter;
import com.yuri.mypet.security.JWTAuthorizationFilter;
import com.yuri.mypet.security.JWTUtil;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfing extends WebSecurityConfigurerAdapter {

	@Autowired
	private Environment env; // para funcionar o H2
	
	@Autowired
	private UserDetailsService userDetailsService;
	
	@Autowired
	private JWTUtil jwtUtil;
	
	private static final String[] PUBLIC_MATCHERS = {

			"/h2-console/**",

	};
	private static final String[] PUBLIC_MATCHERS_GET = {

			"/produtos/**", "/categorias/**","/pessoafisica/**",
			"/pessoajuridica/**"

	};
	private static final String[] PUBLIC_MATCHERS_POST = {

			"/pessoafisica/**","/pessoajuridica/**"

	};
	private static final String[] PUBLIC_MATCHERS_PUT = {

			"/pessoafisica/**","/pessoajuridica/**"

	};
	private static final String[] PUBLIC_MATCHERS_DELETE = {

			"/pessoafisica/**","/pessoajuridica/**"

	};

	@Override
	protected void configure(HttpSecurity http) throws Exception {

		if (Arrays.asList(env.getActiveProfiles()).contains("test")) {
			http.headers().frameOptions().disable();
		}

		http.cors().and().csrf().disable();
		http.authorizeRequests()
		.antMatchers(HttpMethod.DELETE,PUBLIC_MATCHERS_DELETE).permitAll()
		.antMatchers(HttpMethod.PUT,PUBLIC_MATCHERS_PUT).permitAll()
		.antMatchers(HttpMethod.POST,PUBLIC_MATCHERS_POST).permitAll()
     	.antMatchers(HttpMethod.GET,PUBLIC_MATCHERS_GET).permitAll()
		.antMatchers(PUBLIC_MATCHERS).permitAll()
		.anyRequest().authenticated();
		http.addFilter(new JWTAuthorizationFilter(authenticationManager(), jwtUtil,userDetailsService));
		http.addFilter(new JWTAuthenticationFilter(authenticationManager(), jwtUtil));
		http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS); // para não criar sessão de
																							// usuario
	}
	@Override
	public void configure(AuthenticationManagerBuilder auth) throws Exception{
		auth.userDetailsService(userDetailsService).passwordEncoder(bCryptPasswordEncoder());
	} // para diver qual o userDetailService que usamos e qual algaritmo de decodificação usamos
	
	
	
	

	@Bean
	CorsConfigurationSource corsConfigurationSource() {
		final UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
		source.registerCorsConfiguration("/**", new CorsConfiguration().applyPermitDefaultValues());
		return source; // permitindo o acesso por multiplas por configurações basicas
	}
	
	@Bean
	public BCryptPasswordEncoder bCryptPasswordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
}
