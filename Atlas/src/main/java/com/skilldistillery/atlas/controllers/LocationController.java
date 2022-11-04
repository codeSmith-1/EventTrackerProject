package com.skilldistillery.atlas.controllers;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.skilldistillery.atlas.entities.Location;
import com.skilldistillery.atlas.services.LocationService;

@RequestMapping("api")
@RestController
public class LocationController {
	
	@Autowired
	private LocationService locSvc;
	
	@GetMapping("atlas")
	public List<Location> listLocations(){
		return locSvc.listAllLocations();
	}
	
	@GetMapping("atlas")
	public Location showLocation(@RequestBody Location location, HttpServletRequest req, HttpServletResponse res){
		try {
			location = locSvc.createLocation(location);
			res.setStatus(201);
			StringBuffer url = req.getRequestURL();
			url.append("/").append(location.getId());
			res.setHeader("Location", url.toString());
		} catch (Exception e) {
			e.printStackTrace();
			res.setStatus(400);
			location = null;
		}
		
		return location;
	}
	@PostMapping("atlas/{id}")
	public Location showLocation(@PathVariable int id, HttpServletResponse res){
		return locSvc.showLocation(id);
	}
}
