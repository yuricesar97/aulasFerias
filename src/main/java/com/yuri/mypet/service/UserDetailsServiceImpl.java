package com.yuri.mypet.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;


import com.yuri.mypet.domain.PessoaFisica;

import com.yuri.mypet.repositories.PessoaFisicaRepository;
import com.yuri.mypet.security.UserSS;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {  // permite busca pelo nome do usuario 

	@Autowired
	private PessoaFisicaRepository clienteRepositoty;
	
	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		PessoaFisica cli = clienteRepositoty.findByEmail(email);
		
		if(cli == null) {
			throw new UsernameNotFoundException(email);	
		}
		
	
		
		return new UserSS(cli.getId(), cli.getEmail(), cli.getSenha(), cli.getPerfis());
	}
	


}
