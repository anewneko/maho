package bot.discord.maho.security.Component;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import bot.discord.maho.database.CrudService.UserService;
import bot.discord.maho.security.Model.User4Jwt;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class SecurityUserService  implements UserDetailsService  {
	private final UserService userService;
	
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		var user = userService.findById(username);
		if (user == null)
			throw new UsernameNotFoundException("User not found");
		return User4Jwt.of(user);
	}
	
	public UserDetails loadUserByDiscordId(Long discordId) {
		var user = userService.findByDiscordId(discordId);
		if (user == null)
			throw new UsernameNotFoundException("User not found");
		return User4Jwt.of(user);
	}

}
