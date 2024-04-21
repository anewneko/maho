package bot.discord.maho.core.Config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import bot.discord.maho.discord.Command.CommandManager;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.entities.Activity;
import net.dv8tion.jda.api.hooks.EventListener;

@Configuration
public class AppConfig {
	@Autowired private EventListener listener;
	@Value("${discord.bot.token}")
	private String token;
	
	@Autowired
	private ApplicationContext context;

	
//	@Autowired
//	private StringRedisTemplate redisTemplate;
	

	@Autowired
	@Bean
	JDA jda() throws Exception {
////		token = redisTemplate.opsForValue().get("token");
////		System.out.println(token);
		JDA jda = JDABuilder.createDefault(token)
						    .setActivity(Activity.playing("咕嚕靈波（●′∀‵）ノ♡"))
						    .addEventListeners(listener)
						    .build();
		
		new CommandManager(context,jda).setCommands();
		jda.awaitReady();
		System.out.println("Bot is Online");
		return jda;
//		
	}
	
	

}
