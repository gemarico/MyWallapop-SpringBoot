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
import com.mywallapop.services.PurchaseService;
import com.mywallapop.services.UsersService;
import com.mywallapop.validators.AddOfferFormValidator;

@Controller
public class OffersControllers {

	@Autowired
	private OffersService offersService;

	@Autowired
	private UsersService usersService;

	@Autowired
	private PurchaseService purchaseService;

	@Autowired
	private AddOfferFormValidator addOfferFormValidator;

	@RequestMapping(value = "/offer/add")
	public String getOffer(Model model) {
		model.addAttribute("offer", new Offer());
		return "offer/add";
	}

	@RequestMapping(value = "/offer/add", method = RequestMethod.POST)
	public String setOffer(Model model, @Validated Offer offer, BindingResult result, @RequestParam(value = "id", required=false) String id) {
		addOfferFormValidator.validate(offer, result);
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		User activeUser = usersService.getUserByEmail(auth.getName());
		if (result.hasErrors()) {
			return "offer/add";
		}

		offersService.addOffer(offer, activeUser, id);
		return "redirect:/home";
	}

	@RequestMapping("/offer/delete/{id}" )
	public String deleteMark(@PathVariable Long id) {
		offersService.deleteOffer(id);
		return "redirect:/home";
	}

	@RequestMapping(value = "/catalogue", method = RequestMethod.GET)
	public String showCatalogue(Model model, Pageable pageable,
			@RequestParam(value = "", required = false) String searchText) {

		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		User activeUser = usersService.getUserByEmail(auth.getName());
		Page<Offer> offers = new PageImpl<Offer>(new LinkedList<Offer>());
		Page<Offer> flash = new PageImpl<Offer>(new LinkedList<Offer>());
		if (searchText != null && !searchText.isEmpty()) {
			offers = offersService.searchOffersByTitle(pageable, searchText, activeUser);
			flash = offersService.getFlashOffers(pageable, activeUser);
		} else {
			offers = offersService.getOffers(pageable, activeUser);
			flash = offersService.getFlashOffers(pageable, activeUser);
		}

		model.addAttribute("offerList", offers);
		model.addAttribute("flashList", flash);
		model.addAttribute("List", offers);
		model.addAttribute("page", offers);
		model.addAttribute("user", activeUser);

		return "catalogue";
	}

	@RequestMapping(value = "/catalogue/buyOffer/{id}", method = RequestMethod.POST)
	public String buyOffer(Model model, @PathVariable Long id) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		User activeUser = usersService.getUserByEmail(auth.getName());
		purchaseService.buyOffer(activeUser, offersService.getOffer(id));
		return "redirect:/catalogue/update";

	}

	@RequestMapping(value = "/catalogue/buyFlash/{id}", method = RequestMethod.POST)
	public String buyFlash(Model model, @PathVariable Long id) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		User activeUser = usersService.getUserByEmail(auth.getName());
		purchaseService.buyOffer(activeUser, offersService.getOffer(id));
		return "redirect:/catalogue/update/flash";

	}

	@RequestMapping("/catalogue/update")
	public String updateCatalogue(Model model, Pageable pageable) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		User activeUser = usersService.getUserByEmail(auth.getName());
		model.addAttribute("offerList", offersService.getOffers(pageable, activeUser));
		model.addAttribute("user", activeUser);
		return "catalogue :: tableOffers";

	}

	@RequestMapping("/catalogue/update/flash")
	public String updateFlash(Model model, Pageable pageable) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		User activeUser = usersService.getUserByEmail(auth.getName());
		model.addAttribute("flashList", offersService.getFlashOffers(pageable, activeUser));
		model.addAttribute("user", activeUser);
		return "catalogue :: tableFlash";

	}

}
