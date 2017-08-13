package com.example.service;

import java.util.Arrays;
import java.util.List;

import org.apache.camel.Body;
import org.apache.camel.Header;

import com.example.model.User;

public class HelloService {

	private static List<User> repoUser = Arrays.asList(new User("User 1", 1), new User("User 2", 2));
	
	
	public List<User> getListUser(){
		return repoUser;
	}
	
	public User getUser(@Header("id") Integer id) {
		return repoUser.stream().filter(u -> u.getId()==id).findFirst().get();
	}
	
	public void insertUser(@Body User user) {
		repoUser.add(user);
	}
	
	public void removeUser(@Header("id") Integer id) {
		repoUser.removeIf(u -> u.getId()==id);
		return;
	}
	
	

}
