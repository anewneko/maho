package bot.discord.maho.database.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import bot.discord.maho.database.Entity.Pay;

public interface PayRepository extends JpaRepository<Pay, Integer> {

}
