package bot.discord.maho;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan
public class BookkeepingApplication {

	public static void main(String[] args) throws FileNotFoundException, IOException {
		SpringApplication app = new SpringApplication(BookkeepingApplication.class);
		var bannerFile = new File("./src/main/java/bot/discord/maho/banner.txt");
		try (var bannerReader = new BufferedReader(new FileReader(bannerFile))) {
			var banner = new StringBuilder();
			String line;
			while ((line = bannerReader.readLine()) != null) 
				banner.append(line).append('\n');
			
			app.setBanner((environment, sourceClass, out) -> 
						   out.print(banner.append(
								   String.format("				\u001B[32m マホロボ : \u001B[0m version %s\n", 
										   environment.getProperty("discord.bot.version")))));
		}
        app.run(args);
	}

}
