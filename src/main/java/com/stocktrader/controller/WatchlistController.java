package com.stocktrader.controller;

import org.slf4j.LoggerFactory;

import java.util.List;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.stocktrader.domain.Watchlist;
import com.stocktrader.dto.WatchlistForm;
import com.stocktrader.repository.WatchlistRepository;
import com.stocktrader.util.WatchlistMapper;

@Controller
@RequestMapping("/admin/watchlist")
public class WatchlistController {
	
	private static Logger logger = LoggerFactory.getLogger(WatchlistController.class);

	@Autowired
	private WatchlistRepository watchlistRepository;
	
	@Autowired
	private WatchlistMapper mapper;
	
	@RequestMapping(method=RequestMethod.GET)
	public String doGetWatchlist(Model model) {
		
		//Display all watchlists
		List<Watchlist> watchlists = (List<Watchlist>) watchlistRepository.findAll();
		model.addAttribute("watchlists", watchlists);
		
		//Let Users add new symbols at the same page
		WatchlistForm watchlistForm = new WatchlistForm();
		model.addAttribute("watchlistForm", watchlistForm);
		logger.info(watchlistForm.toString());
		
		return "watchlist";
	}
	
	@RequestMapping(value="/create", method=RequestMethod.POST)
	public String doPostCreateWatchlist(@ModelAttribute("watchlistForm") @Valid WatchlistForm watchlistForm, BindingResult result) {
		
		if (result.hasErrors()) {
			return "redirect:/admin/watchlist";
		}
		
		//Map DTO to the domain class to save new record and save
		Watchlist watchlist = mapper.map(watchlistForm);
		watchlistRepository.save(watchlist);
		return "redirect:/admin/watchlist";
	}
	
	@RequestMapping(value="/edit", method=RequestMethod.GET)
	public ModelAndView doGetEditWatchlist(@RequestParam(value="symbol", required=true) String symbol) {
		
		ModelAndView modelAndView = new ModelAndView("watchlist-edit");
		Watchlist watchlist = watchlistRepository.findBySymbol(symbol);
		WatchlistForm watchlistForm = mapper.map(watchlist);
		modelAndView.addObject(watchlistForm);
		
		return modelAndView;
	}
	
	@RequestMapping(value="/edit", method=RequestMethod.POST)
	public String doPostEditWatchlist(@ModelAttribute("watchlistForm") @Valid WatchlistForm watchlistForm, BindingResult result) {
		
		if (result.hasErrors()) {
			return "redirect:/admin/watchlist/edit";
		}
		
		//Map DTO to the domain class to save new record and save
		Watchlist watchlist = mapper.map(watchlistForm);
		watchlistRepository.save(watchlist);
		return "redirect:/admin/watchlist";
	}
	
	@RequestMapping(value="/delete", method=RequestMethod.GET)
	public String doGetDeleteWatchlist(@RequestParam(value="symbol", required=true) String symbol) {
		
		Watchlist watchlist = new Watchlist();
		watchlist.setSymbol(symbol);
		watchlistRepository.delete(watchlist);
		
		return "redirect:/admin/watchlist";
	}
	
}






























