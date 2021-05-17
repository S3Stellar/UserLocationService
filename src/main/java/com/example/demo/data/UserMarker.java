package com.example.demo.data;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "Markers")
public class UserMarker {
	@Id
	private String email;
	private Location location;
	// if user wants to be on agent mode
	private boolean visibility;
	private boolean isConnected;
	private int icon;

	public UserMarker() {
		super();
	}

	public UserMarker(String email, Location location, boolean visibility, boolean isConnected, int icon) {
		super();
		this.email = email;
		this.location = location;
		this.visibility = visibility;
		this.isConnected = isConnected;
		this.icon = icon;
	}

	
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Location getLocation() {
		return location;
	}

	public void setLocation(Location location) {
		this.location = location;
	}

	public boolean isVisibility() {
		return visibility;
	}

	public void setVisibility(boolean visibility) {
		this.visibility = visibility;
	}

	public int getIcon() {
		return icon;
	}

	public void setIcon(int icon) {
		this.icon = icon;
	}

	public boolean isConnected() {
		return isConnected;
	}

	public void setConnected(boolean isConnected) {
		this.isConnected = isConnected;
	}

	@Override
	public String toString() {
		return "UserMarker [email=" + email + ", location=" + location + ", visibility=" + visibility + ", isConnected="
				+ isConnected + ", icon=" + icon + "]";
	}

}
