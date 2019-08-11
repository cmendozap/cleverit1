package com.cleverit.springboottest.client.service;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.cleverit.springboottest.client.model.user.User;

@Component
public class UserClient {
    public final String SERVICE_URL = "http://jsonverserverclever.azurewebsites.net/User";

    private RestTemplate restTemplate = new RestTemplate();

    //get all users
    public List<User> getUsers() {
    	List<User> users = Arrays.stream(restTemplate.getForObject(SERVICE_URL, User[].class)).collect(Collectors.toList());
    	System.out.println("getAllUsers 1");
    	for (User user : users) {
    		System.out.println(user.getName());
    	}
    	System.out.println("getAllUsers 2");
    	System.out.println(users.toString());
        return users;
    }

    //get user
    public User getUser(Long id) {
    	User user = restTemplate.getForObject(SERVICE_URL+"/"+id, User.class);
    	return user;
    }
    
    //create user
    public User postUser(User user) {
        return restTemplate.postForObject(SERVICE_URL, user, User.class);
    }

    //delete user
    public void delete(Long id){
    	System.out.println(SERVICE_URL+"/"+id);
        restTemplate.delete(SERVICE_URL+"/"+id);
    }

    //update user
    public User update(Long id, User user){
        return restTemplate.exchange(SERVICE_URL+"/"+id, HttpMethod.PUT,
                new HttpEntity<>(user), User.class, id).getBody();

    }
}
