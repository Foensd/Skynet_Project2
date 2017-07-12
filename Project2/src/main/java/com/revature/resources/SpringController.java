package com.revature.resources;

import javax.servlet.http.HttpSession;

//import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.revature.bean.Users;
import com.revature.service.Register;

@RestController
public class SpringController {
	
	// has to accept 
	@RequestMapping(headers="Accept=application/json", value="/play.do", method = RequestMethod.POST)
	public String registerUser(Users user, BindingResult bindingResult, ModelMap modelMap, HttpSession session){
		Register r = new Register();
		
		if(r.createUser(user.getUsername())){
		session.setAttribute("username", user.getUsername());
		session.setAttribute("role", user.getRole());
		session.setAttribute("status", user.getStatus());
		
		System.out.println("Creating User " + user.getUsername());
		
		return "lobby";
		
		
		}else{
			
			return "login";
			
		}
		
	}
	
	//-------------------Create a User--------------------------------------------------------
    
    /*@RequestMapping(value = "/user/", method = RequestMethod.POST)
    public String registerUser(Users user, BindingResult bindingResult, ModelMap modelMap, HttpSession session){
        System.out.println("Creating User " + user.getUsername());
  
        if (userService.isUserExist(user)) {
            System.out.println("A User with name " + user.getUsername() + " already exist");
            return new ResponseEntity<Void>(HttpStatus.CONFLICT);
        }
  
        userService.saveUser(user);
  
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(ucBuilder.path("/user/{id}").buildAndExpand(user.getId()).toUri());
        return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
    }*/
	
	
}
