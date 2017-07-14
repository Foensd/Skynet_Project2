package com.revature.resources;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;
/*import javax.validation.Valid;*/

import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
/*import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;*/
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
/*import org.springframework.web.bind.annotation.ResponseStatus;*/
import org.springframework.web.bind.annotation.RestController;

import com.revature.bean.Users;
import com.revature.dao.UserDao;
import com.revature.dao.UserDaoImpl;
import com.revature.service.Register;
import com.revature.service.RoleAssig;

@RestController
public class SpringController {

	// -------------------Create a
	// User-------------------------------------------------------- \\
	@RequestMapping(method = RequestMethod.POST, headers = "Accept=application/json", value = "/register.do")
	/*public ResponseEntity<String> registerUser(@RequestBody String jsonObject, HttpSession session) {*/
	public ResponseEntity<Void> registerUser(@RequestBody String jsonObject, HttpSession session) {
		Register r = new Register();
		Users user = null;
		System.out.println("jsonObject: " + jsonObject);
		try {
			user = new ObjectMapper().readValue(jsonObject, Users.class);
		} catch (JsonParseException e) {
			e.printStackTrace();
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		System.out.println("User: " + user);
		/*
		 * {
		 * 	"username": "jdhfsjkh"
		 * }
		 * 
		 */
		
		if (r.createUser(user.getUsername())) {
			session.setAttribute("username", user.getUsername());

			System.out.println("Created user: " + user.getUsername());

			//StringBuilder sb = new StringBuilder();
			/*return ResponseEntity.status(HttpStatus.OK).body(user.getUsername());*/
			return ResponseEntity.status(HttpStatus.OK).body(null);

		} else {

			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);

		}

	}

	// -------------------Retrieve All
	// Players--------------------------------------------------------

	@RequestMapping(value = "/lobby.do", method = RequestMethod.GET)
	@ResponseBody
	public List<String> listAllUsers() {
		System.out.println("Getting a list of users");
		UserDao dao = new UserDaoImpl();
		List<Users> users = dao.getUsers();
		List<String> usernames = new ArrayList<String>();
		for (int i = 0; i < users.size(); i++) {
			usernames.add(users.get(i).getUsername());
		}
		/*
		 * if(users.isEmpty()){ return new
		 * ResponseEntity<List<Users>>(HttpStatus.NO_CONTENT);//You many decide
		 * to return HttpStatus.NOT_FOUND }
		 */

		System.out.println("Successfully got a list of users, returning them to lobbyCtrl.js");
		System.out.println(users);

		return usernames;
	}

	@RequestMapping(method = RequestMethod.POST, headers = "Accept=application/json", value = "/something.do")
	public ResponseEntity<Void> setRoles(@RequestBody String jsonObject, HttpSession session) {
		RoleAssig r = new RoleAssig();
		r.assignRandomRoles();
			
			return ResponseEntity.status(HttpStatus.OK).body(null);


	}

	
//
//
//	@RequestMapping(method = RequestMethod.POST, headers = "Accept=application/json", value = "/something.do")
//	public ResponseEntity<Void> readyButton(@RequestBody String jsonObject, HttpSession session) {
//		
//
//		return ResponseEntity.status(HttpStatus.OK).body(null);
//	}
}
