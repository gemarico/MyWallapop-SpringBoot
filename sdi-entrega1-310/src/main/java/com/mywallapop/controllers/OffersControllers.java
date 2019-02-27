package com.mywallapop.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
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
		offersService.addOffer(offer,activeUser);
		return "redirect:/home";
	}

	

}
