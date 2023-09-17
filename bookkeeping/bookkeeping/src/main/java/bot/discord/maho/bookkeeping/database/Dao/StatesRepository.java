package bot.discord.maho.bookkeeping.database.Dao;

import org.springframework.data.jpa.repository.JpaRepository;

import bot.discord.maho.bookkeeping.database.entity.States;

public interface StatesRepository extends JpaRepository<States, String> {

}
