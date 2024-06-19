package bot.discord.maho.database.Repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import bot.discord.maho.database.Entity.Member;

public interface MemberRepository extends JpaRepository<Member, UUID> {
	
	Member findByDiscordId(Long discordId);

}
