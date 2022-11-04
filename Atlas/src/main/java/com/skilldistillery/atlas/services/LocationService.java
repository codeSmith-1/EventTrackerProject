package com.skilldistillery.atlas.services;

import java.util.List;

import com.skilldistillery.atlas.entities.Location;

public interface LocationService {
	List<Location> listAllLocations();
	Location showLocation(int id);
	Location createLocation(Location location);
	boolean deleteLocation(int id);
	Location updateLocation(int id, Location location);
}
