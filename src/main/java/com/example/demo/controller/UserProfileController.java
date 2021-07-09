package com.example.demo.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.mapper.UserProfileMapper;
import com.example.demo.model.UserProfile;

@RestController
public class UserProfileController {
	
	private Map<String, UserProfile> userMap;
	
	private UserProfileMapper mapper;
	
	public UserProfileController(UserProfileMapper mapper) {
		// TODO Auto-generated constructor stub
		this.mapper = mapper;
	}
	
	/*
	@PostConstruct
	public void init() {
		userMap = new HashMap<String, UserProfile>();
		
		userMap.put("1", new UserProfile("1", "박제범", "010-1234-1234", "경기도 광명시"));
		userMap.put("2", new UserProfile("2", "박효진", "010-1234-1234", "경기도 고양시"));
		userMap.put("3", new UserProfile("3", "박별", "010-1234-1234", "경기도 고양시"));
		userMap.put("4", new UserProfile("4", "박콩딱", "010-1234-1234", "경기도 고양시"));
	}
	*/

	@GetMapping("/user/{id}")
	public UserProfile getUserProfile(@PathVariable("id") String id) {
		//return userMap.get(id);
		return mapper.getUserProfile(id);
	}
	
	@GetMapping("/user/all")
	public List<UserProfile> getUserAllProfile() {
		//return new ArrayList<UserProfile>(userMap.values());
		return mapper.getUserAllProfile();
	}
	
	@PutMapping("/user/{id}")
	public void putUserProfile(@RequestParam("name") String name, 
			@RequestParam("phone") String phone,
			@RequestParam("address") String address,
			@PathVariable("id") String id) {
		
//		UserProfile userProfile = new UserProfile(id, name, phone, address);
//		userMap.put(id, userProfile);
		
		phone = phone.replaceAll("-", "");
		
		mapper.insertUserProfile(id, name, phone, address);
	}
	
	@PostMapping("/user/{id}")
	public void postUserPorfile(@PathVariable("id") String id,
			@RequestParam("name") String name, 
			@RequestParam("phone") String phone,
			@RequestParam("address") String address) {
		
//		UserProfile userProfile = userMap.get(id);
//		
//		userProfile.setName(name);
//		userProfile.setPhone(phone);
//		userProfile.setAddress(address);
		
		phone = phone.replaceAll("-", "");
		
		mapper.updateUserProfile(id, name, phone, address);
	}
	
	@DeleteMapping("/user/{id}")
	public void deleteUserProfile(@PathVariable("id") String id) {
		if(mapper.getUserProfile(id) != null) {
			//userMap.remove(id);
			mapper.deleteUserProfile(id);
		}
	}
}
