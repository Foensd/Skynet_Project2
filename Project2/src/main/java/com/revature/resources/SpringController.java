package com.revature.resources;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.revature.bean.Users;
import com.revature.service.Register;

@Controller
public class SpringController {
	
	@RequestMapping(value="/register",method = RequestMethod.POST)
	public String registerUser(Users user, BindingResult bindingResult, ModelMap modelMap, HttpSession session){
		Register r = new Register();
		
		if(r.createUser(user.getUsername())){
		session.setAttribute("username", user.getUsername());
		session.setAttribute("role", user.getRole());
		session.setAttribute("status", user.getStatus());
		return "lobby";
		
		}else{
			
			return "login";
			
		}
		
		
	
	}
	
	
}
