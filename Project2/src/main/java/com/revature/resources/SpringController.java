package com.revature.resources;

import javax.servlet.http.HttpSession;

import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.revature.bean.Users;
import com.revature.service.Register;

@RestController
public class SpringController {
	
	//-------------------Create a User-------------------------------------------------------- \\
	@RequestMapping(headers="Accept=application/json", value="/play.do", method = RequestMethod.POST)
	public String registerUser(@RequestBody String username, BindingResult bindingResult, ModelMap modelMap, HttpSession session){
		Register r = new Register();
		System.out.println("TRYING TO CREATE A USER: " + username);
		
		Users user = new Users(username);
		
		if(r.createUser(username)){
		session.setAttribute("username", user.getUsername());
		session.setAttribute("role", user.getRole());
		session.setAttribute("status", user.getStatus());
		
		System.out.println("Created user: " + user.getUsername());
		
		return "lobby";
		
		
		}else{
			
			return "login";
			
		}
		
	}
	
	//-------------------Retrieve All Players--------------------------------------------------------
    
    /*@RequestMapping(value = "/lobby/", method = RequestMethod.GET)
    public ResponseEntity<List<User>> listAllUsers() {
        List<User> users = userService.findAllUsers();
        if(users.isEmpty()){
            return new ResponseEntity<List<User>>(HttpStatus.NO_CONTENT);//You many decide to return HttpStatus.NOT_FOUND
        }
        return new ResponseEntity<List<User>>(users, HttpStatus.OK);
    }*/
	
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
