package bot.discord.maho.bookkeeping;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;

import java.util.UUID;

import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;

import com.fasterxml.jackson.databind.ObjectMapper;

import bot.discord.maho.BookkeepingApplication;
import bot.discord.maho.database.Entity.LoginDetail;
import bot.discord.maho.database.Entity.Member;
import bot.discord.maho.security.Model.User4Jwt;
import bot.discord.maho.security.Service.JwtService;

@AutoConfigureMockMvc
@SpringBootTest(classes = BookkeepingApplication.class)
public abstract class MahoRobotUnitTest {
	@Autowired protected MockMvc mockMvc;
	@Autowired protected ObjectMapper objectMapper;
	@Autowired private JwtService jwtService;
	protected LoginDetail login = LoginDetail.getCurrent();
	
	@BeforeEach()
	private void init() {
		login.setIsUsed(true);
		login.setIsVerify(true);
		var member = new Member();
		member.setId(UUID.randomUUID());
		member.setIsManager(false);
		login.setMember(member);
	}
	
	protected MockHttpServletRequestBuilder request(RestFunction func, String path , Object body) throws Exception {
		MockHttpServletRequestBuilder builder = null;
		switch (func) {
			case POST -> builder = post(path);
			case PUT -> builder = put(path);
			default -> throw new Exception("Not support");
		}
		
		return builder
				 .contentType("application/json")
				 .header("Authorization", "Bearer " + jwtService.generateToken(User4Jwt.of(login)))
				 .content(objectMapper.writeValueAsString(body));
		
	}
	
	
	protected MockHttpServletRequestBuilder request(RestFunction func, String path) throws Exception  {
		MockHttpServletRequestBuilder builder = null;
        switch (func) {
	        case GET -> builder = get(path);
			case POST -> builder = post(path);
			case PUT -> builder = put(path);
	        case DELETE -> builder = delete(path);
        }
        
        return builder
				 .contentType("application/json")
				 .header("Authorization", "Bearer " + jwtService.generateToken(User4Jwt.of(login)));
	}


}
