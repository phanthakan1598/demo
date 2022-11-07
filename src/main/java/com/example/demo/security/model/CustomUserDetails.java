package com.example.demo.security.model;

import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

public class CustomUserDetails implements UserDetails{

	private static final long serialVersionUID = 1457168587775026399L;

	private Collection<? extends GrantedAuthority> authorities;

	private UserAuth user;

	private String memberPassword;
	private String memberUsername;
	private Boolean enabled;
	private Boolean accountNonExpired;
	private Boolean accountNonLocked;
	private boolean credentialsNonExpired;
	
	
	public CustomUserDetails(String username, String password, Collection<? extends GrantedAuthority> authorities, UserAuth user) {
	    this.enabled = true;
	    this.memberUsername = username;
	    this.memberPassword = password;
	    this.accountNonExpired = true;
	    this.accountNonLocked = true;
	    this.credentialsNonExpired = true;
	    this.authorities = authorities == null ? List.of() : authorities;
	    this.user = user;
	}

	
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return authorities;
	}

	@Override
	public String getPassword() {
		return memberPassword;
	}

	@Override
	public String getUsername() {
		return memberUsername;
	}

	@Override
	public boolean isAccountNonExpired() {
		return accountNonExpired;
	}

	@Override
	public boolean isAccountNonLocked() {
		return accountNonLocked;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return credentialsNonExpired;
	}

	@Override
	public boolean isEnabled() {
		return enabled;
	}

	public void setUser(UserAuth user) {
		this.user = user;
	}
	
	public UserAuth getUser() {
		return user;
	}
}
