package com.skilldistillery.atlas.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.skilldistillery.atlas.entities.Visit;

public interface VisitRepository extends JpaRepository<Visit, Integer>{
	public Visit queryById(int id);

}
