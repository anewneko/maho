package bot.discord.maho.database.CrudService;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import bot.discord.maho.core.Model.User4Jwt;

@Service
public class UserService implements UserDetailsService {

	public UserDetails loadUserByUsername(String id) {
		return id.isEmpty() ? null : User4Jwt.fake();
	}
	
	public UserDetails loadUserByUsername(Long id) {
		return null;
	}
}
