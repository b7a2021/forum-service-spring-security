package telran.b7a.security.service;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

public class UserProfile extends User {
	/**
	 * 
	 */
	private static final long serialVersionUID = -6320604906928729189L;
	
	private boolean passwordExpired;

	public UserProfile(String username, String password, Collection<? extends GrantedAuthority> authorities,
			boolean passwordExpired) {
		super(username, password, authorities);
		this.passwordExpired = passwordExpired;
	}

	public boolean isPasswordExpired() {
		return passwordExpired;
	}

}
