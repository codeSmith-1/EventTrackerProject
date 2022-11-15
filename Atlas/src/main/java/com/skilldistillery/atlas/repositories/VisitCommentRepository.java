package com.skilldistillery.atlas.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.skilldistillery.atlas.entities.VisitComment;

public interface VisitCommentRepository extends JpaRepository<VisitComment, Integer>{
	public VisitComment queryById(int id);
	public List<VisitComment> findByVisitId(int id);
	
}
