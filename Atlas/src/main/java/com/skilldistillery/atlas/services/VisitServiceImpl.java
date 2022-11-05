package com.skilldistillery.atlas.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.skilldistillery.atlas.entities.Visit;
import com.skilldistillery.atlas.repositories.VisitRepository;

public class VisitServiceImpl implements VisitService {
	
	@Autowired
	private VisitRepository vRepo;

	@Override
	public Visit showVisit(int id) {
		return vRepo.queryById(id);
	}

	@Override
	public List<Visit> showVisits() {
		return vRepo.findAll();
	}

	@Override
	public Visit createVisit(Visit visit) {
		vRepo.saveAndFlush(visit);
		if (vRepo.existsById(visit.getId())) {
			return visit;
		}
		return null;
	}

	@Override
	public Visit updateVisit(Visit visit, int id) {
		Visit update = vRepo.queryById(id);
		if (update != null) {
			update.setDepartureDate(visit.getDepartureDate());
			update.setLocation(visit.getLocation());
			update.setNote(visit.getNote());
			update.setPhoto(visit.getNote());
			update.setArrivalDate(visit.getArrivalDate());
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

}
