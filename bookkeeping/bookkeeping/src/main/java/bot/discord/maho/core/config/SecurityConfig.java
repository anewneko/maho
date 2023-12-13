package bot.discord.maho.core.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
public class SecurityConfig  {
	@Autowired
	UserDetailsService userService;
	
	
//	@Bean
//	JwtAuthenticationFilter jwtAuthenticationFilter() {
//		return new JwtAuthenticationFilter();
//	}

	
    @Bean
    BCryptPasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
    
    @Bean
    SecurityFilterChain filterChain(HttpSecurity http,AuthenticationManagerBuilder auth) throws Exception {
        http.csrf(csrf -> csrf.disable())
//        	.exceptionHandling(el -> el.authenticationEntryPoint(authenticationEntryPoint))
//        	.sessionManagement(el -> el.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
            .authorizeHttpRequests(req -> req.requestMatchers("/register/**").permitAll()
                							 .requestMatchers("/index").permitAll()
                							 .requestMatchers("/user").hasRole("ADMIN")
                							 .anyRequest().authenticated())
            .formLogin(fm -> fm.loginPage("/login")
                               .loginProcessingUrl("/login")
                               .defaultSuccessUrl("/users")
                               .permitAll())
            .logout(logout -> logout.logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
            .permitAll()
        );
//        access token
//    	http.addFilterBefore(jwtAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class);
        auth.userDetailsService(userService)
        	.passwordEncoder(passwordEncoder());
        
    	return http.build();
    }
    
}
