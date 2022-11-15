package com.skilldistillery.atlas.services;

import java.util.List;

import com.skilldistillery.atlas.entities.Photos;

public interface PhotoService {
	public Photos addPhoto(int vid, Photos photo);
	public boolean deletePhoto(int pid, int vid);
	public Photos updatePhoto(Photos photo, int vid, int pid);
	public List<Photos> findAllPhotosForVisit(int vid);
	public Photos showPhoto(int id);
}
