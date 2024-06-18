package bot.discord.maho.core.Config;

import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.web.client.RestTemplate;

import bot.discord.maho.discord.Command.CommandManager;
import bot.discord.maho.discord.Event.DiscordListener;
import lombok.RequiredArgsConstructor;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.entities.Activity;
import net.dv8tion.jda.api.requests.GatewayIntent;

@Configuration
@RequiredArgsConstructor
public class AppConfig {
	private final Environment environment;
	private final ApplicationContext context;

	@Bean
	JDA jda() throws Exception {
		String token = environment.getProperty("discord.bot.token");
		JDA jda = JDABuilder.createDefault(token)
						    .setActivity(Activity.customStatus("咕嚕靈波（●′∀‵）ノ♡"))
						    .addEventListeners(new DiscordListener(context))
						    .enableIntents(List.of(GatewayIntent.values()))
						    .build();
		
		new CommandManager(context,jda).setCommands();
		jda.awaitReady();
		System.out.println("Bot is Online");
		return jda;
	}
	
	@Bean
	RestTemplate restTemplate() {
		return new RestTemplate();
	}
}
