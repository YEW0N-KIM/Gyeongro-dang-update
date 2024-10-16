package com.acorn.project.user.controller;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.request;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.acorn.project.board.domain.Board;
import com.acorn.project.board.service.BoardServiceI;
import com.acorn.project.report.domain.ReportCheck;
import com.acorn.project.report.service.ReportServiceI;
import com.acorn.project.user.domain.User;
import com.acorn.project.user.service.UserServiceI;


@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration   // Servlet의 ServletContext를 이용하기 위함
@ContextConfiguration({   "file:src/main/webapp/WEB-INF/spring/root-context.xml", "file:src/main/webapp/WEB-INF/spring/appServlet/servlet-context.xml"})
public class UserControllerTest {
	
	@Mock
	UserServiceI userS;
	
	@Mock
	BoardServiceI boardS;
	
	@Mock
	ReportServiceI reportS;
	
	@Mock
	HttpSession session;
	
	@InjectMocks
	UserController control;
	
	private MockMvc mvc;
//	private HttpSession session;
	
	 @Before
	    public void setUp() {
	        MockitoAnnotations.initMocks(this);
	        mvc = MockMvcBuilders.standaloneSetup(control).build(); // MockMvc 초기화
	    }

//	@Test
//	public void testToDate() {
//		fail("Not yet implemented");
//	}
//
//	@Test
//	public void testLogin() {
//		fail("Not yet implemented");
//	}
//
//	@Test
//	public void testLogin_check() {
//		fail("Not yet implemented");
//	}
//
//	@Test
//	public void testLogout() {
//		fail("Not yet implemented");
//	}
//
//	@Test
//	public void testRegisterProcess() {
//		fail("Not yet implemented");
//	}
//
//	@Test
//	public void testCheckId() {
//		fail("Not yet implemented");
//	}
//
//	@Test
//	public void testCheckNickname() {
//		fail("Not yet implemented");
//	}
//
//	@Test
//	public void testCheckUserTel() {
//		fail("Not yet implemented");
//	}
//
//	@Test
//	public void testMyPage() {
//		fail("Not yet implemented");
//	}
//
//	@Test
//	public void testMyPageArch() {
//		fail("Not yet implemented");
//	}
//
//	@Test
//	public void testMyPageLike() {
//		fail("Not yet implemented");
//	}
//
//	@Test
//	public void testMyPageCom() {
//		fail("Not yet implemented");
//	}
//
//	@Test
//	public void testMyPagePoint() {
//		fail("Not yet implemented");
//	}
//
//	@Test
//	public void testMyPageInquiry() {
//		fail("Not yet implemented");
//	}
//
//	@Test
//	public void testMyPageReport() {
//		fail("Not yet implemented");
//	}
//
//	@Test
//	public void testModifyInfo() {
//		fail("Not yet implemented");
//	}
//
//	@Test
//	public void testModifyProcess() {
//		fail("Not yet implemented");
//	}
//
//	@Test
//	public void testDeleteInfo() {
//		fail("Not yet implemented");
//	}
//
//	@Test
//	public void testDeleteProcess() {
//		fail("Not yet implemented");
//	}
//
//	@Test
//	public void testFindMyInfo() {
//		fail("Not yet implemented");
//	}
//
//	@Test
//	public void testFindUserId() {
//		fail("Not yet implemented");
//	}
//
//	@Test
//	public void testFoundMyId() {
//		fail("Not yet implemented");
//	}
//
//	@Test
//	public void testFindUserPw() {
//		fail("Not yet implemented");
//	}
//
//	@Test
//	public void testFoundMyPw() {
//		fail("Not yet implemented");
//	}
//
//	@Test
//	public void testUpdateUserPw() {
//		fail("Not yet implemented");
//	}
	@Test
    public void testLogin() throws Exception {
        mvc.perform(get("/user/login.do"))
                .andExpect(status().isOk())
                .andExpect(view().name("user/login"));
    }

    @Test
    public void testLoginCheck_Success() throws Exception {
        User user = new User();
        user.setUserId("testUser");
        user.setUserPw("testPassword");

        when(userS.loginCheck(any(User.class), any(HttpSession.class))).thenReturn(user);

        mvc.perform(post("/user/login_check.do")
                .flashAttr("user", user))
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/"));
    }

