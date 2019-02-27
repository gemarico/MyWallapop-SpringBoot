package com.mywallapop.validators;

import org.springframework.stereotype.Component;
import org.springframework.validation.*;

import com.mywallapop.entities.Offer;
import com.mywallapop.entities.User;

@Component
public class AddOfferFormValidator implements Validator {
	
	
	@Override
	public boolean supports(Class<?> aClass) {
		return User.class.equals(aClass);
	}

	@Override
	public void validate(Object target, Errors errors) {
		Offer offer = (Offer) target;
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "title", "Error.empty");
		if (offer.getPrice() < 0 ) {
			errors.rejectValue("score", "Error.score.values");
		}
		if (offer.getDescription().length() < 5 || (offer.getDescription().length() > 24)) {
			errors.rejectValue("description", "Error.offer.description.length");
		}
		
		
	}
}
