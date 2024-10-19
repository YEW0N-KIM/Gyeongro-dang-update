package com.acorn.project;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.acorn.project.board.domain.RouteBoard;
import com.acorn.project.board.service.BoardServiceI;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration   
@ContextConfiguration({   "file:src/main/webapp/WEB-INF/spring/root-context.xml", "file:src/main/webapp/WEB-INF/spring/appServlet/servlet-context.xml"})
public class HomeControllerTest {


	@Mock
	BoardServiceI boardS;
	
	@InjectMocks
    private HomeController control;

	private MockMvc mvc;
	
    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this); // Mock 객체 초기화
        mvc = MockMvcBuilders.standaloneSetup(control).build(); // MockMvc 초기화
    }
	
	@Test
	public void testMain() throws Exception {
		
		List<RouteBoard> list = new ArrayList<>();	
	    when(boardS.getHomeRouteData(0)).thenReturn(list);
	    
		mvc.perform(get("/"))
          .andExpect(status().isOk())
          .andExpect(view().name("home"))
          .andExpect(model().attributeExists("homeRouteData"))
          .andExpect(model().attribute("homeRouteData", list));
	}

}
