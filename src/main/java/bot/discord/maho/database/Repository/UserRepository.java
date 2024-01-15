package bot.discord.maho.database.Repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import bot.discord.maho.database.Entity.User;

public interface UserRepository extends JpaRepository<User, UUID>{

}
