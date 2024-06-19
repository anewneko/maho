package bot.discord.maho.core.Model;

import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import bot.discord.maho.database.Entity.Member;
import lombok.Data;
@Data
public class User4Jwt implements UserDetails {
	private static final long serialVersionUID = 6228579724714609705L;
	private String username;
	private List<GrantedAuthority> authorities;

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return authorities;
	}

	@Override
	public String getPassword() {
		return "";
	}

	@Override
	public String getUsername() {
		return username;
	}

	@Override
	public boolean isAccountNonExpired() {
		return false;
	}

	@Override
	public boolean isAccountNonLocked() {
		return false;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return false;
	}

	@Override
	public boolean isEnabled() {
		return false;
	}
	
	public static User4Jwt of(Member member) {
		var user = new User4Jwt();
		user.setUsername(member.getId().toString());
		if(member.getIsManager()) 
			user.setAuthorities(List.of(() -> "ROLE_MANAGER"));
		return user;
	}

}
