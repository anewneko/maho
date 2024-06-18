package bot.discord.maho;

import java.io.FileNotFoundException;
import java.io.IOException;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan
public class BookkeepingApplication {

	public static void main(String[] args) throws FileNotFoundException, IOException {
		SpringApplication app = new SpringApplication(BookkeepingApplication.class);
		app.run(args);
			
	}
}
