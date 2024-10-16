package com.acorn.project.event.controller;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.content;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.acorn.project.HomeService;
import com.acorn.project.event.domain.EventDTO1;
import com.acorn.project.event.domain.EventDTO2;
import com.acorn.project.event.domain.EventDTO3;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration   // Servlet의 ServletContext를 이용하기 위함
@ContextConfiguration({   "file:src/main/webapp/WEB-INF/spring/root-context.xml", "file:src/main/webapp/WEB-INF/spring/appServlet/servlet-context.xml"})
public class EventControllerTest {
	
	@Mock
	HomeService homeS;
	
	@Mock
	HttpServletRequest request;
	
	@Mock
	HttpSession session;
	
	
	@InjectMocks
	EventController control;
	
	private MockMvc mockMvc;
	
	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);
		mockMvc = MockMvcBuilders.standaloneSetup(control).build();
	}
	
	  @Test
	    public void testGetEventListAjax() throws Exception {
	        String year = "2023";
	        String month = "05";
	        String pageNo = "1";
	        List<EventDTO1> mockEventList = new ArrayList<>();
	        mockEventList.add(new EventDTO1());

	        when(homeS.extractEvents(any())).thenReturn((ArrayList<EventDTO1>) mockEventList);

	        RequestBuilder request = get("/ajax/eventlist")
	                .param("year", year)
	                .param("month", month)
	                .param("pageNo", pageNo)
	                .accept(MediaType.APPLICATION_JSON);

//	        mockMvc.perform(request)
//	                .andExpect(status().isOk())
//	                .andExpect((ResultMatcher) content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON)) // 호환성 검사
//	                .andExpect(jsonPath("$").isArray()) // 응답이 배열인지 확인
//	                .andExpect(jsonPath("$.length()").value(mockEventList.size()));
	    }

	    @Test
	    public void testEventList() throws Exception {
	        String pageNo = "1";
	        String year = "2023";
	        String month = "01";
	        String area = "1";
	        List<EventDTO1> mockEventList = new ArrayList<>();
	        mockEventList.add(new EventDTO1());

	        when(request.getSession()).thenReturn(session);
	        when(session.getAttribute("selectedYear")).thenReturn(year);
	        when(session.getAttribute("selectedMonth")).thenReturn(month);
	        when(session.getAttribute("selectedArea")).thenReturn(area);
	        when(homeS.extractEvents2(any())).thenReturn((ArrayList<EventDTO1>) mockEventList);

	        mockMvc.perform(get("/eventlist")
	                .param("pageNo", pageNo)
	                .param("year", year)
	                .param("month", month)
	                .param("area", area))
	                .andExpect(status().isOk())
	                .andExpect(view().name("/event/eventlist"))
	                .andExpect(model().attributeExists("eventList"))
	                .andExpect(model().attribute("currentPage", pageNo))
	                .andExpect(model().attribute("selectedYear", year))
	                .andExpect(model().attribute("selectedMonth", month))
	                .andExpect(model().attribute("selectedArea", area));
	    }

	    @Test
	    public void testGetEventInfo() throws Exception {
	        String contentId = "12345";
	        String contentTypeId = "1";
	        
	        List<EventDTO2> list = new ArrayList<>();
	        List<EventDTO3> list2 = new ArrayList<>();
	        list.add(new EventDTO2());
	        list2.add(new EventDTO3());
	        
	        when(homeS.extractEventInfo(any())).thenReturn((ArrayList<EventDTO2>) list);
	        when(homeS.extractEventInfo2(any())).thenReturn((ArrayList<EventDTO3>) list2);

	        mockMvc.perform(get("/eventInfo")
	                .param("contentId", contentId)
	                .param("contentTypeId", contentTypeId))
	                .andExpect(status().isOk())
	                .andExpect(view().name("/event/eventDetail"))
	                .andExpect(model().attributeExists("eventInfo"))
	                .andExpect(model().attributeExists("eventInfo2"));
	    }

	    @Test
	    public void testGetEventListWithDefaultValues() throws Exception {
	        when(request.getSession()).thenReturn(session);
	        when(session.getAttribute("selectedYear")).thenReturn(null);
	        when(session.getAttribute("selectedMonth")).thenReturn(null);
	        when(session.getAttribute("selectedArea")).thenReturn(null);

	        mockMvc.perform(get("/eventlist"))
	                .andExpect(status().isOk())
	                .andExpect(view().name("/event/eventlist"))
	                .andExpect(model().attributeExists("eventList"))
	                .andExpect(model().attribute("selectedYear", "2023"))
	                .andExpect(model().attribute("selectedMonth", "01"))
	                .andExpect(model().attribute("selectedArea", "1"));
	    }

}
