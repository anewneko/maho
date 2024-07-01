package bot.discord.maho.database.CrudService.Impl;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import bot.discord.maho.core.Util.MahoTool;
import bot.discord.maho.database.CrudService.CrudService;
import bot.discord.maho.database.Entity.Member;
import bot.discord.maho.database.Repository.MemberRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MemberService implements CrudService<Member>{
	private final MemberRepository repo;
	
	@Override
	public Optional<Member> findById(UUID id) {
		return repo.findById(id);
	}
	
	@Override
	public Member save(Member member) {
		return repo.save(member);
	}

	@Override
	public List<Member> findAll() {
		return repo.findAll();
	}

	@Override
	public List<Member> findByExample(Example<Member> t) {
		return repo.findAll(t);
	}

	@Override
	public List<Member> saveAll(List<Member> t) {
		return repo.saveAll(t);
	}

	@Override
	public void delete(Member t) {
		repo.delete(t);
	}

	@Override
	public void deleteById(UUID id) {
		repo.deleteById(id);
	}
	
	public Optional<Member> findById(String id) {
		return MahoTool.isUUID(id) ? findById(UUID.fromString(id)) : Optional.empty();
	}
	
	public Member findByDiscordId(Long discordId) {
		return repo.findByDiscordId(discordId);
	}
	
}
