package com.skilldistillery.atlas.controllers;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.skilldistillery.atlas.entities.Visit;
import com.skilldistillery.atlas.services.VisitService;

@RequestMapping("api")
@RestController
public class VisitController {

	@Autowired
	private VisitService vSvc;

	@GetMapping("visits")
	public List<Visit> showAll() {
		return vSvc.showVisits();
	}

	@GetMapping("visits/{id}")
	public Visit showVisit(@PathVariable int id, HttpServletResponse res) {
		return vSvc.showVisit(id);
	}
	
	@PostMapping("visit")
	public Visit createVisit(@RequestBody Visit vis, HttpServletResponse res, HttpServletRequest req) {
		vis = vSvc.createVisit(vis);
		if (vis != null) {
			res.setStatus(200);
			StringBuffer url = req.getRequestURL();
			url.append("/").append(vis.getId());
			res.setHeader("Location", url.toString());
		} else {
			res.setStatus(400);
			vis = null;
		}
		return vis;
	}
	
	@PutMapping("visit/{id}")
	public Visit updateVisit(@RequestBody Visit visit, @PathVariable int id, HttpServletResponse res) {
		try {
			visit = vSvc.updateVisit(visit, id);
		} catch (Exception e) {
			e.printStackTrace();
			res.setStatus(400);
			visit = null;
		}
		return visit;
	}
}
