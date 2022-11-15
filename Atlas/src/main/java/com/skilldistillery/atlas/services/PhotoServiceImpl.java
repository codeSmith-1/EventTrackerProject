package com.skilldistillery.atlas.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.skilldistillery.atlas.entities.Photos;
import com.skilldistillery.atlas.entities.Visit;
import com.skilldistillery.atlas.repositories.PhotoRepository;
import com.skilldistillery.atlas.repositories.VisitRepository;
@Service
public class PhotoServiceImpl implements PhotoService {
	
	@Autowired
	private PhotoRepository pRepo;
	@Autowired
	private VisitRepository vRepo;

	@Override
	public Photos addPhoto(int vid, Photos photo) {
		Visit vis = vRepo.queryById(vid);
		if (photo != null) {
			photo.setVisit(vis);
			pRepo.saveAndFlush(photo);
			return photo;
		}
		return null;
	}

	@Override
	public boolean deletePhoto(int pid, int vid) {
		Photos photo = pRepo.queryById(pid);
		if (photo != null) {
			pRepo.delete(photo);
			return true;
		}
		return false;
	}

	@Override
	public Photos updatePhoto(Photos photo, int vid, int pid) {
		Photos update = pRepo.queryById(pid);
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
	public List<Photos> findAllPhotosForVisit(int vid) {
		return pRepo.findByVisitId(vid);
	}

	@Override
	public Photos showPhoto(int id) {
		return pRepo.queryById(id);
	}

}
