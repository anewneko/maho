package bot.discord.maho.database.Repo;

import org.springframework.data.jpa.repository.JpaRepository;

import bot.discord.maho.database.entity.Pay;

public interface PayRepository extends JpaRepository<Pay, Integer> {

}
