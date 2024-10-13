package com.acorn.project.point.controller;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.sql.Timestamp;


import javax.inject.Inject;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.acorn.project.point.service.PointServiceI;
import com.acorn.project.user.domain.User;
import com.acorn.project.user.service.UserServiceI;

@Controller
@RequestMapping("/point/*")
public class PointController {
   @Inject
   PointServiceI service;
   
   @Inject
   UserServiceI userService;
   
   @RequestMapping("showMyPoint.do")
    public ModelAndView showMyPoint(HttpSession session, 
                                    @RequestParam(value = "startDate", required = false) String startDate,
                                    @RequestParam(value = "endDate", required = false) String endDate) {
        ModelAndView mv = new ModelAndView();
        User user = (User) session.getAttribute("user");

        if (user != null) {
            try {
                String userCode = user.getUserCode();
                List<Map<String, Object>> pointList;

                if (startDate != null && endDate != null) {
                    pointList = service.getPointsWithinDateRange(userCode, startDate, endDate);
                } else {
                    pointList = service.getPointOne(userCode);
                }

                Collections.sort(pointList, new Comparator<Map<String, Object>>() {
                    @Override
                    public int compare(Map<String, Object> p1, Map<String, Object> p2) {
                        return ((Timestamp) p1.get("pointDate")).compareTo((Timestamp) p2.get("pointDate"));
                    }
                });

                int remainingPoints = 0;
                for (Map<String, Object> point : pointList) {
                    int pointStatus = (int) point.get("pointStatus");
                    int pointAmount = (int) point.get("pointAmount");

                    if (pointStatus == 1 || pointStatus == 3) {
                        remainingPoints += pointAmount;
                    } else if (pointStatus == 0 || pointStatus == 2) {
                        remainingPoints -= pointAmount;
                    }
                    point.put("remainingPoints", remainingPoints);
                }

                mv.addObject("pointList", pointList);
                mv.setViewName("point/showMyPoint");
            } catch (Exception e) {
                e.printStackTrace();
                mv.setViewName("errorPage");
                mv.addObject("message", "Error occurred while fetching user points.");
            }
        } else {
            mv.setViewName("user/login");
            mv.addObject("message", "Login");
        }

        return mv;
    }

