package com.skilldistillery.atlas.services;

import java.util.List;

import com.skilldistillery.atlas.entities.VisitComment;

public interface VisitCommentService {
	public List<VisitComment> findVisitsCommentsByVisitId(int id);
	public VisitComment createVisitCommentForVisit(VisitComment vc, int id);
	public boolean deleteVisitComment(int id);
	public VisitComment updateVisitComment(VisitComment vc, int vcid, int vid);
	
}
