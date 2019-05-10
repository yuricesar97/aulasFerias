package com.yuri.aulas.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.yuri.aulas.domain.Cliente;
import com.yuri.aulas.repositories.ClienteRepositoty;
import com.yuri.aulas.security.UserSS;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {  // permite busca pelo nome do usuario 

	@Autowired
	private ClienteRepositoty clienteRepositoty;
	
	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		Cliente cli = clienteRepositoty.findByEmail(email);
		
		if(cli == null) {
			throw new UsernameNotFoundException(email);	
		}
		
	
		
		return new UserSS(cli.getId(), cli.getEmail(), cli.getSenha(), cli.getPerfis());
	}
	


}
