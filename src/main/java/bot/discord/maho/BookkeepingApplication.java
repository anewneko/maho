package bot.discord.maho;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class BookkeepingApplication {

	public static void main(String[] args) {
		SpringApplication app = new SpringApplication(BookkeepingApplication.class);
		app.run(args);
	}
}
