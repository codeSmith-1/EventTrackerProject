package com.skilldistillery.atlas.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.skilldistillery.atlas.entities.Location;
import com.skilldistillery.atlas.repositories.LocationRepository;

@Service
public class LocationServiceImpl implements LocationService {
	
	@Autowired
	private LocationRepository locRepo;

	@Override
	public List<Location> listAllLocations() {
		return locRepo.findAll();
	}

	@Override
	public Location showLocation(int id) {
		return locRepo.queryById(id);
	}

	@Override
	public Location createLocation(Location location) {
		return locRepo.saveAndFlush(location);
	}

	@Override
	public boolean deleteLocation(int id) {
		Location loc = locRepo.queryById(id);
		if (loc != null) {
			locRepo.delete(loc);
			return true;
		}
		return false;
	}

	@Override
	public Location updateLocation(int id, Location location) {
		Location update = locRepo.queryById(id);
		if (update != null) {
			update.setCountry(location.getCountry());
			update.setCity(location.getCity());
			update.setImage(location.getImage());
			update.setLattitude(location.getLattitude());
			update.setLongitude(location.getLongitude());
			locRepo.saveAndFlush(update);
		}
		return update;
	}

}
