package bot.discord.maho.core.Config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
@Configuration
public class CorsConfig  implements WebMvcConfigurer  {

	@Override
    public void addCorsMappings(CorsRegistry registry)  {
        registry.addMapping("/**")
                .allowedOrigins("http://localhost:3333","http://localhost:3000","https://discord.com","https://cloudflare.com")
                .allowedMethods("GET", "POST", "PUT", "DELETE")
                .exposedHeaders("Authorization", "Header2");
    }
}
