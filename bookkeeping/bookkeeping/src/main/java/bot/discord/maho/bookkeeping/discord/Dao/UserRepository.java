package bot.discord.maho.bookkeeping.discord.Dao;

import org.springframework.data.jpa.repository.JpaRepository;

import bot.discord.maho.bookkeeping.database.entity.User;

public interface UserRepository extends JpaRepository<User, Integer>{

}
