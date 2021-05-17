package com.example.demo.logic;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.example.demo.dao.UserMarkerDao;
import com.example.demo.data.Location;
import com.example.demo.data.UserMarker;
import com.example.demo.exceptions.InvalidMarkerAttributes;
import com.example.demo.exceptions.UserMarkerNotFound;
import com.example.demo.service.UserMarkerService;
import com.example.demo.validations.Validator;

@Service
public class UserMarkerServiceWithDB implements UserMarkerService {

	private UserMarkerDao userMarkerDao;
	private Validator validator;

	@Autowired
	public void setUserMarkerDao(UserMarkerDao userMarkerDao) {
		this.userMarkerDao = userMarkerDao;
	}

	@Autowired
	public void setValidator(Validator validator) {
		this.validator = validator;
	}

	@Override
	public UserMarker create(UserMarker input) {

		if (this.userMarkerDao.findById(input.getEmail()).isPresent()) {
			this.update(input.getEmail(), input);
		}

		if (validator.validateCreateMarker(input))
			return this.userMarkerDao.save(input);
		else
			throw new InvalidMarkerAttributes("Invalid marker params");
	}

	@Override
	public void update(String userEmail, UserMarker update) {
		UserMarker oldMarker = this.userMarkerDao.findById(userEmail)
				.orElseThrow(() -> new UserMarkerNotFound("Marker with email" + userEmail + " was not found"));

		if (validator.validateMarkerLocation(update)) {
			oldMarker.setLocation(new Location(update.getLocation().getLat(), update.getLocation().getLng()));
		}

		if (validator.validateMarkerIcon(update)) {
			oldMarker.setIcon(update.getIcon());
		}

		oldMarker.setVisibility(update.isVisibility());
		oldMarker.setConnected(update.isConnected());

		this.userMarkerDao.save(oldMarker);
	}

	@Override
	public List<UserMarker> getAllUserMarkersByPerimeter(Map<String, String> attr, int page, int size) {
		return this.userMarkerDao.findAllByVisibilityAndIsConnectedAndLocation_latBetweenAndLocation_lngBetween(true,
				true, toDouble(attr.get("minLat")), toDouble(attr.get("maxLat")), toDouble(attr.get("minLng")),
				toDouble(attr.get("maxLng")), PageRequest.of(page, size, Direction.ASC));
	}

	public boolean toBoolean(String value) {
		try {
			return Boolean.parseBoolean(value);
		} catch (IllegalArgumentException ex) {
			throw new RuntimeException("Cannot convert from string to boolean");
		}
	}

	public double toDouble(String value) {
		try {
			return Double.parseDouble(value);
		} catch (IllegalArgumentException ex) {
			throw new RuntimeException("Cannot convert from string to double");
		}
	}
}
