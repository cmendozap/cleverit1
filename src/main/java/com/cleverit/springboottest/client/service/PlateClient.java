package com.cleverit.springboottest.client.service;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.cleverit.springboottest.client.model.plate.Plate;


@Component
public class PlateClient {
    public final String SERVICE_URL = "http://jsonverserverclever.azurewebsites.net/LicensePlate";

    private RestTemplate restTemplate = new RestTemplate();

    //get all plates
    public List<Plate> getPlates() {
    	List<Plate> plates = Arrays.stream(restTemplate.getForObject(SERVICE_URL, Plate[].class)).collect(Collectors.toList());
    	System.out.println("getPlates 1");
    	for (Plate plate : plates) {
    		System.out.println(plate.getLicensePlate());
    	}
    	System.out.println("getPlates 2");
    	System.out.println(plates.toString());
        return plates;
    }
}
