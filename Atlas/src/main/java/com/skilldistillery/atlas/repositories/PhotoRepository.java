package com.skilldistillery.atlas.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.skilldistillery.atlas.entities.Photo;

public interface PhotoRepository extends JpaRepository<Photo, Integer>{
	public Photo queryById(int id);
	public List<Photo> findByVisitId(int id);
}
