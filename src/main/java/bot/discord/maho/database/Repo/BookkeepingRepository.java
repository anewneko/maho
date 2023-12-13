package bot.discord.maho.database.Repo;

import org.springframework.data.jpa.repository.JpaRepository;

import bot.discord.maho.database.entity.Bookkeeping;

public interface BookkeepingRepository extends JpaRepository<Bookkeeping, Integer> {

}
