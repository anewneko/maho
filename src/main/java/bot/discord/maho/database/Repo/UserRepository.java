package bot.discord.maho.database.Repo;

import org.springframework.data.jpa.repository.JpaRepository;

import bot.discord.maho.database.entity.User;

public interface UserRepository extends JpaRepository<User, Integer>{

}
