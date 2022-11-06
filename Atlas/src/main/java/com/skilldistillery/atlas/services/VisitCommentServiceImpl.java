package com.skilldistillery.atlas.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.skilldistillery.atlas.entities.Visit;
import com.skilldistillery.atlas.entities.VisitComment;
import com.skilldistillery.atlas.repositories.VisitCommentRepository;

public class VisitCommentServiceImpl implements VisitCommentService {

	@Autowired
	private VisitCommentRepository vcr;
	
	@Autowired
	private VisitService vSvc;
	
	@Override
	public List<VisitComment> findVisitsCommentsByVisitId(int id) {
		return vcr.findByVisitId(id);
	}

	@Override
	public VisitComment createVisitCommentForVisit(VisitComment vc, int id) {
		Visit v = vSvc.showVisit(id);
		if (v != null) {
			vc.setVisit(v);
			vcr.saveAndFlush(vc);
			return vc;
		}
		return null;
	}

	@Override
	public boolean deleteVisitComment(int id) {
		VisitComment vComm = vcr.queryById(id);
		if (vComm != null) {
			vcr.delete(vComm);
			return true;
		}
		return false;
	}


	@Override
	public VisitComment updateVisitComment(VisitComment vc, int vcid) {
		VisitComment vComm = vcr.queryById(vcid);
		if (vComm != null) {
			vComm.setComment(vc.getComment());
			vComm.setDate(vc.getDate());
			vComm.setVisit(vc.getVisit());
			vcr.saveAndFlush(vComm);
			return vComm;
		}
		return null;
	}

}
