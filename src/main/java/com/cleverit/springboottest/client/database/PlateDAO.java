package com.cleverit.springboottest.client.database;

import java.util.List;

import com.cleverit.springboottest.client.model.plate.Plate;

public interface PlateDAO {
	String storeAll(List<Plate> plates);

}
