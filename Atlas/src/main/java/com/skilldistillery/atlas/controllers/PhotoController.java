package com.skilldistillery.atlas.controllers;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.skilldistillery.atlas.entities.Photos;
import com.skilldistillery.atlas.services.PhotoService;

@RestController
@RequestMapping("api")
public class PhotoController {
	
	@Autowired
	private PhotoService pSvc;
	
	@GetMapping("locations/{id}/visits/{vid}/photos")
	public List<Photos> getPhotos(@PathVariable int id, @PathVariable int vid, HttpServletResponse res) {
		List<Photos> photos = pSvc.findAllPhotosForVisit(vid);
		if (photos.isEmpty()) {
			res.setStatus(404);
			return null;
		} else {
			res.setStatus(200);
			return photos;
		}
	}
	
	@DeleteMapping("visits/{vid}/photos/{pid}")
	public void deletePhoto(@PathVariable int vid, @PathVariable int pid, HttpServletResponse res) {
		try {
			if (pSvc.deletePhoto(pid, vid)) {
				res.setStatus(204);
			} else {
				res.setStatus(404);
			}
		} catch (Exception e) {
			res.setStatus(400);
			e.printStackTrace();
		}
	}
	
	@PostMapping("visits/{vid}/photos")
	public Photos addPhoto(@RequestBody Photos photo, @PathVariable int vid, HttpServletResponse res, HttpServletRequest req) {
		try {
			photo = pSvc.addPhoto(vid, photo);
			res.setStatus(201);
			StringBuffer url = req.getRequestURL();
			url.append("/").append(photo.getId());
			res.setHeader("Location", url.toString());
		} catch (Exception e) {
			res.setStatus(400);
			photo = null;
			e.printStackTrace();
		}
		return photo;
	}

}
