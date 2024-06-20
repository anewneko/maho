package bot.discord.maho.database.CrudService;

import java.util.UUID;

import org.springframework.stereotype.Service;

import bot.discord.maho.core.Util.MahoTool;
import bot.discord.maho.database.Entity.Member;
import bot.discord.maho.database.Repository.MemberRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserService{
	
	private final MemberRepository repo;
	
	public Member findById(UUID id) {
		return repo.findById(id).orElse(null);
	}
	
	public Member findById(String id) {
		return MahoTool.isUUID(id) ? findById(UUID.fromString(id)) : null;
	}
	
	public Member findByDiscordId(Long discordId) {
		return repo.findByDiscordId(discordId);
	}
	
	public Member createUser(Member member) {
		return repo.save(member);
	}
}
