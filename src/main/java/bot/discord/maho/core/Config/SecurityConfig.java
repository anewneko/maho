package bot.discord.maho.core.Config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import bot.discord.maho.security.Filter.JwtAuthenticationTokenFilter;
import bot.discord.maho.security.Service.SecurityUserService;
import lombok.RequiredArgsConstructor;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig{
	
	private final JwtAuthenticationTokenFilter jwtAuthFilter;
    @Autowired private SecurityUserService userSvc;
    
	@Bean
    BCryptPasswordEncoder passwordEncoder() throws Exception{
		return new BCryptPasswordEncoder();
	}
	
	@Bean
    AuthenticationProvider authenticationProvider() throws Exception {
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setUserDetailsService(userSvc);
        provider.setPasswordEncoder(passwordEncoder());
        return provider;
    }
	
	@Bean
    SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.csrf(csrf -> csrf.disable())
            .authorizeHttpRequests(req -> req.requestMatchers(new AntPathRequestMatcher("/redirect/**"),
            		                                          new AntPathRequestMatcher("/member/jwt/**"),
            		                                          new AntPathRequestMatcher("/login/**"),
            												  new AntPathRequestMatcher("/ping")).permitAll()
                                             .anyRequest().authenticated())
            .sessionManagement(sessionManagement -> 
            	sessionManagement.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
            .authenticationProvider(authenticationProvider())
            .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class);

    	return http.build();
    }

}
