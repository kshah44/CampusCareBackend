package com.ssdi.campuscare.controller;

import org.json.JSONObject;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;


@CrossOrigin(origins = "*")
@RestController
public class AdminController {
	
	
@RequestMapping(method=RequestMethod.POST, value="/loginadmin")
	
	public String verifyLogin(@RequestBody String admin ) {
		JSONObject obj = new JSONObject(admin);
		
		if(obj.get("username").equals("admin") && obj.get("password").equals("admin")) {
			
			JSONObject obj_success = new JSONObject();
			obj_success.put("username", "admin");
			obj_success.put("password", "admin");
			return obj_success.toString();
			
		}else {
			JSONObject obj_faliure = new JSONObject();
			obj_faliure.put("username", "error");
			obj_faliure.put("password", "error");
			return obj_faliure.toString();
			
		}
		
		
		
	}

}
