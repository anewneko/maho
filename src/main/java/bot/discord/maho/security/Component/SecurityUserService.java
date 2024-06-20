package bot.discord.maho.security.Component;

import java.util.function.Supplier;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import bot.discord.maho.database.CrudService.UserService;
import bot.discord.maho.database.Entity.Member;
import bot.discord.maho.security.Model.User4Jwt;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class SecurityUserService  implements UserDetailsService  {
	private final UserService userService;
	@Autowired JwtOwner owner;
	
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		return loadAndVerify(() -> userService.findById(username));
	}
	
	public UserDetails loadUserByDiscordId(Long discordId) {
		return loadAndVerify(() -> userService.findByDiscordId(discordId));
	}
	
	private UserDetails loadAndVerify(Supplier<Member> supplier) {
		var user = supplier.get();
		if (user == null)
			throw new UsernameNotFoundException("User not found");
		else
			owner.of(user);
		return User4Jwt.of(user);
	}

}