    @Test
    public void testLoginCheck_Failure() throws Exception {
        User user = new User();
        user.setUserId("testUser");
        user.setUserPw("wrongPassword");

        when(userS.loginCheck(any(User.class), any(HttpSession.class))).thenReturn(null);

        mvc.perform(post("/user/login_check.do")
                .flashAttr("user", user))
                .andExpect(status().isOk())
                .andExpect(view().name("user/login"))
                .andExpect(model().attributeExists("message"));
    }

    @Test
    public void testLogout() throws Exception {
        mvc.perform(get("/user/logout.do"))
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/"));
    }

    @Test
    public void testRegisterProcess_Success() throws Exception {
        User user = new User();
        user.setUserId("newUser");
        user.setUserPw("newPassword");

        when(userS.regUser(any(User.class))).thenReturn(1);

        mvc.perform(post("/user/register_process.do")
                .flashAttr("user", user))
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/"))
                .andExpect(model().attribute("message", "registered"));
    }

    @Test
    public void testRegisterProcess_Failure() throws Exception {
        User user = new User();
        user.setUserId("newUser");
        user.setUserPw("newPassword");

        when(userS.regUser(any(User.class))).thenReturn(0); 

        mvc.perform(post("/user/register_process.do")
                .flashAttr("user", user))
                .andExpect(status().isOk())
                .andExpect(view().name("user/register"))
                .andExpect(model().attribute("message", "error"));
    }

    @Test
    public void testCheckId_Available() throws Exception {
        when(userS.getUserById("testUser")).thenReturn(null); 

        mvc.perform(post("/user/check_id.do")
                .param("userId", "testUser"))
                .andExpect(status().isOk());
    }

    @Test
    public void testCheckId_Duplicate() throws Exception {
        User existingUser = new User();
        existingUser.setUserId("testUser");

        when(userS.getUserById("testUser")).thenReturn(existingUser); 

        mvc.perform(post("/user/check_id.do")
                .param("userId", "testUser"))
                .andExpect(status().isOk());
    }

    @Test
    public void testCheckNickname_Available() throws Exception {
        when(userS.nicknameCheck("testNickname")).thenReturn(0);

        mvc.perform(post("/user/check_nickname.do")
                .param("nickname", "testNickname"))
                .andExpect(status().isOk());
    }

    @Test
    public void testCheckNickname_Duplicate() throws Exception {
        when(userS.nicknameCheck("testNickname")).thenReturn(1);

        mvc.perform(post("/user/check_nickname.do")
                .param("nickname", "testNickname"))
                .andExpect(status().isOk());
    }

    @Test
    public void testMyPage() throws Exception {
        User user = new User();
        user.setUserId("testUser");

        List<Board> list = new ArrayList<>();
        list.add(new Board());
        
        when(session.getAttribute("user")).thenReturn(user);
        when(boardS.getBoardByuser("testUser",1)).thenReturn(list);

        mvc.perform(get("/user/mypage.do")
                .sessionAttr("user", user)
                .param("page", "1"))
                .andExpect(status().isOk())
                .andExpect(view().name("user/mypage"))
                .andExpect(model().attribute("user", user));
    }
    
    @Test
    public void testMyPageArch() throws Exception {
        User user = new User();
        user.setUserId("testUser");
        
        List<Board> list = new ArrayList<>();
        list.add(new Board());
        when(session.getAttribute("user")).thenReturn(user);
        when(boardS.selectUserArch("testUser", 1)).thenReturn(list);
        when(boardS.MyArchCount("testUser")).thenReturn(5); // 총 아카이브 수

        mvc.perform(get("/user/mypage.do/arch")
                .sessionAttr("user", user)
                .param("page", "1"))
                .andExpect(status().isOk())
                .andExpect(view().name("user/mypage"))
                .andExpect(model().attribute("user", user))
                .andExpect(model().attributeExists("list"))
                .andExpect(model().attributeExists("paging"));
    }

