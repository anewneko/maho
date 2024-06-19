package bot.discord.maho.core.Model;

import org.springframework.security.core.GrantedAuthority;

import lombok.Data;
@Data
public class CustomGrantedAuthority implements GrantedAuthority  {
	private static final long serialVersionUID = -4787287379020250313L;
	private String authority;
	
	public CustomGrantedAuthority(String authority) {
		this.authority = authority;
	}
}
