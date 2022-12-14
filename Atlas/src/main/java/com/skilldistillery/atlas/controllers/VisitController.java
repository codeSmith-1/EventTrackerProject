package com.skilldistillery.atlas.controllers;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.skilldistillery.atlas.entities.Photos;
import com.skilldistillery.atlas.entities.Visit;
import com.skilldistillery.atlas.services.PhotoService;
import com.skilldistillery.atlas.services.VisitService;

@RequestMapping("api")
@RestController
public class VisitController {

	@Autowired
	private VisitService vSvc;
	
	@Autowired
	private PhotoService pSvc;

	@GetMapping("visits")
	public List<Visit> showAll() {
		return vSvc.showVisits();
	}

//	@GetMapping("locations/{id}/visits")
//	public Visit showVisit(@PathVariable int id, HttpServletResponse res) {
//		return vSvc.showVisit(id);
//	}
	
	@GetMapping("locations/{id}/visits")
	public List<Visit> showVisitsByLocationId(@PathVariable int id, HttpServletResponse res) {
		List<Visit> visits = vSvc.findVisitsByLocationId(id);
		if(visits == null) {
			res.setStatus(404);
		}
		res.setStatus(200);
		return visits;
	}
	
	
	@PostMapping("locations/{id}/visits")
	public Visit addVisitToLocation(@RequestBody Visit vis, @PathVariable int id, HttpServletResponse res, HttpServletRequest req) {
		try {
			vis = vSvc.addVisitToLocation(id, vis);
			res.setStatus(201);
			StringBuffer url = req.getRequestURL();
			url.append("/").append(vis.getId());
			res.setHeader("Location", url.toString());
		} catch (Exception e) {
			res.setStatus(400);
			vis = null;
			e.printStackTrace();
		}
		return vis;
	} 
	
	@PutMapping("visits/{vid}")
	public Visit updateVisit(@RequestBody Visit visit, @PathVariable int vid, HttpServletResponse res) {
		try {
			visit = vSvc.updateVisit(visit, vid);
			res.setStatus(201);
		} catch (Exception e) {
			e.printStackTrace();
			res.setStatus(400);
			visit = null;
		}
		return visit;
	}
	
	@DeleteMapping("visits/{vid}")
	public void deleteVisit(@PathVariable int vid, HttpServletResponse res) {
		
		try {
			if (vSvc.deleteVisit(vid)) {
				res.setStatus(204);
			} else {
				res.setStatus(404);
			}
		} catch (Exception e) {
			res.setStatus(400);
			e.printStackTrace();
		}
	}
	
	
}
