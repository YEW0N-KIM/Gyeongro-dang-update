package com.acorn.project.tour.controller;

import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.io.IOException;
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
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.ui.Model;

import com.acorn.project.HomeService;
import com.acorn.project.board.domain.RouteBoard;
import com.acorn.project.board.service.BoardServiceI;
import com.acorn.project.tour.domain.TourDTO1;
import com.acorn.project.tour.domain.TourDTO2;
import com.acorn.project.tour.domain.TourDTO3;


@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration({   "file:src/main/webapp/WEB-INF/spring/root-context.xml", "file:src/main/webapp/WEB-INF/spring/appServlet/servlet-context.xml"})
public class TourControllerTest {
	
	@Mock
	HomeService homeS;
	
	@Mock
	BoardServiceI boardS;
	
	@Mock
	HttpSession session;
	
	@Mock
	HttpServletRequest request;
	
	@Mock
	Model model;
	
	@InjectMocks
	TourController control;
	
	@Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }
	
    @Test
    public void testGetTourListAjax() {
        String area = "1";
        String pageNo = "1";
        List<TourDTO1> expectedTourList = new ArrayList<>(); 
        expectedTourList.add(new TourDTO1()); 

        when(homeS.extractTours(any())).thenReturn((ArrayList<TourDTO1>) expectedTourList); 

        List<TourDTO1> actualTourList = control.getTourListAjax(area, pageNo);

        assertEquals(expectedTourList, actualTourList);
    }

    @Test
    public void testGetRouteListAjax() {

    	int boardRegion = 0;
        List<RouteBoard> expectedRouteList = Collections.singletonList(new RouteBoard());
        when(boardS.getHomeRouteData(boardRegion)).thenReturn(expectedRouteList);

        List<RouteBoard> actualRouteList = control.getRouteListAjax(boardRegion);

        assertEquals(expectedRouteList, actualRouteList);
    }

    @Test
    public void testTourList() throws IOException {
        String pageNo = "1";
        String area = "1";
        String arrange = "Q";
        String mapX = "";
        String mapY = "";
        
        when(request.getSession()).thenReturn(session);
        when(session.getAttribute("selectedArea")).thenReturn(area);
        when(session.getAttribute("arrange")).thenReturn(arrange);
        when(session.getAttribute("mapX")).thenReturn(mapX);
        when(session.getAttribute("mapY")).thenReturn(mapY);

        List<TourDTO1> expectedTourList = new ArrayList<>();
        expectedTourList.add(new TourDTO1());
        when(homeS.extractTours(any())).thenReturn((ArrayList<TourDTO1>) expectedTourList);

        String viewName = control.tourList(pageNo, area, arrange, mapX, mapY, model, request);

        assertEquals("/tour/tourlist", viewName);
        verify(model).addAttribute("tourList", expectedTourList);
        verify(model).addAttribute("currentPage", pageNo);
        verify(model).addAttribute("totalPages", control.calculateTotalPages(1000, 12));
        verify(model).addAttribute("selectedArrange", arrange);
        verify(model).addAttribute("area", area);
        verify(model).addAttribute("mapX", mapX);
        verify(model).addAttribute("mapY", mapY);
    }

    @Test
    public void testGetTourInfo() throws IOException {
        String contentId = "1";
        String contentTypeId = "2";
        
        List<TourDTO2> list = new ArrayList<>();
        list.add(new TourDTO2());
        List<TourDTO3> list2 = new ArrayList<>();
        list2.add(new TourDTO3());
        when(homeS.extractTourInfo(any())).thenReturn((ArrayList<TourDTO2>) list);
        when(homeS.extractTourInfo2(any())).thenReturn((ArrayList<TourDTO3>) list2);

        String viewName = control.getTourInfo(contentId, contentTypeId, model);

        assertEquals("/tour/tourDetail", viewName);
        verify(model).addAttribute("tourInfo", list);
        verify(model).addAttribute("tourInfo2", list2);
    }

    @Test
    public void testGetTourList() {
        String numOfRows = "3";
        String pageNo = "1";
        String area = "1";
        List<TourDTO1> expectedTourList = new ArrayList<>();
        expectedTourList.add(new TourDTO1());
        
        when(homeS.extractTours(any())).thenReturn((ArrayList<TourDTO1>) expectedTourList);

        List<TourDTO1> actualTourList = control.getTourList(numOfRows, pageNo, area);
        assertEquals(expectedTourList, actualTourList);
    }

    @Test
    public void testGetOrDefault() {
        String value = "";
        String defaultValue = "default";
        when(session.getAttribute("attributeName")).thenReturn("sessionValue");

        String result = control.getOrDefault(value, defaultValue, session, "attributeName");

        assertEquals("sessionValue", result);
    }

    @Test
    public void testCalculateTotalPages() {
        int totalItems = 1000;
        int itemsPerPage = 12;

        int totalPages = control.calculateTotalPages(totalItems, itemsPerPage);

        assertEquals(84, totalPages);
    }

}
