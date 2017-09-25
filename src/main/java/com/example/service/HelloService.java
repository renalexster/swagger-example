package com.example.service;

import java.util.ArrayList;
import java.util.List;

import org.apache.camel.Body;
import org.apache.camel.Exchange;
import org.apache.camel.Header;

import com.example.model.User;

public class HelloService {

	private static List<User> repoUser;
	
	static {
		repoUser = new ArrayList<>();
		repoUser.add(new User("User 1", 1));
		repoUser.add(new User("User 2", 2));
	}
	
	public List<User> getListUser(){
		return repoUser;
	}
	
	public User getUser(@Header("id") Integer id) {
		try {
			User ret = repoUser.stream().filter(u -> u.getId()==id).findFirst().get();
			return ret;
		} catch (Exception e) {
			return null;
		}
		
	}
	
	public void insertUser(@Body User user) {
		repoUser.add(user);
	}
	
	public String removeUser(@Header("id") Integer id) {
		String retur = repoUser.removeIf(u -> u.getId()==id) ? "OK": "Ops";
		return retur;
	}
	
	public Object debug(@Body Object payload, Exchange ex) {
		System.out.println(ex.getExchangeId());
		return payload;
	}

}
