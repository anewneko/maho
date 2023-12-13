package bot.discord.maho;


import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan
public class BookkeepingApplication{

	public static void main(String[] args) throws InterruptedException {
		
		SpringApplication.run(BookkeepingApplication.class, args);
		
		
	}

	
	
}
