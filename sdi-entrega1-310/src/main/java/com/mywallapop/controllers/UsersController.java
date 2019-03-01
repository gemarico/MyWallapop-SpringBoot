package com.mywallapop.controllers;

import java.security.Principal;

import org.springframework.beans.factory.annotation.*;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import com.mywallapop.entities.User;
import com.mywallapop.services.SecurityService;
import com.mywallapop.services.UsersService;
import com.mywallapop.validators.SignUpFormValidator;

@Controller
public class UsersController {
	@Autowired
	private UsersService usersService;

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
		model.addAttribute("offerList", activeUser.getOffers());
		model.addAttribute("purchasedList", activeUser.getPurchased());
		model.addAttribute("completeName", activeUser.getFullName());
		return "home";
	}

	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String login(Model model) {
		return "login";
	}
}