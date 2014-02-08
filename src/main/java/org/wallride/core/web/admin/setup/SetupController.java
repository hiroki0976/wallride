package org.wallride.core.web.admin.setup;

import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.wallride.core.service.SetupService;

import javax.inject.Inject;
import javax.validation.Valid;

@Controller @Lazy
@RequestMapping("/setup")
public class SetupController {
	
	@Inject
	private SetupService setupService;
	
	@ModelAttribute("form")
	public SetupForm setupForm() {
		return new SetupForm();
	}
	
	@RequestMapping(method=RequestMethod.GET)
	public String setup() {
		return "/setup";
	}
	
	@RequestMapping(method=RequestMethod.POST)
	public String save(
			@Valid @ModelAttribute("form") SetupForm form,
			BindingResult result,
			RedirectAttributes redirectAttributes) {
		if (result.hasErrors()) {
			return "setup";
		}
		setupService.setup(form.buildSetupRequest(), result);
		return "redirect:/_admin/login";
	}
}
