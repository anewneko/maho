package bot.discord.maho.core.Config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import bot.discord.maho.discord.Command.CommandManager;
import bot.discord.maho.discord.Event.DiscordListener;
import lombok.RequiredArgsConstructor;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.entities.Activity;

@Configuration
@RequiredArgsConstructor
public class AppConfig {
	final private ApplicationContext context;
	@Value("${discord.bot.token}") private String token;
	

	
//	@Autowired
//	private StringRedisTemplate redisTemplate;
	

	@Bean
	JDA jda() throws Exception {
////		token = redisTemplate.opsForValue().get("token");
////		System.out.println(token);
		JDA jda = JDABuilder.createDefault(token)
						    .setActivity(Activity.playing("咕嚕靈波（●′∀‵）ノ♡"))
						    .addEventListeners(new DiscordListener(context))
						    .build();
		
		new CommandManager(context,jda).setCommands();
		jda.awaitReady();
		System.out.println("Bot is Online");
		return jda;
	}
	
	
	@Bean
    WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
            	
                registry.addMapping("/**")
                        .allowedOrigins("http://localhost:3000")
                        .allowCredentials(true)
                        .allowedMethods("GET", "POST", "PUT", "DELETE")
                        .allowedHeaders("/*")
                        //可獲取哪些Header（因為跨網域預設不能取得全部Header資訊）
                        .exposedHeaders("Header1", "Header2");
            }
        };
    }
	
	

}
