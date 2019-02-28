package com.mywallapop.controllers;

import java.util.LinkedList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.mywallapop.entities.Offer;
import com.mywallapop.entities.User;
import com.mywallapop.services.OffersService;
import com.mywallapop.services.UsersService;
import com.mywallapop.validators.AddOfferFormValidator;

@Controller
public class OffersControllers {

	@Autowired
	private OffersService offersService;

	@Autowired
	private UsersService usersService;

	@Autowired
	private AddOfferFormValidator addOfferFormValidator;

	@RequestMapping(value = "/offer/add")
	public String getOffer(Model model) {
		model.addAttribute("offer", new Offer());
		return "offer/add";
	}

	@RequestMapping(value = "/offer/add", method = RequestMethod.POST)
	public String setOffer(Model model, @Validated Offer offer, BindingResult result) {
		addOfferFormValidator.validate(offer, result);
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		User activeUser = usersService.getUserByEmail(auth.getName());
		if (result.hasErrors()) {
			return "offer/add";
		}
		offersService.addOffer(offer, activeUser);
		return "redirect:/home";
	}

	@RequestMapping("/offer/delete/{id}")
	public String deleteMark(@PathVariable Long id) {
		offersService.deleteOffer(id);
		return "redirect:/home";
	}

	@RequestMapping(value = "/catalogue", method = RequestMethod.GET )
	public String showCatalogue(Model model,Pageable pageable , @RequestParam(value = "", required = false) String searchText) {
		Page<Offer> offers = new PageImpl<Offer>(new LinkedList<Offer>());
		if (searchText != null && !searchText.isEmpty()) {
			offers = offersService.searchOffersByTitle(pageable, searchText);
		} else {
			offers = offersService.getOffers(pageable);
		}

		model.addAttribute("offerList", offers);
		model.addAttribute("page", offers);
		return "catalogue";
	}

	@RequestMapping(value = "/catalogue/buy/{id}", method = RequestMethod.GET)
	public String buyOffer(Model model, @PathVariable Long id) {
		offersService.buyOffer(true, id);
		return "redirect:/catalogue";
	}

	@RequestMapping("/catalogue/update")
	public String updateCatalogue(Model model, Pageable pageable) {
		model.addAttribute("offerList", offersService.getOffers(pageable));
		return "catalogue :: tableOffers";
	}

}
