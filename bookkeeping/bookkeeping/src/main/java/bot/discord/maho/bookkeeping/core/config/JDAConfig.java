package bot.discord.maho.bookkeeping.core.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import bot.discord.maho.bookkeeping.discord.Command.CommandManager;
import bot.discord.maho.bookkeeping.discord.Dao.StatesRepository;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.entities.Activity;
import net.dv8tion.jda.api.hooks.EventListener;

@Configuration
public class JDAConfig {
	@Autowired
	private StatesRepository state;
	@Autowired
	private EventListener listener;
	private String token;
	private String playing ;
	

	@Bean
	JDA jda() throws InterruptedException {
		playing = state.findById("playing").get().getValue();
		token = state.findById("token").get().getValue();
		JDA jda = JDABuilder.createDefault(token)
										 .setActivity(Activity.playing(playing))
										 .addEventListeners(listener)
										 .build();
											
		new CommandManager(jda).setCommands();
		jda.awaitReady();
		return jda;
	}

}
