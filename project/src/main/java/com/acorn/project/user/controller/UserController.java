package com.acorn.project.user.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.acorn.project.board.domain.Board;
import com.acorn.project.board.domain.PagingHandler;
import com.acorn.project.board.service.BoardServiceI;
import com.acorn.project.report.domain.ReportCheck;
import com.acorn.project.report.service.ReportServiceI;
import com.acorn.project.user.domain.User;
import com.acorn.project.user.service.UserServiceI;


@Controller
@RequestMapping("/user/")
public class UserController {
	
	
	 @InitBinder
		public void toDate( WebDataBinder binder) {
		 SimpleDateFormat df = new SimpleDateFormat("yyyy-mm-dd");
		     binder.registerCustomEditor( Date.class , new CustomDateEditor( df, false)) ; 
		} 
	
	@Inject
	UserServiceI userService;
	
	@Autowired
	BoardServiceI boardService;
	
	@Autowired
	ReportServiceI reportService;
	
	@RequestMapping("login.do")
	public String login() {
		return "user/login";
	}
	
	@RequestMapping("login_check.do")
	public ModelAndView login_check(@ModelAttribute User user, HttpSession session) {
	    user = userService.loginCheck(user, session);
	    ModelAndView mv = new ModelAndView();
	    if(user != null) {
	        mv.setViewName("redirect:/");
	    } else {
	    	session.invalidate();
	        mv.setViewName("user/login");
	        mv.addObject("message", "error");
	    }
	    return mv;
	}

	
	@RequestMapping("logout.do")
	public String logout(HttpSession session) {
	    session.invalidate();
	    return "redirect:/";
	}
	
	@RequestMapping("register_process.do")
	public ModelAndView registerProcess(@ModelAttribute User user) {
		ModelAndView mv = new ModelAndView();
		try {
			int result = userService.regUser(user);
			if(result > 0) {
				mv.setViewName("redirect:/");
				mv.addObject("message", "registered");
			} else {
				mv.setViewName("user/register");
				mv.addObject("message", "error");
			}
		} catch (Exception e) {
			e.printStackTrace();
			mv.setViewName("user/register");
			mv.addObject("message", "error");
		}
		return mv;
	}
	
	@RequestMapping(value = "/check_id.do", method = RequestMethod.POST)
    @ResponseBody
    public String checkId(@RequestParam String userId) {
        try {
            User user = userService.getUserById(userId);
            if (user != null) {
                return "duplicate";
            } else {
                return "available";
            }
        } catch (Exception e) {
            e.printStackTrace();
            return "error";
        }
    }
	
	@RequestMapping(value = "/check_nickname.do", method = RequestMethod.POST)
	@ResponseBody
	public String checkNickname(@RequestParam String nickname) {
	    try {
	        int result = userService.nicknameCheck(nickname);
	        if (result >= 1) {
	            return "duplicate";
	        } else {
	            return "available";
	        }
	    } catch (Exception e) {
	        e.printStackTrace();
	        return "error";
	    }
	}
	
	@RequestMapping(value = "/check_userTel.do", method = RequestMethod.POST)
	@ResponseBody
	public String checkUserTel(@RequestParam String userTel) {
	    try {
	        int result = userService.userTelCheck(userTel);
	        if (result >= 1) {
	            return "duplicate";
	        } else {
	            return "available";
	        }
	    } catch (Exception e) {
	        e.printStackTrace();
	        return "error";
	    }
	}

	
	
	@RequestMapping("mypage.do")
	public ModelAndView myPage(HttpSession session,  @RequestParam(defaultValue = "1") int page, HttpServletRequest request) {
	    ModelAndView mv = new ModelAndView();
	    String url = request.getRequestURL().toString();
	    session.setAttribute("url", url);
	    try {
	        User user = (User) session.getAttribute("user");
	        if (user != null) {
	            mv.setViewName("user/mypage");
	            mv.addObject("user", user);
	            String userId = user.getUserId();
	            List<Board> myboard = boardService.getBoardByuser(userId,page); 
	            mv.addObject("list",myboard);
	            
	            int pageSize= 10;
	            int totRecords = boardService.selectUserCount(userId);
	            PagingHandler handler = new PagingHandler(page, totRecords, pageSize);
	            mv.addObject("paging",handler);
	            
	        } else {
	            mv.setViewName("errorPage");
	            mv.addObject("message", "User information not found");
	        }
	    } catch (Exception e) {
	        e.printStackTrace();
	        mv.setViewName("errorPage");
	        mv.addObject("message", "Error");
	    }
	    return mv;
	}
	
