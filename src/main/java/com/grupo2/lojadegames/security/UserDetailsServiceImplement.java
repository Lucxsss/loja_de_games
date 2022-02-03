package com.grupo2.lojadegames.security;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.grupo2.lojadegames.model.Usuario;
import com.grupo2.lojadegames.repository.UsuarioRepository;

@Service
public class UserDetailsServiceImplement implements UserDetailsService {

	@Autowired
	private UsuarioRepository repository;

	@Override
	public UserDetailslmp loadUserByUsername(String email) throws UsernameNotFoundException {
		Optional<Usuario> user = repository.findByEmail(email);
		user.orElseThrow(() -> new UsernameNotFoundException(email + "Not_Found"));

		return user.map(UserDetailslmp::new).get();
	}

}
