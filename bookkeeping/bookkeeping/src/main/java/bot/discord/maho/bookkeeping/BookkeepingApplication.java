package bot.discord.maho.bookkeeping;


import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import bot.discord.maho.bookkeeping.discord.util.Constants;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;

@SpringBootApplication
@MapperScan
public class BookkeepingApplication implements Constants{

	public static void main(String[] args) throws InterruptedException {
		SpringApplication.run(BookkeepingApplication.class, args);
		
		JDA jda = JDABuilder.createDefault(token)
	            						 .build();

	        // optionally block until JDA is ready
	        jda.awaitReady();
		
		
		
	}

	
	
}
