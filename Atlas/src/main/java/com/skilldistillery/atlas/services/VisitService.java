package com.skilldistillery.atlas.services;

import java.util.List;

import com.skilldistillery.atlas.entities.Visit;

public interface VisitService {
	public Visit showVisit(int id);
	public List<Visit> showVisits();
	public Visit updateVisit(Visit visit, int vid);
	public boolean deleteVisit(int id);
	public Visit addVisitToLocation(int id, Visit visit);
	
	List<Visit> findVisitsByLocationId(int id);
}