	@RequestMapping("mypage.do/arch")
	public ModelAndView myPageArch(HttpSession session,  @RequestParam(defaultValue = "1") int page, HttpServletRequest request) {
	    ModelAndView mv = new ModelAndView();
	    String url = request.getRequestURL().toString();
	    session.setAttribute("url", url);
	    try {
	        User user = (User) session.getAttribute("user");
	        if (user != null) {
	            mv.setViewName("user/mypage");
	            mv.addObject("user", user);
	            String userId = user.getUserId();
	            List<Board> myboard = boardService.selectUserArch(userId,page); 
	            mv.addObject("list",myboard);
	            
	            int pageSize= 10;
	            int totRecords = boardService.MyArchCount(userId);
	            PagingHandler handler = new PagingHandler(page, totRecords, pageSize);
	            mv.addObject("paging",handler);
	            
	        } else {
	            mv.setViewName("errorPage");
	            mv.addObject("message", "User information not found");
	        }
	    } catch (Exception e) {
	        e.printStackTrace();
	        mv.setViewName("errorPage");
	        mv.addObject("message", "Error");
	    }
	    return mv;
	}
	
	@RequestMapping("mypage.do/like")
	public ModelAndView myPageLike(HttpSession session,  @RequestParam(defaultValue = "1") int page, HttpServletRequest request) {
	    ModelAndView mv = new ModelAndView();
	    String url = request.getRequestURL().toString();
	    session.setAttribute("url", url);
	    try {
	        User user = (User) session.getAttribute("user");
	        if (user != null) {
	            mv.setViewName("user/mypage");
	            mv.addObject("user", user);
	            String userId = user.getUserId();
	            List<Board> myboard = boardService.selectUserLike(userId,page); 
	            mv.addObject("list",myboard);
	            
	            int pageSize= 10;
	            int totRecords = boardService.MyLikeCount(userId);
	            PagingHandler handler = new PagingHandler(page, totRecords, pageSize);
	            mv.addObject("paging",handler);
	            
	        } else {
	            mv.setViewName("errorPage");
	            mv.addObject("message", "User information not found");
	        }
	    } catch (Exception e) {
	        e.printStackTrace();
	        mv.setViewName("errorPage");
	        mv.addObject("message", "Error");
	    }
	    return mv;
	}
	
	@RequestMapping("mypage.do/com")
	public ModelAndView myPageCom(HttpSession session,  @RequestParam(defaultValue = "1") int page, HttpServletRequest request) {
	    ModelAndView mv = new ModelAndView();
	    String url = request.getRequestURL().toString();
	    session.setAttribute("url", url);
	    try {
	        User user = (User) session.getAttribute("user");
	        if (user != null) {
	            mv.setViewName("user/mypage");
	            mv.addObject("user", user);
	            String userId = user.getUserId();
	            List<Board> myboard = boardService.selectUserCom(userId,page); 
	            mv.addObject("list",myboard);
	            
	            int pageSize= 10;
	            int totRecords = boardService.MyComCount(userId);
	            PagingHandler handler = new PagingHandler(page, totRecords, pageSize);
	            mv.addObject("paging",handler);
	            
	        } else {
	            mv.setViewName("errorPage");
	            mv.addObject("message", "User information not found");
	        }
	    } catch (Exception e) {
	        e.printStackTrace();
	        mv.setViewName("errorPage");
	        mv.addObject("message", "Error");
	    }
	    return mv;
	}
	
