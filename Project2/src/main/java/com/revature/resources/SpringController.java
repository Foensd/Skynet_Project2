package com.revature.resources;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;
/*import javax.validation.Valid;*/

import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.json.JSONObject;
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
	public ResponseEntity<String> registerUser(@RequestBody String jsonObject, HttpSession session) {
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
		if (user.getUsername().matches("[a-zA-Z]\\w*") && r.createUser(user.getUsername())) {
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
	
	@RequestMapping(method = RequestMethod.POST, headers = "Accept=application/json", value = "/readyButton.do")
	public ResponseEntity<Void> readyButton(@RequestBody String jsonObject, HttpSession session) {
		Users user = null;
		UserDao userDao = new UserDaoImpl();
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
		
		userDao.changeStatusByUsername(1, user.getUsername());
		session.setAttribute("status", "Employed");
		return ResponseEntity.status(HttpStatus.OK).body(null);
	}
	
	@RequestMapping(method = RequestMethod.POST, headers = "Accept=application/json", value = "/allReady.do")
	public ResponseEntity<String> allReady(@RequestBody String jsonObject) {

		Users currentUser = null;
		System.out.println("jsonObject: " + jsonObject);
		try {
			currentUser = new ObjectMapper().readValue(jsonObject, Users.class);
		} catch (JsonParseException e) {
			e.printStackTrace();
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		System.out.println("User: " + currentUser);
		UserDao dao = new UserDaoImpl();
		List<Users> users = dao.getUsers();
		//Users ur = dao.getUserByUsername("test");
		for(Users user: users) {
			if(user.getStatus() == null)
				return new ResponseEntity<String>(HttpStatus.BAD_REQUEST);
		}
		for(Users user: users) {
			if(user.getRole() == null) {
				RoleAssig r = new RoleAssig();
				r.assignRandomRoles();
			}
		}
		
		Users u = dao.getUserByUsername(currentUser.getUsername());
		
		
		return ResponseEntity.status(HttpStatus.OK).body(JSONObject.quote(u.getRole().getDescription()));
	}
	
	
	
	@RequestMapping(method = RequestMethod.POST, headers = "Accept=application/json", value = "/action.do")
	public ResponseEntity<String> NightTarget(@RequestBody String jsonObject) {

		Users currentUser = null;
		System.out.println("jsonObject: " + jsonObject);
		try {
			currentUser = new ObjectMapper().readValue(jsonObject, Users.class);
		} catch (JsonParseException e) {
			e.printStackTrace();
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		UserDao dao = new UserDaoImpl();
		dao.updateUserTarget(currentUser.getUsername(), currentUser.getTargetUser());
		return ResponseEntity.status(HttpStatus.OK).body(null);
	}
	
	@RequestMapping(method = RequestMethod.POST, headers = "Accept=application/json", value = "/nightEnd.do")
	public ResponseEntity<JSONObject> NightActions(@RequestBody String jsonObject) {
		JSONObject messages = new JSONObject();
		String message1 = null;
		String message2 = null;

		UserDao dao = new UserDaoImpl();
		List<Users> users = dao.getImportantUsers();
		for(Users user: users) {
			if(user.getTargetUser() == null)
				return new ResponseEntity<JSONObject>(HttpStatus.BAD_REQUEST);
		}
		
		
		List<String> usernames = new ArrayList<String>();
		for (Users user : users) {
			if (user.getRole().getDescription().equals("Hacker")) {
				usernames.add(user.getTargetUser());
			}
		}
		int chosen = (int) (Math.random() * usernames.size());
		dao.changeStatusByUsername(2, usernames.get(chosen));
		message1 = "1 Employee was fired last night !";

		for (Users user : users) {
			if (user.getRole().getDescription().equals("Trainer")) {
				if (dao.getUserByUsername(user.getTargetUser()).getRole().getDescription().equals("Hacker")) {

					dao.changeStatusByUsername(2, user.getTargetUser());
					message1 = "2 Employees were fired last night !";
				}
			}
		}

		for (Users user : users) {
			if (user.getRole().getDescription().equals("HR")) {
				if (dao.getUserByUsername(user.getTargetUser()).getStatus().getStatus().equals("Fired")) {
					dao.changeStatusByUsername(1, user.getTargetUser());
					message2 = "However HR stepped in and decided to let them stay ! ";
				}
			}
		}
		
		messages.put("message1", message1);
		messages.put("message2", message2);
		
		return ResponseEntity.status(HttpStatus.OK).body(messages);
	}
}
