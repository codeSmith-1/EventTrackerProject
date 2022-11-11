package com.skilldistillery.atlas.services;

import java.util.List;

import com.skilldistillery.atlas.entities.Photo;

public interface PhotoService {
	public Photo addPhoto(int vid, Photo photo);
	public boolean deletePhoto(int pid, int vid);
	public Photo updatePhoto(Photo photo, int vid, int pid);
	public List<Photo> findAllPhotosForVisit(int vid);
	public Photo showPhoto(int id);
}