	@RequestMapping("mypage.do/point")
	public ModelAndView myPagePoint(HttpSession session,  @RequestParam(defaultValue = "1") int page, HttpServletRequest request) {
	    ModelAndView mv = new ModelAndView();
	    String url = request.getRequestURL().toString();
	    session.setAttribute("url", url);
	    try {
	        User user = (User) session.getAttribute("user");
	        if (user != null) {
	            mv.setViewName("user/mypage");
	            mv.addObject("user", user);
	            String userId = user.getUserId();
	            List<Board> myboard = boardService.selectUserPoint(userId,page); 
	            mv.addObject("list",myboard);
	            
	            int pageSize= 10;
	            int totRecords = boardService.MyPointCount(userId);
	            PagingHandler handler = new PagingHandler(page, totRecords, pageSize);
	            mv.addObject("paging",handler);
	            
	        } else {
	            mv.setViewName("errorPage");
	            mv.addObject("message", "User information not found");
	        }
	    } catch (Exception e) {
	        e.printStackTrace();
	        mv.setViewName("errorPage");
	        mv.addObject("message", "Error");
	    }
	    return mv;
	}
	
	
	@RequestMapping("mypage.do/inquiry")
	public ModelAndView myPageInquiry(HttpSession session,  @RequestParam(defaultValue = "1") int page, HttpServletRequest request) {
	    ModelAndView mv = new ModelAndView();
	    String url = request.getRequestURL().toString();
	    session.setAttribute("url", url);
	    try {
	        User user = (User) session.getAttribute("user");
	        if (user != null) {
	            mv.setViewName("user/mypage");
	            mv.addObject("user", user);
	            List<Board> myboard = boardService.selectInquiry(page); 
	            mv.addObject("list",myboard);
	            
	            int pageSize= 10;
	            int totRecords = boardService.selectInquiryCount();
	            PagingHandler handler = new PagingHandler(page, totRecords, pageSize);
	            mv.addObject("paging",handler);
	            
	        } else {
	            mv.setViewName("errorPage");
	            mv.addObject("message", "User information not found");
	        }
	    } catch (Exception e) {
	        e.printStackTrace();
	        mv.setViewName("errorPage");
	        mv.addObject("message", "Error");
	    }
	    return mv;
	}
	
	@RequestMapping("mypage.do/report")
	public ModelAndView myPageReport(HttpSession session,  @RequestParam(defaultValue = "1") int page, HttpServletRequest request) {
	    ModelAndView mv = new ModelAndView();
	    String url = request.getRequestURL().toString();
	    session.setAttribute("url", url);
	    try {
	        User user = (User) session.getAttribute("user");
	        if (user != null) {
	            mv.setViewName("user/mypage");
	            mv.addObject("user", user);
	            List<ReportCheck> myboard = reportService.selectReport(page); 
	            mv.addObject("list",myboard);
	            
	            int pageSize= 10;
	            int totRecords = reportService.reportCount();
	            PagingHandler handler = new PagingHandler(page, totRecords, pageSize);
	            mv.addObject("paging",handler);
	            
	        } else {
	            mv.setViewName("errorPage");
	            mv.addObject("message", "User information not found");
	        }
	    } catch (Exception e) {
	        e.printStackTrace();
	        mv.setViewName("errorPage");
	        mv.addObject("message", "Error");
	    }
	    return mv;
	}
	
	
	

	
	@RequestMapping("modifyInfo.do")
	public ModelAndView modifyInfo(HttpSession session) {
	    ModelAndView mv = new ModelAndView();
	    try {
	    	User user = (User) session.getAttribute("user");
	        if (user != null) {
	            mv.setViewName("user/modify");
	            mv.addObject("user", user);
	        } else {
	            mv.setViewName("errorPage");
	            mv.addObject("message", "User information not found");
	        }
	    } catch (Exception e) {
	        e.printStackTrace();
	        mv.setViewName("errorPage");
	        mv.addObject("message", "Error");
	    }
	    return mv;
	}
	
	

	@RequestMapping("modify_process.do")
	public ModelAndView modifyProcess(@ModelAttribute User user, BindingResult r, HttpSession session) {
	    ModelAndView mv = new ModelAndView();
	    try {
	        int result = userService.modUser(user);
	        if (result > 0) {
	            User updatedUser = userService.getUserById(user.getUserId());
	            session.setAttribute("user", updatedUser);
	            mv.setViewName("redirect:/user/mypage.do");
	        } else {
	            mv.setViewName("errorPage");
	            mv.addObject("message", "Error");
	        }
	    } catch (Exception e) {
	        e.printStackTrace();
	        mv.setViewName("errorPage");
	        mv.addObject("message", "Error");
	    }
	    return mv;
	}

	
	
