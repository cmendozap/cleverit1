package com.cleverit.springboottest.client.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import org.springframework.web.bind.annotation.RequestMapping;
import com.cleverit.springboottest.client.database.PlateRepository;
import com.cleverit.springboottest.client.model.plate.Plate;
import com.cleverit.springboottest.client.service.PlateClient;

@Controller
public class PlateController {

	@Autowired
	private PlateClient plateClient;
	
	@Autowired
	private PlateRepository plateRepository;

	@RequestMapping("/test2") // when this url is requested
	public String test2() {
        System.out.println("test2");
		return "test2";  // home.html is loaded
	}
	
    @RequestMapping("/process")
    public String getProcesing(Model model){
    	
    	List<Plate> plates = plateClient.getPlates();
		
        //model.addAttribute("count", plates.size());

        return plateRepository.storeAll(plates);
    }
    


}
