package com.acorn.project.tour.controller;

import com.acorn.project.HomeService;
import com.acorn.project.board.domain.RouteBoard;
import com.acorn.project.board.service.BoardServiceI;
import com.acorn.project.tour.domain.*;
import org.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Collections;
import java.util.List;

@Controller
public class TourController {

    @Autowired
    private HomeService homeService;
    
    @Autowired
    private BoardServiceI boardService;

    @GetMapping("/ajax/tourlist")
    @ResponseBody
    public List<TourDTO1> getTourListAjax(@RequestParam(value = "area", defaultValue = "1") String area,
                                          @RequestParam(value = "pageNo", defaultValue = "1") String pageNo) {
        String numOfRows = "3";
        return getTourList(numOfRows, pageNo, area);
    }

    @GetMapping("/ajax/routeList")
    @ResponseBody
    public List<RouteBoard> getRouteListAjax(@RequestParam(value="boardRegion", defaultValue="0") int boardRegion) {
    System.out.println(boardService.getHomeRouteData(boardRegion));
        return boardService.getHomeRouteData(boardRegion);
    }
    
    @GetMapping(value = "/tourlist", produces = "application/json;charset=utf-8")
    public String tourList(@RequestParam(value = "pageNo", defaultValue = "1") String pageNo,
                           @RequestParam(value = "area", defaultValue = "1") String area,
                           @RequestParam(value = "arrange", defaultValue = "Q") String arrange,
                           @RequestParam(value = "mapX", defaultValue = "") String mapX,
                           @RequestParam(value = "mapY", defaultValue = "") String mapY, Model model, HttpServletRequest request)
            throws IOException {

        HttpSession session = request.getSession();
        String numOfRows = "12";

        area = getOrDefault(area, "1", session, "selectedArea");
        arrange = getOrDefault(arrange, "Q", session, "arrange");
        mapX = getOrDefault(mapX, "", session, "mapX");
        mapY = getOrDefault(mapY, "", session, "mapY");

        addTourListToModel2(numOfRows, pageNo, area, arrange, mapX, mapY, model);

        model.addAttribute("currentPage", pageNo);
        model.addAttribute("totalPages", calculateTotalPages(1000, Integer.parseInt(numOfRows)));
        model.addAttribute("selectedArrange", arrange);
        model.addAttribute("area", area);
        model.addAttribute("mapX", mapX);
        model.addAttribute("mapY", mapY);

        return "/tour/tourlist";
    }
    
    
    @GetMapping(value = { "/tourInfo", "/tourlist/tourInfo" })
    public String getTourInfo(@RequestParam("contentId") String contentId,
                              @RequestParam("contentTypeId") String contentTypeId, Model model) throws IOException {
        addTourInfoToModel(contentId, contentTypeId, model);
        return "/tour/tourDetail";
    }

    private void addTourListToModel2(String numOfRows, String pageNo, String area, String arrange, String mapX,
                                     String mapY, Model model) throws IOException {
        List<?> tourList = null;

        if ("S".equals(arrange)) {
            List<TourDTO_dist> tourList2 = homeService
                    .extractTours2(homeService.getDataTour2(numOfRows, pageNo, arrange, mapX, mapY));
            if (tourList2 != null && !tourList2.isEmpty()) {
                tourList = tourList2;
            }
        } else {
            List<TourDTO1> tourList1 = homeService.extractTours(homeService.getDataTour(numOfRows, pageNo, area));
            if (tourList1 != null && !tourList1.isEmpty()) {
                tourList = tourList1;
            }
        }

        if (tourList == null || tourList.isEmpty()) {
            tourList = Collections.emptyList();
        }

        model.addAttribute("tourList", tourList);
        System.out.println(tourList);
    }

    private void addTourInfoToModel(String contentId, String contentTypeId, Model model) throws IOException {
        try {
            model.addAttribute("tourInfo",
                    homeService.extractTourInfo(homeService.getTourInfo(contentId, contentTypeId)));
            model.addAttribute("tourInfo2",
                    homeService.extractTourInfo2(homeService.getTourInfo3(contentId, contentTypeId)));
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    private List<TourDTO1> getTourList(String numOfRows, String pageNo, String area) {
        try {
            return homeService.extractTours(homeService.getDataTour(numOfRows, pageNo, area));
        } catch (IOException | JSONException e) {
            e.printStackTrace();
            return Collections.emptyList();
        }
    }

    private String getOrDefault(String value, String defaultValue, HttpSession session, String attributeName) {
        if (value == null || value.isEmpty()) {
            Object sessionValue = session.getAttribute(attributeName);
            return (sessionValue != null) ? sessionValue.toString() : defaultValue;
        }
        return value;
    }

    private int calculateTotalPages(int totalItems, int itemsPerPage) {
        return (int) Math.ceil((double) totalItems / itemsPerPage);
    }
}
