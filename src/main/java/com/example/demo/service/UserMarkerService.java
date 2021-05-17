package com.example.demo.service;

import java.util.List;
import java.util.Map;

import com.example.demo.data.UserMarker;

public interface UserMarkerService {

	UserMarker create(UserMarker input);

	void update(String userEmail, UserMarker update);

	List<UserMarker> getAllUserMarkersByPerimeter(Map<String, String> attr, int page, int size);
	
	
}
