package com.example.demo.dao;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import com.example.demo.data.UserMarker;

public interface UserMarkerDao extends PagingAndSortingRepository<UserMarker, String>{
	
	public List<UserMarker> findAllByVisibilityAndIsConnectedAndLocation_latBetweenAndLocation_lngBetween(
			@Param("isConnected") boolean isConnected,
			@Param("Visibility") boolean visibility,
			@Param("minLat") double minLat, @Param("maxLat") double maxLat,
			@Param("minLng") double minLng, @Param("maxLng") double maxLng,
			Pageable pageable);

}
