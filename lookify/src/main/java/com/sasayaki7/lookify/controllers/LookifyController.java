package com.sasayaki7.lookify.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.sasayaki7.lookify.models.Song;
import com.sasayaki7.lookify.services.LookifyService;

@Controller
public class LookifyController {
	
	private final LookifyService lookServ;
	
	public LookifyController(LookifyService lookServ) {
		this.lookServ=lookServ;
	}
	
	
	@RequestMapping("/")
	public String home() {
		return "home.jsp";
	}
	
	
	@RequestMapping("/dashboard")
	public String dashboard(Model model) {
		List<Song> songs = lookServ.allSongs();
		model.addAttribute("songs", songs);
		return "dashboard.jsp";
	}
	
	@RequestMapping("/songs/new")
	public String newSongForm(Model model) {
		model.addAttribute("song", new Song());
		return "newsongform.jsp";
	}
	
	@RequestMapping(value="/songs/new", method=RequestMethod.POST)
	public String newSong(@Valid @ModelAttribute("song") Song s, BindingResult result ){
		if (result.hasErrors()) {
			return "newsongform.jsp";
		}
		else {
			lookServ.create(s);
			return "redirect:/dashboard";
		}
	}
	
	@RequestMapping("/songs/{id}")
	public String songInfo(@PathVariable("id") Long id, Model model) {
		Song s = lookServ.findSong(id);
		if (s != null) {
			model.addAttribute("song", s);
			return "songinfo.jsp";
		}
		else {
			return "redirect:/dashboard";
		}
	}
	
	@RequestMapping("/search/topTen")
	public String topTen(Model model) {
		List<Song> songList = lookServ.topTen();
		model.addAttribute("songs", songList);
		return "topten.jsp";
	}
	
	@RequestMapping("/search")
	public String searchSong(@RequestParam(value="searchQuery") String searchQuery, Model model) {
		List<Song> songList = lookServ.searchSongs(searchQuery);
		model.addAttribute("artist", searchQuery);
		model.addAttribute("songs", songList);
		return "searchRes.jsp";
	}

}
