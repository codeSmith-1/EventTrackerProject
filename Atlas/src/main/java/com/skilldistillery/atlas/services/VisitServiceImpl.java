package com.skilldistillery.atlas.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.skilldistillery.atlas.entities.Location;
import com.skilldistillery.atlas.entities.Visit;
import com.skilldistillery.atlas.repositories.LocationRepository;
import com.skilldistillery.atlas.repositories.VisitRepository;

@Service
public class VisitServiceImpl implements VisitService {
	
	@Autowired
	private VisitRepository vRepo;
	
	@Autowired 
	private LocationRepository lRepo;

	@Override
	public Visit showVisit(int id) {
		return vRepo.queryById(id);
	}

	@Override
	public List<Visit> showVisits() {
		return vRepo.findAll();
	}

//	@Override
//	public Visit createVisit(Visit visit) {
//		vRepo.saveAndFlush(visit);
//		if (vRepo.existsById(visit.getId())) {
//			return visit;
//		}
//		return null;
//	}

	@Override
	public Visit updateVisit(Visit visit, int lid, int vid) {
		Visit update = vRepo.queryById(vid);
		if (update != null) {
			update.setDepartureDate(visit.getDepartureDate());
			update.setLocation(visit.getLocation());
			update.setNote(visit.getNote());
			update.setPhoto(visit.getNote());
			update.setArrivalDate(visit.getArrivalDate());
			update.setLocation(lRepo.queryById(lid));
			vRepo.saveAndFlush(update);
		}
		return update;
	}

	@Override
	public boolean deleteVisit(int id) {
		Visit v = vRepo.queryById(id);
		if (v != null) {
			vRepo.delete(v);
			return true;
		}
		return false;
	}

	@Override
	public Visit addVisitToLocation(int id, Visit visit) {
		Location loc = lRepo.queryById(id);
		if (loc == null) {
			return null;
		}
		visit.setLocation(loc);
		vRepo.saveAndFlush(visit);
		return visit;
	}

	@Override
	public List<Visit> findVisitsByLocationId(int lid) {
		if (!lRepo.existsById(lid)) {
			return null;
		}
		return vRepo.findByLocationId(lid);
	}

}