    @Test
    public void testMyPageLike() throws Exception {
        User user = new User();
        user.setUserId("testUser");

        List<Board> list = new ArrayList<>();
        list.add(new Board());
        
        when(session.getAttribute("user")).thenReturn(user);
        when(boardS.selectUserLike("testUser", 1)).thenReturn(list);
        when(boardS.MyLikeCount("testUser")).thenReturn(5); // 총 좋아요 수

        mvc.perform(get("/user/mypage.do/like")
                .sessionAttr("user", user)
                .param("page", "1"))
                .andExpect(status().isOk())
                .andExpect(view().name("user/mypage"))
                .andExpect(model().attribute("user", user))
                .andExpect(model().attributeExists("list"))
                .andExpect(model().attributeExists("paging"));
    }

    @Test
    public void testMyPageCom() throws Exception {
        User user = new User();
        user.setUserId("testUser");
        
        List<Board> list = new ArrayList<>();
        list.add(new Board());
        
        when(session.getAttribute("user")).thenReturn(user);
        when(boardS.selectUserCom("testUser", 1)).thenReturn(list);
        when(boardS.MyComCount("testUser")).thenReturn(5); // 총 댓글 수

        mvc.perform(get("/user/mypage.do/com")
                .sessionAttr("user", user)
                .param("page", "1"))
                .andExpect(status().isOk())
                .andExpect(view().name("user/mypage"))
                .andExpect(model().attribute("user", user))
                .andExpect(model().attributeExists("list"))
                .andExpect(model().attributeExists("paging"));
    }

    @Test
    public void testMyPagePoint() throws Exception {
        User user = new User();
        user.setUserId("testUser");

        List<Board> list = new ArrayList<>();
        list.add(new Board());
        
        when(session.getAttribute("user")).thenReturn(user);
        when(boardS.selectUserPoint("testUser", 1)).thenReturn(list);
        when(boardS.MyPointCount("testUser")).thenReturn(5); // 총 포인트 수

        mvc.perform(get("/user/mypage.do/point")
                .sessionAttr("user", user)
                .param("page", "1"))
                .andExpect(status().isOk())
                .andExpect(view().name("user/mypage"))
                .andExpect(model().attribute("user", user))
                .andExpect(model().attributeExists("list"))
                .andExpect(model().attributeExists("paging"));
    }

    @Test
    public void testMyPageInquiry() throws Exception {
        User user = new User();
        user.setUserId("testUser");

        List<Board> list = new ArrayList<>();
        list.add(new Board());
        
        when(session.getAttribute("user")).thenReturn(user);
        when(boardS.selectInquiry(1)).thenReturn(list); // 문의 목록
        when(boardS.selectInquiryCount()).thenReturn(5); // 총 문의 수

        mvc.perform(get("/user/mypage.do/inquiry")
                .sessionAttr("user", user)
                .param("page", "1"))
                .andExpect(status().isOk())
                .andExpect(view().name("user/mypage"))
                .andExpect(model().attribute("user", user))
                .andExpect(model().attributeExists("list"))
                .andExpect(model().attributeExists("paging"));
    }

    @Test
    public void testMyPageReport() throws Exception {
        User user = new User();
        user.setUserId("testUser");

        List<ReportCheck> list = new ArrayList<>();
        list.add(new ReportCheck());
        
        when(session.getAttribute("user")).thenReturn(user);
        when(reportS.selectReport(1)).thenReturn(list); // 신고 목록
        when(reportS.reportCount()).thenReturn(5); // 총 신고 수

        mvc.perform(get("/user/mypage.do/report")
                .sessionAttr("user", user)
                .param("page", "1"))
                .andExpect(status().isOk())
                .andExpect(view().name("user/mypage"))
                .andExpect(model().attribute("user", user))
                .andExpect(model().attributeExists("list"))
                .andExpect(model().attributeExists("paging"));
    }

    @Test
    public void testModifyInfo() throws Exception {
        User user = new User();
        user.setUserId("testUser");

        when(session.getAttribute("user")).thenReturn(user);

        mvc.perform(get("/user/modifyInfo.do")
                .sessionAttr("user", user))
                .andExpect(status().isOk())
                .andExpect(view().name("user/modify"))
                .andExpect(model().attribute("user", user));
    }

