package com.sasayaki7.lookify.controllers;

import java.util.List;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.sasayaki7.lookify.models.Song;
import com.sasayaki7.lookify.services.LookifyService;

@RestController
public class LookifyApi {
	private final LookifyService lookServ;
	public LookifyApi(LookifyService lookServ) {
		this.lookServ = lookServ;
	}
	
	
	@RequestMapping("/api/songs")
	public List<Song> findAll(){
		return lookServ.allSongs();
	}
	
	@RequestMapping(value="/api/new", method=RequestMethod.POST)
	public Song createSong(@RequestParam(value="title") String title, @RequestParam(value="artist") String artist, @RequestParam(value="rating") Integer rating) {
		Song s = new Song(title, artist, rating);
		return lookServ.create(s);
	}
	
	@RequestMapping(value="/api/songs/{id}", method=RequestMethod.PUT)
	public Song updateSong(@PathVariable("id") Long id, @RequestParam(value="title") String title, @RequestParam(value="artist") String artist, @RequestParam(value="rating") Integer rating) {		
		Song song = lookServ.findSong(id);
		song.setTitle(title);
		song.setArtist(artist);
		song.setRating(rating);
		song.setId(id);
		return lookServ.updateSong(song);
	}
	
	@RequestMapping(value="/api/songs/{id}")
	public Song showSong(@PathVariable("id") Long id) {
		return lookServ.findSong(id);
	}
	
	@RequestMapping(value="/api/songs/{id}", method=RequestMethod.DELETE)
	public void deleteSong(@PathVariable("id") Long id) {
		lookServ.deleteSong(id);
	}
	
	@RequestMapping(value="/api/topten", method=RequestMethod.GET)
	public List<Song> topTen(){
		return lookServ.topTen();
	}	
}
