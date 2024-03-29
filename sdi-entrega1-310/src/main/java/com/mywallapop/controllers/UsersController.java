package com.mywallapop.controllers;

import java.security.Principal;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.*;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import com.mywallapop.entities.Message;
import com.mywallapop.entities.Offer;
import com.mywallapop.entities.User;
import com.mywallapop.services.ConversationsService;
import com.mywallapop.services.OffersService;
import com.mywallapop.services.PurchaseService;
import com.mywallapop.services.SecurityService;
import com.mywallapop.services.UsersService;
import com.mywallapop.validators.SignUpFormValidator;

@Controller
public class UsersController {
	
	private static final Logger logger = LogManager.getLogger(UsersController.class);

	@Autowired
	private UsersService usersService;

	@Autowired
	private OffersService offersService;

	@Autowired
	private PurchaseService purchaseService;

	@Autowired
	private ConversationsService conversService;

	@Autowired
	private SignUpFormValidator signUpFormValidator;

	@Autowired
	private SecurityService securityService;

	@RequestMapping("/user/list")
	public String getListado(Model model, Principal principal) {
		User user = usersService.getUserByEmail(principal.getName());
		model.addAttribute("usersList", usersService.getUsers(user));
		return "user/list";
	}

	@RequestMapping("/user/delete")
	public String delete(@RequestParam("id") String[] ids) {
		usersService.deleteUser(ids);
		return "redirect:/user/list";
	}

	@RequestMapping(value = "/signup", method = RequestMethod.POST)
	public String signup(@Validated User user, BindingResult result) {
		signUpFormValidator.validate(user, result);
		if (result.hasErrors()) {
			return "signup";
		}

		usersService.addUser(user);
		securityService.autoLogin(user.getEmail(), user.getPasswordConfirm());
		logger.debug(String.format("Auto login %s successfully!", user.getEmail()));
		return "redirect:home";
	}

	@RequestMapping(value = "/signup", method = RequestMethod.GET)
	public String signup(Model model) {
		model.addAttribute("user", new User());
		return "signup";
	}

	@RequestMapping(value = "/home", method = RequestMethod.GET)
	public String home(Model model) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String email = auth.getName();
		User activeUser = usersService.getUserByEmail(email);
		model.addAttribute("credits", activeUser.getCredits());
		model.addAttribute("offerList", offersService.getOffersCreated(activeUser));
		model.addAttribute("purchasedList", purchaseService.getOffersPurchased(activeUser));
		model.addAttribute("completeName", activeUser.getFullName());
		return "home";
	}

	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String login(Model model) {
		return "login";
	}

	@RequestMapping(value = "/user/addmessage/{id}", method = RequestMethod.GET)
	public String getmessage(Model model, @PathVariable Long id) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String email = auth.getName();
		User activeUser = usersService.getUserByEmail(email);
		model.addAttribute("offer", offersService.getOffer(id));
		model.addAttribute("message", new Message());
		model.addAttribute("user", activeUser);
		return "user/addmessage";
	}

	@RequestMapping(value = "/user/conver/{id}", method = RequestMethod.POST)
	public String conver(Model model, Message message, BindingResult result, @PathVariable Long id) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		User activeUser = usersService.getUserByEmail(auth.getName());
		Offer offer = offersService.getOffer(id);
		conversService.addMessage(offer, activeUser, message);
		logger.debug(String.format("Message from %s to %s successfully!", activeUser.getEmail(), offer.getUser().getEmail()));
		return "user/conver";
	}

	@RequestMapping(value = "/user/conver/{id}", method = RequestMethod.GET)
	public String getconver(Model model, @PathVariable Long id) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String email = auth.getName();
		User activeUser = usersService.getUserByEmail(email);
		model.addAttribute("message", new Message());
		model.addAttribute("user", activeUser);
		model.addAttribute("offer", conversService.getConver(id).getOffer());
		model.addAttribute("messagesList", conversService.getMessages(conversService.getConver(id), activeUser));
		return "user/conver";
	}

	@RequestMapping(value = "/user/addmessage/{id}", method = RequestMethod.POST)
	public String addmessage(Model model, Message message, BindingResult result, @PathVariable Long id) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		User activeUser = usersService.getUserByEmail(auth.getName());
		Offer offer = offersService.getOffer(id);
		conversService.addMessage(offer, activeUser, message);
		logger.debug(String.format("Message from %s to %s successfully!", activeUser.getEmail(), offer.getUser().getEmail()));
		return "redirect:/user/messagelist";
	}

	@RequestMapping(value = "/user/messagelist", method = RequestMethod.GET)
	public String listmessage(Model model) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String email = auth.getName();
		User activeUser = usersService.getUserByEmail(email);
		model.addAttribute("user", activeUser);
		model.addAttribute("recievedList", conversService.getConversation(activeUser));
		return "user/messagelist";
	}

	@RequestMapping("/user/conver/delete/{id}")
	public String deleteConver(@PathVariable String id) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String email = auth.getName();
		User activeUser = usersService.getUserByEmail(email);
		conversService.delete(Long.parseLong(id));
		logger.debug(String.format("Conversation from %s deleted successfully!",activeUser.getEmail() ));
		return "redirect:/user/messagelist";
	}

}