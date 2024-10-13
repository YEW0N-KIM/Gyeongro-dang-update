package com.acorn.project.event.controller;

import com.acorn.project.HomeService;
import com.acorn.project.event.domain.*;
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
import java.util.Calendar;
import java.util.Collections;
import java.util.List;

@Controller
public class EventController {

    @Autowired
    private HomeService homeService;

    @GetMapping("/ajax/eventlist")
    @ResponseBody
    public List<EventDTO1> getEventListAjax(@RequestParam(required = false) String year,
                                            @RequestParam(required = false) String month,
                                            @RequestParam(defaultValue = "1") String pageNo) {
        String numOfRows = "3";

        if (year == null || month == null) {
            Calendar cal = Calendar.getInstance();
            year = String.valueOf(cal.get(Calendar.YEAR));
            month = String.format("%02d", cal.get(Calendar.MONTH) + 1);
        }

        return getEventList(numOfRows, pageNo, year, month);
    }

    @GetMapping(value = "/eventlist", produces = "application/json;charset=utf-8")
    public String eventList(@RequestParam(value = "pageNo", defaultValue = "1") String pageNo,
                            @RequestParam(value = "year", defaultValue = "") String year,
                            @RequestParam(value = "month", defaultValue = "") String month,
                            @RequestParam(value = "area", defaultValue = "") String area, Model model, HttpServletRequest request)
            throws IOException {

        HttpSession session = request.getSession();
        String numOfRows = "12";

        year = getOrDefault(year, "2023", session, "selectedYear");
        month = getOrDefault(month, "01", session, "selectedMonth");
        area = getOrDefault(area, "1", session, "selectedArea");

        addEventListToModel2(numOfRows, pageNo, year, month, area, model);

        model.addAttribute("currentPage", pageNo);
        model.addAttribute("totalPages", calculateTotalPages(1000, Integer.parseInt(numOfRows)));
        model.addAttribute("selectedYear", year);
        model.addAttribute("selectedMonth", month);
        model.addAttribute("selectedArea", area);

        return "/event/eventlist";
    }

    @GetMapping(value = { "/eventInfo", "/eventlist/eventInfo" })
    public String getEventInfo(@RequestParam("contentId") String contentId,
                               @RequestParam("contentTypeId") String contentTypeId, Model model) throws IOException {
        addEventInfoToModel(contentId, contentTypeId, model);
        return "/event/eventDetail";
    }

    private void addEventListToModel2(String numOfRows, String pageNo, String year, String month, String area,
                                      Model model) throws IOException {
        try {
            List<EventDTO1> eventList = homeService
                    .extractEvents2(homeService.getDataEvent1(numOfRows, pageNo, year, month, area));
            if (eventList != null) {
                model.addAttribute("eventList", eventList);
            } else {
                model.addAttribute("eventList", Collections.emptyList());
            }
        } catch (JSONException e) {
            e.printStackTrace();
            model.addAttribute("eventList", Collections.emptyList());
        }
    }

    private void addEventInfoToModel(String contentId, String contentTypeId, Model model) throws IOException {
        try {
            model.addAttribute("eventInfo",
                    homeService.extractEventInfo(homeService.getEventInfo(contentId, contentTypeId)));
            model.addAttribute("eventInfo2",
                    homeService.extractEventInfo2(homeService.getEventInfo3(contentId, contentTypeId)));
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    private List<EventDTO1> getEventList(String numOfRows, String pageNo, String year, String month) {
        try {
            return homeService.extractEvents(homeService.getDataEvent(numOfRows, pageNo, year, month));
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
