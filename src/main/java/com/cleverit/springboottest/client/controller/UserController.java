package com.cleverit.springboottest.client.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.cleverit.springboottest.client.model.user.User;
import com.cleverit.springboottest.client.service.UserClient;

@Controller
public class UserController {

	@Autowired
	private UserClient userClient;

	@RequestMapping("/") // when this url is requested
	public String home() {
        System.out.println("home");
		return "home";  // home.html is loaded
	}
	
    @GetMapping("/test1")
    public String getAll(Model model){
    	
        model.addAttribute("users", userClient.getUsers());
        System.out.println("getAll");
        System.out.println(model.toString());
        return "test1";
    }
    
    @GetMapping("/test3")
    public String getAllAjax(){
    	
        System.out.println("getAll-Ajax");

        return "test3";
    }
    
    @GetMapping("/user")
    public String serverUser(@RequestParam (value = "id", required = false) Long id, Model model) {
        System.out.println("server-user");
        
        User user;
        
        if (id == null) {
        	System.out.println("id is null");
        	user = new User();
        } else {
        	user = userClient.getUser(id);
        	System.out.println("id is " + id);
        	System.out.println(user.getName());
        }

        model.addAttribute("user", user);
                
        return "form";
    }
    
    @PostMapping("/save")
    public String update(@RequestParam Long id, @Valid User user, BindingResult result) {
        System.out.println("save");
        
        if(result.hasErrors()) {
        	System.out.println("form with errors");

        	//model.addAttribute("user", user);

        	return "form";
        }
        
        if (id == 0) {
        	System.out.println("id == null post");
        	userClient.postUser(user);
        } else {
        	System.out.println("id <> null put");
        	userClient.update(id, user);
        }
        System.out.println("saved");
        return "redirect:/test1";
    }
    
    @RequestMapping("/delete")
    public String delete(@RequestParam("id") Long id){
    	System.out.println("delete "  + id);
        userClient.delete(id);
        return "redirect:/test1";
    }

}
