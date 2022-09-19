package com.company.rewards.service;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import com.company.rewards.controller.CustomerController;
import com.company.rewards.dto.Rewards;

@ExtendWith(SpringExtension.class)
@WebMvcTest(controllers = CustomerController.class)
public class CustomerControllerTest {
	
	  @Autowired
	  private MockMvc mockMvc;

	  @MockBean
	  private CustomerService customerService;

	  
	    @Test
		public void shouldReturnNoContent() throws Exception {
			when(customerService.getAll()).thenReturn(new ArrayList<Rewards>());
			this.mockMvc.perform(get("/customer")).andDo(print()).andExpect(status().isNoContent());
		}
	    
}
