package com.example.demo.validations;

import org.springframework.stereotype.Component;

import com.example.demo.data.UserMarker;

@Component
public class Validator {

	public boolean validateMarker(UserMarker marker) {
		return marker != null && marker.getEmail() != null && !marker.getEmail().isEmpty()
				&& marker.getLocation() != null && marker.getLocation().getLat() != 0
				&& marker.getLocation().getLng() != 0;
	}
	
	public boolean validateCreateMarker(UserMarker marker) {
		return marker != null && marker.getEmail() != null && !marker.getEmail().isEmpty()
		/* && marker.getLocation() != null */;
	}

	public boolean validateMarkerLocation(UserMarker update) {
		return update.getLocation() != null && update.getLocation().getLat() != 0
				&& update.getLocation().getLng() != 0;
	}

	public boolean validateMarkerIcon(UserMarker update) {
		return update.getIcon() >= 0;
	}
}