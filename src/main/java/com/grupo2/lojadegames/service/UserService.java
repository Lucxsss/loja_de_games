package com.grupo2.lojadegames.service;

import java.nio.charset.Charset;
import java.util.Optional;

import org.apache.commons.codec.binary.Base64;
import org.generation.blogPessoal.model.Usuario;
import org.generation.blogPessoal.model.UsuarioLogin;
import org.generation.blogPessoal.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

		@Autowired
		private UsuarioRepository repository;
		
		public Usuario CadastrarUsuario(Usuario usuario) {
			BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
			
			String senhaEncoder = encoder.encode(usuario.getPassword());
			usuario.setPassword(senhaEncoder);
			return repository.save(usuario);
		}
		
		public Optional<UsuarioLogin> Logar(Optional<UsuarioLogin> user){
			BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
			Optional<Usuario> usuario = repository.findByUsername(user.get().getUser());
			
			if(usuario.isPresent()) {
				if(encoder.matches(user.get().getPassword(), usuario.get().getPassword())) {
					String auth = user.get().getUser() + ":" + user.get().getPassword();
							byte[] encodeAuth = Base64.encodeBase64(auth.getBytes(Charset.forName("US-ASCII")));
							String authHeader = "Basic" + new String(encodeAuth);
							
							user.get().setToken(authHeader);
							user.get().setName(usuario.get().getName());
							
							return user;
		}
	}	

							return null;
		}
	}
