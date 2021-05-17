package com.example.demo.rest;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.data.UserMarker;
import com.example.demo.service.UserMarkerService;

@RestController
public class UserMarkerController {

	private UserMarkerService userMarkerService;

	@Autowired
	public void setUserMakerService(UserMarkerService userMarkerService) {
		this.userMarkerService = userMarkerService;
	}

	@RequestMapping(
			path = "/markers",
			method = RequestMethod.POST,
			produces = MediaType.APPLICATION_JSON_VALUE,
			consumes = MediaType.APPLICATION_JSON_VALUE)
	public UserMarker create(@RequestBody UserMarker input) {
		return this.userMarkerService.create(input);
	}

	@RequestMapping(
			path = "/markers/{userEmail}",
			method = RequestMethod.PUT,
			consumes = MediaType.APPLICATION_JSON_VALUE)
	public void updateMarker(
			@PathVariable("userEmail") String userEmail,
			@RequestBody UserMarker update) {
		this.userMarkerService.update(userEmail, update);
	}
	
	@RequestMapping(path = "/markers",
			method = RequestMethod.GET,
			produces = MediaType.APPLICATION_JSON_VALUE)
	public UserMarker[] getAllUserMarkersByPerimeter(
			@RequestParam(required = true) Map<String, String> attr,
			@RequestParam(name = "page", required = false, defaultValue = "0") int page,
			@RequestParam(name = "size", required = false, defaultValue = "10") int size) {
		return this.userMarkerService.getAllUserMarkersByPerimeter(attr, page, size)
				.toArray(new UserMarker[0]);
	}
}
