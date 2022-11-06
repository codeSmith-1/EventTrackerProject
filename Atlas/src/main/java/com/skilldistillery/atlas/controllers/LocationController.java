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

import com.skilldistillery.atlas.entities.Location;
import com.skilldistillery.atlas.services.LocationService;

@RequestMapping("api")
@RestController
public class LocationController {

	@Autowired
	private LocationService locSvc;

	@GetMapping("locations")
	public List<Location> listLocations() {
		return locSvc.listAllLocations();
	}

	@PostMapping("locations")
	public Location createLocation(@RequestBody Location location, HttpServletRequest req, HttpServletResponse res) {
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

	@GetMapping("locations/{id}")
	public Location showLocation(@PathVariable int id, HttpServletResponse res) {
		Location loc = locSvc.showLocation(id);
		if (loc != null) {
			res.setStatus(200);
		} else {
			res.setStatus(404);
		}
		return loc;
	}

	@DeleteMapping("locations/{id}")
	public void deleteLocation(@PathVariable int id, HttpServletResponse res) {
		try {
			if (locSvc.deleteLocation(id)) {
				res.setStatus(204);
			} else {
				res.setStatus(404);
			}
		} catch (Exception e) {
			res.setStatus(400);
			e.printStackTrace();
		}
	}
	
	@PutMapping("locations/{id}")
	public Location updateLocation(@PathVariable int id, @RequestBody Location loc, HttpServletResponse res) {
		try {
			loc = locSvc.updateLocation(id, loc);
			res.setStatus(200);
		} catch (Exception e) {
			e.printStackTrace();
			res.setStatus(400);
			loc = null;
		}
		return loc;
	}

	
}
