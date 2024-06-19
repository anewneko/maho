package bot.discord.maho.database.CrudService;

import java.util.UUID;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import bot.discord.maho.core.Model.User4Jwt;
import bot.discord.maho.core.Util.StringTool;
import bot.discord.maho.database.Entity.Member;
import bot.discord.maho.database.Repository.MemberRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserService implements UserDetailsService {
	
	private final MemberRepository repo;

	@Override
	public UserDetails loadUserByUsername(String name) {
		if (name.isEmpty()) 
			return null;
		var user = StringTool.isUUID(name) ? repo.findById(UUID.fromString(name)).orElse(null) : null;
		return User4Jwt.of(user);
	}
	
	public UserDetails loadUserByDiscordId(Long discordId) {
		var user = repo.findByDiscordId(discordId);
		return user == null ? null : User4Jwt.of(user);
	}
	
	public Member loadMemberById(UUID id) {
		return repo.findById(id).orElse(null);
	}
	
	public Member loadMemberById(String id) {
		return StringTool.isUUID(id) ? repo.findById(UUID.fromString(id)).orElse(null) : null;
	}
	
	public UserDetails createUser(Member member) {
		return User4Jwt.of(repo.save(member));
	}
}
