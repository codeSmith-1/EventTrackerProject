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

import com.skilldistillery.atlas.entities.VisitComment;
import com.skilldistillery.atlas.services.VisitCommentService;

@RequestMapping("api")
@RestController
public class VisitCommentController {

	@Autowired
	private VisitCommentService vcs;
	
	@GetMapping("visits/{id}/comments")
	public List<VisitComment> showCommentsForVisit(@PathVariable int id, HttpServletResponse res){
		List<VisitComment> comments = vcs.findVisitsCommentsByVisitId(id);
		if (comments == null) {
			res.setStatus(404);
		}
		return comments;
	}
	
	@PostMapping("visits/{id}/comments")
	public VisitComment createComment(@RequestBody VisitComment vc, @PathVariable int id, HttpServletRequest req, HttpServletResponse res) {
		try {
			vc = vcs.createVisitCommentForVisit(vc, id);
			res.setStatus(201);
			StringBuffer url = req.getRequestURL();
			url.append("/").append(vc.getId());
			res.setHeader("Location", url.toString());
		} catch (Exception e) {
			res.setStatus(400);
			vc = null;
			e.printStackTrace();
		}
		return vc;
		
	}
}
