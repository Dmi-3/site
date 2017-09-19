package com.site;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.hamcrest.Matchers;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.Arrays;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class) // controls the start of the test
@SpringBootTest
@AutoConfigureMockMvc
public class SiteApplicationTests {

	@Autowired
	private MockMvc mockMvc;

	@Autowired
	private BookRepository bookRepository;

	@Test
	public void createBook() throws Exception {
		Book book = new Book("testBook", 0);
		String requestBody = saveRequestJsonString(book);

		MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders
				.post("/books")
				.accept(MediaType.APPLICATION_JSON)
				.content(requestBody)
				.with(user(new User("admin","admin",
				Arrays.asList(new SimpleGrantedAuthority("ROLE_ADMIN")))));

		ResultActions resultActions = mockMvc.perform(requestBuilder);

		resultActions
				.andExpect((status().isCreated()))
				.andExpect(jsonPath("$.name", Matchers.is(book.getName())));

		Book bookFromRepo = bookRepository.findByName(book.getName());
		Assert.assertNotNull(bookFromRepo);

	}

	private final static ObjectMapper OBJECT_MAPPER = new ObjectMapper();

	private String saveRequestJsonString(Object object) throws JsonProcessingException {
		return OBJECT_MAPPER.writeValueAsString(object);
	}
}
