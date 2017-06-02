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
	
	//URLs
	private static final String URL_CREATE = "/create";
	private static final String URL_EDIT = "/edit";
	private static final String URL_DELETE = "/delete";
	
	//Request Parameters
	private static final String PARAM_SYMBOL = "symbol";
	
	//Views
	private static final String V_WATCHLIST = "watchlist";
	private static final String V_WATCHLIST_REDIRECT = "redirect:/admin/watchlist";
	private static final String V_WATCHLIST_EDIT = "watchlist-edit";
	private static final String V_WATCHLIST_EDIT_REDIRECT = "redirect:/admin/watchlist/edit";
	
	//Model Attributes
	private static final String DTO_WATCHLIST = "watchlistForm";
	private static final String WATCHLIST_COLLECTION = "watchlists";
	
	private static Logger logger = LoggerFactory.getLogger(WatchlistController.class);

	@Autowired
	private WatchlistRepository watchlistRepository;
	
	@Autowired
	private WatchlistMapper mapper;
	
	@RequestMapping(method = RequestMethod.GET)
	public String doGetWatchlist(Model model) {
		
		//Display all watchlists
		List<Watchlist> watchlists = (List<Watchlist>) watchlistRepository.findAll();
		model.addAttribute(WATCHLIST_COLLECTION, watchlists);
		
		//Let Users add new symbols at the same page
		WatchlistForm watchlistForm = new WatchlistForm();
		model.addAttribute(DTO_WATCHLIST, watchlistForm);
		logger.info(watchlistForm.toString());
		
		return V_WATCHLIST;
	}
	
	@RequestMapping(value = URL_CREATE, method = RequestMethod.POST)
	public String doPostCreateWatchlist(@ModelAttribute(DTO_WATCHLIST) @Valid WatchlistForm watchlistForm, BindingResult result) {
		
		if (result.hasErrors()) {
			return V_WATCHLIST_REDIRECT;
		}
		
		//Map DTO to the domain class to save new record and save
		Watchlist watchlist = mapper.map(watchlistForm);
		watchlistRepository.save(watchlist);
		return V_WATCHLIST_REDIRECT;
	}
	
	@RequestMapping(value = URL_EDIT, method = RequestMethod.GET)
	public ModelAndView doGetEditWatchlist(@RequestParam(value = PARAM_SYMBOL, required = true) String symbol) {
		
		ModelAndView modelAndView = new ModelAndView(V_WATCHLIST_EDIT);
		Watchlist watchlist = watchlistRepository.findBySymbol(symbol);
		WatchlistForm watchlistForm = mapper.map(watchlist);
		modelAndView.addObject(watchlistForm);
		
		return modelAndView;
	}
	
	@RequestMapping(value = URL_EDIT, method = RequestMethod.POST)
	public String doPostEditWatchlist(@ModelAttribute(DTO_WATCHLIST) @Valid WatchlistForm watchlistForm, BindingResult result) {
		
		if (result.hasErrors()) {
			return V_WATCHLIST_EDIT_REDIRECT;
		}
		
		//Map DTO to the domain class to save new record and save
		Watchlist watchlist = mapper.map(watchlistForm);
		watchlistRepository.save(watchlist);
		return V_WATCHLIST_REDIRECT;
	}
	
	@RequestMapping(value = URL_DELETE, method = RequestMethod.GET)
	public String doGetDeleteWatchlist(@RequestParam(value = PARAM_SYMBOL, required = true) String symbol) {
		
		Watchlist watchlist = new Watchlist();
		watchlist.setSymbol(symbol);
		watchlistRepository.delete(watchlist);
		
		return V_WATCHLIST_REDIRECT;
	}
	
}






























