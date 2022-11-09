package com.skilldistillery.atlas.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.skilldistillery.atlas.entities.Photo;
import com.skilldistillery.atlas.repositories.PhotoRepository;
import com.skilldistillery.atlas.repositories.VisitRepository;
@Service
public class PhotoServiceImpl implements PhotoService {
	
	@Autowired
	private PhotoRepository pRepo;
	@Autowired
	private VisitRepository vRepo;

	@Override
	public Photo addPhoto(int vid, int pid) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean deletePhoto(int pid, int vid) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Photo updatePhoto(Photo photo, int vid, int pid) {
		Photo update = pRepo.queryById(pid);
		if (update != null) {
			update.setDescription(photo.getDescription());
			update.setPhoto(photo.getPhoto());
			update.setVisit(vRepo.queryById(vid));
			pRepo.saveAndFlush(update);
			return update;
		}
		return null;
	}

	@Override
	public List<Photo> findAllPhotosForVisit(int vid) {
		return pRepo.findByVisitId(vid);
	}

	@Override
	public Photo showPhoto(int id) {
		return pRepo.queryById(id);
	}

}
