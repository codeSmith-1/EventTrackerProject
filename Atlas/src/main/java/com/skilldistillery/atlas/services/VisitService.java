package com.skilldistillery.atlas.services;

import java.util.List;

import com.skilldistillery.atlas.entities.Visit;

public interface VisitService {
	public Visit showVisit(int id);
	public List<Visit> showVisits();
	public Visit createVisit(Visit visit);
	public Visit updateVisit(Visit visit, int id);
	public boolean deleteVisit(int id);
	
}
