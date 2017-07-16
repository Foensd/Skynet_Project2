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
		if (user.getUsername().matches("[a-zA-Z]\\w*") && r.createUser(user.getUsername())) {
			session.setAttribute("username", user.getUsername());

			System.out.println("Created user: " + user.getUsername());

			// StringBuilder sb = new StringBuilder();
			/*
			 * return
			 * ResponseEntity.status(HttpStatus.OK).body(user.getUsername());
			 */
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
		// Users ur = dao.getUserByUsername("test");
		for (Users user : users) {
			if (user.getStatus() == null)
				return new ResponseEntity<String>(HttpStatus.BAD_REQUEST);
		}
		for (Users user : users) {
			if (user.getRole() == null) {
				RoleAssig r = new RoleAssig();
				r.assignRandomRoles();
			}
		}

		Users u = dao.getUserByUsername(currentUser.getUsername());

		return ResponseEntity.status(HttpStatus.OK).body(JSONObject.quote(u.getRole().getDescription()));
	}
	
	@RequestMapping(value = "/getAllUsers.do", method = RequestMethod.GET)
	@ResponseBody
	public List<Users> getAllUserObjects() {
		UserDao dao = new UserDaoImpl();
		List<Users> ul = dao.getUsers();

		return ul;

	}

	@RequestMapping(method = RequestMethod.POST, headers = "Accept=application/json", value = "/action.do")
	public ResponseEntity<String> updateTarget(@RequestBody String jsonObject) {

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

	static String message1 = null;
	static String message2 = null;
	
	@RequestMapping(method = RequestMethod.POST, headers = "Accept=application/json", value = "/nightEnd.do")
	@ResponseBody
	public List<String> NightActions(@RequestBody String jsonObject) {

		UserDao dao = new UserDaoImpl();
		List<Users> users = dao.getActiveUsers();

		List<String> usernames = new ArrayList<String>();
		for (Users user : users) {
			if (user.getRole().getDescription().equals("Hacker")) {
				usernames.add(user.getTargetUser());
			}
		}
		int chosen = (int) (Math.random() * usernames.size());
		if(usernames.size() > 0) {
			dao.changeStatusByUsername(2, usernames.get(chosen));
			if(message1 == null)
				message1 = usernames.get(chosen) + " was fired last night !";
		}

		for (Users user : users) {
			if (user.getRole().getDescription().equals("Trainer")) {
				if(user.getTargetUser() != null) { 
					if (dao.getUserByUsername(user.getTargetUser()).getRole().getDescription().equals("Hacker")) {
	
						dao.changeStatusByUsername(2, user.getTargetUser());
						if(message2 == null)
							message2 = user.getTargetUser() + " was fired last night !";
					}
				}
			}
		}

		for (Users user : users) {
			if (user.getRole().getDescription().equals("HR")) {
				if(user.getTargetUser() != null) {
					if (dao.getUserByUsername(user.getTargetUser()).getStatus().getStatus().equals("Fired")) {
						dao.changeStatusByUsername(1, user.getTargetUser());
						if(message1.matches(user.getTargetUser() + " .*"))
							message1 = "Somebody was fired, however HR stepped in and decided to let them stay";
						if(message2.matches(user.getTargetUser() + " .*"))
							message2 = "Somebody was fired, however HR stepped in and decided to let them stay";
					}
				}
			}
		}
		
		List<String> st = new ArrayList<String>();
		st.add(message1);
		st.add(message2);
		dao.deleteTargets();

		return st;
	}

	@RequestMapping(method = RequestMethod.POST, headers = "Accept=application/json", value = "/trial.do")
	@ResponseBody
	public List<Users> trial(@RequestBody String jsonObject) {
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
		
		UserDao dao = new UserDaoImpl();
		List<Users> users = dao.getActiveUsers();
		int count1 = 0;
		int count2 = 0;
		for (Users us : users) {
			if (us.getTargetUser().equals("guilty")) {
				count1++;
			}else if(us.getTargetUser().equals("innocent")){
				count2++;
			}
		}
		if (count1>count2){
			userDao.changeStatusByUsername(2, user.getUsername());
			users = dao.getActiveUsers();
		}
		dao.deleteTargets();
		return users;
	}
	
	@RequestMapping(value = "/getMostVoted.do", method = RequestMethod.GET)
	@ResponseBody
	public List<Users> getMostVoted(){
		UserDao dao = new UserDaoImpl();
		List<Users> ul = dao.getActiveUsers();
		int[] numberOfVotes = new int[ul.size()];
		for(int i=0; i<ul.size(); i++) {
			for(Users user: ul) {
				if(ul.get(i).getUsername().equals(user.getTargetUser()))
					numberOfVotes[i]++;
			}
		}
		int index = 0;
		int max = numberOfVotes[0];
		for(int i=1; i<numberOfVotes.length; i++) {
			if(numberOfVotes[i] > max) {
				index = i;
				max = numberOfVotes[i];
			}
		}
		
		ul.add(new Users(ul.get(index)));
		dao.deleteTargets();
		return ul;
		
	}
	
	@RequestMapping(value = "/checkWinCondition.do", method = RequestMethod.GET)
	@ResponseBody
	public String checkWinConditions() {
		UserDao dao = new UserDaoImpl();
		List<Users> ul = dao.getActiveUsers();
		String message = null;
		int hackerCount = 0;
		int employeeCount = 0;
		for(Users user: ul) {
			if(user.getRole().getDescription().equals("Hacker"))
				hackerCount++;
			else
				employeeCount++;
		}
		if(hackerCount == 0)
			message = "There are no more hackers, the Employees have won!";
		else if(employeeCount == 0)
			message = "There are no more employees, the Hackers have won!";
		else if(hackerCount >= employeeCount)
			message = "The Hackers have outnumbered the Employees, the Hackers have won!";
		return message;
	}
}
