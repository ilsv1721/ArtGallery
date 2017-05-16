package com.ilya.art.webcontollers;

import org.junit.Test;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.ilya.art.webcontrollers.HomePageController;

public class HomePageControllerTest {

	@Test
	public void testGetHomePage() throws Exception {
		HomePageController controller = new HomePageController();
		MockMvc mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
		mockMvc.perform(MockMvcRequestBuilders.get("/")).andExpect(MockMvcResultMatchers.view().name("HomePage"));
	}

}