    @Test
    public void testDeleteInfo() throws Exception {
        User user = new User();
        user.setUserId("testUser");

        when(session.getAttribute("user")).thenReturn(user);

        mvc.perform(get("/user/deleteInfo.do")
                .sessionAttr("user", user))
                .andExpect(status().isOk())
                .andExpect(view().name("user/delete"))
                .andExpect(model().attribute("user", user));
    }

    @Test
    public void testDeleteProcess_Success() throws Exception {
    	MockHttpSession mockSession = new MockHttpSession();
        when(userS.delUser("testUser")).thenReturn(1);
        mvc.perform(get("/user/delete_process.do")
                .param("userId", "testUser")
                .session( mockSession))
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/"));
    }

    @Test
    public void testDeleteProcess_Failure() throws Exception {
    	MockHttpSession mockSession = new MockHttpSession();
    	when(userS.delUser("testUser")).thenReturn(0);
        mvc.perform(get("/user/delete_process.do")
                .param("userId", "testUser")
                .session(mockSession))
                .andExpect(status().isOk())
                .andExpect(view().name("errorPage"))
                .andExpect(model().attribute("message", "Failed to delete user"));
    }

    @Test
    public void testFindMyInfo() throws Exception {
    	mvc.perform(get("/user/findMyInfo.do"))
                .andExpect(status().isOk())
                .andExpect(view().name("user/findMyInfo"));
    }

    @Test
    public void testFoundMyId_Success() throws Exception {
    	MockHttpSession mockSession = new MockHttpSession();
        when(userS.findUserId("testName", "1234567890")).thenReturn("foundUserId");
        mvc.perform(post("/user/findMyId.do")
                .param("userName", "testName")
                .param("userTel", "1234567890")
                .session(mockSession))
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/user/foundMyId.do"));
    }

    @Test
    public void testFoundMyId_Failure() throws Exception {
    	MockHttpSession mockSession = new MockHttpSession();
        when(userS.findUserId("testName", "1234567890")).thenReturn(null);
        mvc.perform(post("/user/findMyId.do")
                .param("userName", "testName")
                .param("userTel", "1234567890")
                .session(mockSession))
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/user/findMyInfo.do"));
    }
    
    @Test
    public void testFoundMyPw() throws Exception {
        MockHttpSession mockSession = new MockHttpSession();
        mockSession.setAttribute("userId", "testUser");

        mvc.perform(get("/user/foundMyPw.do")
                .session(mockSession))
                .andExpect(status().isOk())
                .andExpect(view().name("user/foundMyPw"))
                .andExpect(model().attribute("userId", "testUser"));
    }

    @Test
    public void testFoundMyPw_UserNotFound() throws Exception {
        MockHttpSession mockSession = new MockHttpSession(); // userId가 없는 세션

        mvc.perform(get("/user/foundMyPw.do")
                .session(mockSession))
                .andExpect(status().isOk())
                .andExpect(view().name("errorPage"))
                .andExpect(model().attribute("message", "User not found in session"));
    }

    @Test
    public void testUpdateUserPw_Success() throws Exception {
        String userId = "testUser";
        String newPw = "newPassword";

        when(userS.updateUserPw(userId, newPw)).thenReturn(1); // 비밀번호 업데이트 성공

        MockHttpSession mockSession = new MockHttpSession();
        mvc.perform(post("/user/updateMyPw.do")
                .param("userId", userId)
                .param("newPw", newPw)
                .session(mockSession))
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/user/log	in.do"));
    }

    @Test
    public void testUpdateUserPw_Failure() throws Exception {
        String userId = "testUser";
        String newPw = "newPassword";

        when(userS.updateUserPw(userId, newPw)).thenReturn(0); // 비밀번호 업데이트 실패

        MockHttpSession mockSession = new MockHttpSession();
        mvc.perform(post("/user/updateMyPw.do")
                .param("userId", userId)
                .param("newPw", newPw)
                .session(mockSession))
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/user/findMyInfo.do"))
                .andExpect(request().sessionAttribute("errorMessage", "비밀번호 업데이트에 실패하였습니다."));
    }
}
