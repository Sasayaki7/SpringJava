package com.sasayaki7.lookify.services;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.sasayaki7.lookify.models.Song;
import com.sasayaki7.lookify.repository.LookifyRepository;

@Service
public class LookifyService {
	private final LookifyRepository lookRepo;
	public LookifyService(LookifyRepository lookRepo) {
		this.lookRepo = lookRepo;
	}
	
	public List<Song> allSongs(){
		return lookRepo.findAll();
	}
	
	public Song create(Song s) {
		return lookRepo.save(s);
	}

	public Song findSong(Long id) {
		Optional<Song> optionalSong = lookRepo.findById(id);
		if(optionalSong.isPresent()) {
			return optionalSong.get();
		}
		else {
			return null;
		}
	}
	
	public Song updateSong(Song s) {
		return lookRepo.save(s);
	}
	
	
	public void deleteSong(Long id) {
		lookRepo.deleteById(id);
	}
	
	public List<Song> searchSongs(String artist){
		return lookRepo.findByArtist(artist);
	}
	
	public List<Song> topTen(){
		return lookRepo.findTop10ByOrderByRatingDesc();
	}	
}
