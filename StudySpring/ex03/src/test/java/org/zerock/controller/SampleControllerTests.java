package org.zerock.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import org.zerock.domain.Ticket;

import com.google.gson.Gson;

import lombok.Setter;
import lombok.extern.log4j.Log4j;

@RunWith(SpringJUnit4ClassRunner.class)
// Test for Controller
@WebAppConfiguration
@ContextConfiguration({	"file:src/main/webapp/WEB-INF/spring/root-context.xml",
						"file:src/main/webapp/WEB-INF/spring/appServlet/servlet-context.xml"})
@Log4j
public class SampleControllerTests {

	@Setter(onMethod_ = { @Autowired })
	private WebApplicationContext ctx;
	
	private MockMvc mockMvc;
	
	@Before
	public void setup() {
		this.mockMvc = MockMvcBuilders.webAppContextSetup(ctx).build();
	}
	
	
	
	/* SampleController 클래스 - convert() 메서드 테스트 */
	@Test
	public void testConvert() throws Exception{
		
		Ticket ticket = new Ticket();
		ticket.setTno(123);
		ticket.setOwner("Admin");
		ticket.setGrade("AAA");
		
		// Gson 라이브러리 - Java의 객체를 JSON 문자열로 변환하기 위해서 사용
		String jsonStr = new Gson().toJson(ticket);
		
		log.info(jsonStr);
		
		mockMvc.perform(post("/sample/ticket")
				// MockMvc - contentType() : 전달하는 데이터를 알려줌
				.contentType(MediaType.APPLICATION_JSON)
				.content(jsonStr))
				.andExpect(status().is(200));
	}
}