   @RequestMapping("showMyEarnedPoint.do")
   public ModelAndView showMyEarnedPoint(HttpSession session, 
                                         @RequestParam(value = "startDate", required = false) String startDate,
                                         @RequestParam(value = "endDate", required = false) String endDate) {
        ModelAndView mv = new ModelAndView();
        User user = (User) session.getAttribute("user");

        if (user != null) {
            try {
                String userCode = user.getUserCode();
                List<Map<String, Object>> pointList;

                if (startDate != null && endDate != null) {
                    pointList = service.getPointsWithinDateRange(userCode, startDate, endDate);
                } else {
                    pointList = service.getPointOne(userCode);
                }
                Collections.sort(pointList, new Comparator<Map<String, Object>>() {
                    @Override
                    public int compare(Map<String, Object> p1, Map<String, Object> p2) {
                        return ((Timestamp) p1.get("pointDate")).compareTo((Timestamp) p2.get("pointDate"));
                    }
                });
                
                int remainingPoints = 0;
                for (Map<String, Object> point : pointList) {
                    int pointStatus = (int) point.get("pointStatus");
                    int pointAmount = (int) point.get("pointAmount");
                    remainingPoints += pointAmount;
                    point.put("remainingPoints", remainingPoints);
                }
                mv.addObject("pointList", pointList);
                mv.setViewName("point/showMyEarnedPoint");
            } catch (Exception e) {
                e.printStackTrace();
                mv.setViewName("errorPage");
                mv.addObject("message", "Error occurred while fetching user points.");
            }
        } else {
            mv.setViewName("user/login");
            mv.addObject("message", "Login");
        }
        return mv;
    }
   
   
   @RequestMapping("showMyUsePoint.do")
    public ModelAndView showMyUsePoint(HttpSession session, 
                                    @RequestParam(value = "startDate", required = false) String startDate,
                                    @RequestParam(value = "endDate", required = false) String endDate) {
        ModelAndView mv = new ModelAndView();
        User user = (User) session.getAttribute("user");

        if (user != null) {
            try {
                String userCode = user.getUserCode();
                List<Map<String, Object>> pointList;

                if (startDate != null && endDate != null) {
                    pointList = service.getPointsWithinDateRange(userCode, startDate, endDate);
                } else {
                    pointList = service.getPointOne(userCode);
                }

                Collections.sort(pointList, new Comparator<Map<String, Object>>() {
                    @Override
                    public int compare(Map<String, Object> p1, Map<String, Object> p2) {
                        return ((Timestamp) p1.get("pointDate")).compareTo((Timestamp) p2.get("pointDate"));
                    }
                });
                int remainingPoints = 0;
                for (Map<String, Object> point : pointList) {
                    int pointStatus = (int) point.get("pointStatus");
                    int pointAmount = (int) point.get("pointAmount");

                    if (pointStatus == 1 || pointStatus == 3) {
                        remainingPoints += pointAmount;
                    } else if (pointStatus == 0 || pointStatus == 2) {
                        remainingPoints -= pointAmount;
                    }
                    point.put("remainingPoints", remainingPoints);
                }
                mv.addObject("pointList", pointList);
                mv.setViewName("point/showMyUsePoint");
            } catch (Exception e) {
                e.printStackTrace();
                mv.setViewName("errorPage");
                mv.addObject("message", "Error occurred while fetching user points.");
            }
        } else {
            mv.setViewName("user/login");
            mv.addObject("message", "Login");
        }
        return mv;
    }   
   
   @ResponseBody
   @RequestMapping("charge_process.do")
   public int chargeProcess(@RequestParam int pointAmount, HttpSession session) throws Exception { 
       User user = (User) session.getAttribute("user");
       System.out.println(user);
       
       //
       if (user == null) {
           throw new IllegalStateException("User not logged in.");
       }

       int result = service.buyPoint(pointAmount, user.getUserCode());
       if(result > 0) {
          userService.updatePoint(pointAmount, user);
          user = userService.getUserById(user.getUserId());
          session.setAttribute("user", user);
       }
       return user.getUserPoint();
   }

   @ResponseBody
   @RequestMapping("exchange_process.do")
   public int exchangeProcess(@RequestParam int exchangeAmount, HttpSession session) throws Exception {
      User user = (User) session.getAttribute("user");
           if (user != null) {
               if (user.getUserPoint() >= exchangeAmount) {
                   int result = service.pointExchange(exchangeAmount, user.getUserCode());
                   if (result > 0) {
                       userService.updatePoint(-exchangeAmount, user);
                       user = userService.getUserById(user.getUserId());
                       session.setAttribute("user", user);
                   }
               }
           }
           return user.getUserPoint();
   }
   
   @ResponseBody
   @RequestMapping("buyBoard_process.do")
   public int buyBoardProcess(@RequestParam int pointAmount,
                               @RequestParam String boardCode,
                               @RequestParam String writerCode,
                               HttpSession session) throws Exception {
       User user = (User) session.getAttribute("user");
       String userCode = user.getUserCode();
       User writer = userService.getUserByCode(writerCode);
       int result = 0;
       
       if (user != null) {
           if (user.getUserPoint() >= pointAmount) {
               result = service.buyBoard(userCode, boardCode, pointAmount);
               if (result > 0) {
                   userService.updatePoint(-pointAmount, user);
                   userService.updatePoint(pointAmount, writer);
                   int result2 = service.sellBoard(writerCode, boardCode, pointAmount);
                   user = userService.getUserById(user.getUserId());
                   session.setAttribute("user", user);
                   System.out.println(user.getUserPoint());
               }
           }
       }
       
       return result;
   }

}
