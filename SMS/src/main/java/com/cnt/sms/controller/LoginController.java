package com.cnt.sms.controller;

import java.io.UnsupportedEncodingException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.cnt.sms.dao.UserDAO;
import com.cnt.sms.model.User;
import com.cnt.sms.service.LoginService;

@Controller
public class LoginController {

	private static final Logger logger = LoggerFactory
			.getLogger(LoginController.class);
	
	@Autowired	
	private LoginService service;
	private UserDAO userDAO = new UserDAO();
	
	@RequestMapping(value = "/loginController/login.do", method = RequestMethod.POST)
	public String login(HttpServletRequest request)
			throws UnsupportedEncodingException {
		
		request.setCharacterEncoding("utf-8");
		String userID = request.getParameter("userID");
		String userPassword = request.getParameter("userPassword");
		
		User user = null;
		try {
			user = userDAO.login(userID, userPassword);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		logger.info("유저아이디 : " + userID);
		if (user == null) {
				return "alert";
		} else {
			HttpSession session = request.getSession();
			session.setMaxInactiveInterval(60*60);
			request.getSession().setAttribute("userSession", user);
			request.getSession().setAttribute("teamName", user.getTeamName());
			request.getSession().setAttribute("id", user.getUserID());
			return "NewFile";
		}
	}
	
	@RequestMapping(value = "/loginController/signupPage.do")
	public String signupPage(HttpServletRequest request)
			throws UnsupportedEncodingException {
			return "signup";
	}
	
	@RequestMapping(value = "/loginController/signup.do", method = RequestMethod.POST)
	public String signup(HttpServletRequest request)
			throws UnsupportedEncodingException {
		request.setCharacterEncoding("utf-8");
		String userID = request.getParameter("userID");
		String teamName = request.getParameter("teamName");
		String userPassword = request.getParameter("userPassword");
		
		try {
		service.signup(userID, teamName, userPassword);
		} catch(Exception e) {
			e.printStackTrace();
		}
			return "redirect:/";
	}
	
	
	@RequestMapping(value = "/loginController/logout", method = RequestMethod.GET)
	public String logout(HttpServletRequest request) {
		request.getSession().removeAttribute("userSession");
		return "redirect:/";
	}
}