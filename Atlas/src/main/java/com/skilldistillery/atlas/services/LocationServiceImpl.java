package com.skilldistillery.atlas.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.skilldistillery.atlas.entities.Location;
import com.skilldistillery.atlas.entities.Photos;
import com.skilldistillery.atlas.entities.Visit;
import com.skilldistillery.atlas.repositories.LocationRepository;
import com.skilldistillery.atlas.repositories.VisitRepository;

@Service
public class LocationServiceImpl implements LocationService {

	@Autowired
	private LocationRepository locRepo;

	@Autowired
	private VisitRepository visRepo;

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
			if (loc.getVisits().size() > 0) {
				List<Visit> visits = loc.getVisits();
				for (Visit vis : visits) {
					visRepo.delete(vis);
				}
			}
			if (loc != null) {
				locRepo.delete(loc);
				return true;
			}
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
			update.setLatitude(location.getLatitude());
			update.setLongitude(location.getLongitude());
			update.setArrivalDate(location.getArrivalDate());
			update.setDepartureDate(location.getDepartureDate());
			update.setNote(location.getNote());
			locRepo.saveAndFlush(update);
		}
		return update;
	}

}
