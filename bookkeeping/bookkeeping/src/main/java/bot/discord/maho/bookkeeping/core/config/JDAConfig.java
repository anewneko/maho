package bot.discord.maho.bookkeeping.core.config;

import java.util.EventListener;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.core.StringRedisTemplate;

import bot.discord.maho.bookkeeping.database.Repo.StatesRepository;
import bot.discord.maho.bookkeeping.discord.Command.CommandManager;
import jakarta.persistence.PersistenceContext;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.entities.Activity;

@PersistenceContext

@Configuration
public class JDAConfig {
	@Autowired
	private EventListener listener;
	private String token;
	@Value("${discord.state.playing}")
	private String playing ;
	@Autowired
	private StringRedisTemplate redisTemplate;
	

	@Bean
	JDA jda() throws InterruptedException {
		token = redisTemplate.opsForValue().get("token");
		JDA jda = JDABuilder.createDefault(token)
						    .setActivity(Activity.playing(playing))
						    .addEventListeners(listener)
						    .build();
											
		new CommandManager(jda).setCommands();
		jda.awaitReady();
		return null;
	}
	
	

}
