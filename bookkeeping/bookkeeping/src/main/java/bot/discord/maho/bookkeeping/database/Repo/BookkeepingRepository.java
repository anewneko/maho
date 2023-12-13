package bot.discord.maho.bookkeeping.database.Repo;

import org.springframework.data.jpa.repository.JpaRepository;

import bot.discord.maho.bookkeeping.database.entity.Bookkeeping;

public interface BookkeepingRepository extends JpaRepository<Bookkeeping, Integer> {

}
