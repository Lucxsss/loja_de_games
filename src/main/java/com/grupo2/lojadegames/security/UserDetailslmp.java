package com.grupo2.lojadegames.security;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.grupo2.lojadegames.model.Usuario;

public class UserDetailslmp implements UserDetails {

	private static final long serialVersionUID = 1L;

	private String email;
	private String password;

	public UserDetailslmp(Usuario user) {
		this.email = user.getEmail();
		this.password = user.getPassword();
		
		/* private Map<String, User> roles = new HashMap<>();

		    @PostConstruct
		    public void init() {
		        roles.put("admin", new User("admin", "{noop}admin1", getAuthority("ROLE_ADMIN")));
		        roles.put("user", new User("user", "{noop}user1", getAuthority("ROLE_USER")));
		    }

		    @Override
		    public UserDetails loadUserByUsername(String username) {
		        return roles.get(username);
		    }

		    private List<GrantedAuthority> getAuthority(String role) {
		        return Collections.singletonList(new SimpleGrantedAuthority(role));
		    }
		}*/
	}

	public UserDetailslmp() {
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getPassword() {
		// TODO Auto-generated method stubO
		return password;
	}

	@Override
	public String getUsername() {
		// TODO Auto-generated method stub
		return email;
	}

	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return true;
	}

}
