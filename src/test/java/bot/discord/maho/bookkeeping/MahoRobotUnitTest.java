package bot.discord.maho.bookkeeping;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;

import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;

import com.fasterxml.jackson.databind.ObjectMapper;

import bot.discord.maho.BookkeepingApplication;
import bot.discord.maho.database.Entity.Member;

@AutoConfigureMockMvc
@SpringBootTest(classes = BookkeepingApplication.class)
public abstract class MahoRobotUnitTest {
	@Autowired protected MockMvc mockMvc;
	@Autowired protected ObjectMapper objectMapper;
	protected Member member = new Member();
	
	@BeforeEach()
	private void init() {
		
	}
	
	protected MockHttpServletRequestBuilder request(RestFunction func, String path , Object body) throws Exception {
		MockHttpServletRequestBuilder builder = null;
		switch (func) {
		case POST:
			builder = post(path);
			break;
		case PUT:
			builder = put(path);
			break;
		default:
			throw new Exception("Not support");
		}
		
		return builder
				 .contentType("application/json")
//				 .header("Authorization", "Bearer " + jsonWebToken.generateJWT(user))
				 .content(objectMapper.writeValueAsString(body));
		
	}
	
	
	protected MockHttpServletRequestBuilder request(RestFunction func, String path) throws Exception  {
		MockHttpServletRequestBuilder builder = null;
        switch (func) {
        case GET:
            builder = get(path);
            break;
		case POST:
			builder = post(path);
			break;
		case PUT:
			builder = put(path);
			break;
        case DELETE:
            builder = delete(path);
            break;
        }
        
        return builder
				 .contentType("application/json");
//				 .header("Authorization", "Bearer " + jsonWebToken.generateJWT(user));
	}


}
