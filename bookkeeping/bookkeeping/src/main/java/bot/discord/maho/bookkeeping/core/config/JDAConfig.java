package bot.discord.maho.bookkeeping.core.config;

import java.util.Base64;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;

@Configuration
public class JDAConfig {
	@Value("${discord.token}")
	private String tokenbase;

	@Bean
	public JDA jda() throws InterruptedException {
		byte[] tokenbyte = Base64.getDecoder().decode(tokenbase);
		String token = new String(tokenbyte);
		JDA jda = JDABuilder.createDefault(token)
											.build();
											
		jda.awaitReady();
		return jda;
	}

}