	@RequestMapping("deleteInfo.do")
	public ModelAndView deleteInfo(HttpSession session) {
	    ModelAndView mv = new ModelAndView();
	    try {
	    	User user = (User) session.getAttribute("user");
	        if (user != null) {
	            mv.setViewName("user/delete");
	            mv.addObject("user", user);
	        } else {
	            mv.setViewName("errorPage");
	            mv.addObject("message", "User information not found");
	        }
	    } catch (Exception e) {
	        e.printStackTrace();
	        mv.setViewName("errorPage");
	        mv.addObject("message", "Error");
	    }
	    return mv;
	}
	
	

	@RequestMapping("delete_process.do")
	public ModelAndView deleteProcess(@RequestParam String userId, HttpSession session) {
	    ModelAndView mv = new ModelAndView();
	    try {
	        int result = userService.delUser(userId);
	        if (result > 0) {
	        	session.invalidate();
	            mv.setViewName("redirect:/");
	        } else {
	            mv.setViewName("errorPage");
	            mv.addObject("message", "Failed to delete user");
	        }
	    } catch (Exception e) {
	        e.printStackTrace();
	        mv.setViewName("errorPage");
	        mv.addObject("message", "Error");
	    }
	    return mv;
	}
	
	@RequestMapping("findMyInfo.do")
	public String findMyInfo() {
		return "user/findMyInfo";
	}

	@RequestMapping(value = "findMyId.do", method = RequestMethod.POST)
	public String findUserId(@RequestParam String userName, @RequestParam String userTel, HttpSession session) {
	    try {
	        String userId = userService.findUserId(userName, userTel);
	        
	        if (userId != null) {
	            session.setAttribute("foundMyId", userId);
	        } else {
	            session.setAttribute("IdErrorMessage", "이름 또는 전화번호를 잘못 입력하셨습니다.");
	            return "redirect:/user/findMyInfo.do";
	        }
	    } catch (Exception e) {
	        e.printStackTrace();
	        return "errorPage";
	    }
	    
	    return "redirect:/user/foundMyId.do";
	}

	
	@RequestMapping(value = "foundMyId.do", method = RequestMethod.GET)
	public ModelAndView foundMyId(HttpSession session) {
	    ModelAndView mv = new ModelAndView();
	    String foundMyId = (String) session.getAttribute("foundMyId");
	    if (foundMyId != null) {
	        mv.setViewName("user/foundMyId");
	        mv.addObject("foundMyId", foundMyId);
	    } else {
	        mv.setViewName("errorPage");
	        mv.addObject("message", "User ID not found in session");
	    }
	    return mv;
	}
	
	@RequestMapping(value = "findMyPw.do", method = RequestMethod.POST)
	public String findUserPw(@RequestParam String userId, @RequestParam String userName, @RequestParam String userTel, HttpSession session) {
	    try {
	        String userPw = userService.findUserPw(userId, userName, userTel);
	        
	        if (userPw != null) {
	        	session.setAttribute("userId", userId);
	        	 return "redirect:/user/foundMyPw.do";
	        } else {
	        	session.setAttribute("PwErrorMessage", "아이디, 이름 또는 전화번호를 잘못 입력하셨습니다.");
	        	return "redirect:/user/findMyInfo.do?type=pw";

	        }
	    } catch (Exception e) {
	        e.printStackTrace();
	        return "errorPage";
	    }
	}
	
	@RequestMapping(value = "foundMyPw.do", method = RequestMethod.GET)
	public ModelAndView foundMyPw(HttpSession session) {
	    ModelAndView mv = new ModelAndView();
	    
	    String userId = (String) session.getAttribute("userId");
	    if (userId != null) {
	        mv.setViewName("user/foundMyPw");
	        mv.addObject("userId", userId);
	        
	    } else {
	        mv.setViewName("errorPage");
	        mv.addObject("message", "User not found in session");
	    }
	    return mv;
	}
	
	@RequestMapping(value = "updateMyPw.do", method = RequestMethod.POST)
	public String updateUserPw(@RequestParam String userId, @RequestParam String newPw, HttpSession session) {
	    try {
	        int result = userService.updateUserPw(userId, newPw);

	        if (result >= 1) {
	            return "redirect:/user/log	in.do";
	        } else {
	            session.setAttribute("errorMessage", "비밀번호 업데이트에 실패하였습니다.");
	            return "redirect:/user/findMyInfo.do";
	        }
	    } catch (Exception e) {
	        e.printStackTrace();
	        return "errorPage";
	    }
	}
}
