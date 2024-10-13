package com.acorn.project;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;

import com.acorn.project.board.domain.RouteBoard;
import com.acorn.project.board.service.BoardService;

import java.util.List;

@Controller
public class HomeController {
	
	@Autowired
	private BoardService boardService;

    @GetMapping(value = "/", produces = "text/html;charset=UTF-8")
    public String main(Model model) { 
    	List<RouteBoard> homeRouteData = boardService.getHomeRouteData(0);
        model.addAttribute("homeRouteData", homeRouteData);
        return "home";
    }
}
