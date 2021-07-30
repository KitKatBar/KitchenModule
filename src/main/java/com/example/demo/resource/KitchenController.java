package com.example.demo.resource;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
//@Transactional
@SessionAttributes("kitchen")
public class KitchenController {
    
	@Autowired
	KitchenServiceImpl kService;
	
	@ModelAttribute("kitchen")
	public Kitchen kitchen() {
		return new Kitchen();
	}
	
	@GetMapping("/register-page=1")
	public String showForm(@ModelAttribute("kitchen") Kitchen kitchen) {
		//Kitchen kitchen = new Kitchen();
		//model.addAttribute("kitchen", kitchen);
		return "kitchen_register_form_1";
	}
	
	@PostMapping("/register-page=1")
	public String submitForm(@Valid @ModelAttribute("kitchen") Kitchen kitchen,
			BindingResult bindingResult) {
		
		if (bindingResult.hasErrors())
			return "kitchen_register_form_1";
		else {
			kService.saveKitchen(kitchen);
			//redirectAttributes.addFlashAttribute("kitchen", kitchen);
			return "redirect:/register-page=2";
		}
	}
	
	@GetMapping("/register-page=2")
	public String showForm2(@ModelAttribute("kitchen") Kitchen kitchen) {
		//Kitchen k = (Kitchen) model.getAttribute("kitchen");
		//Kitchen k = new Kitchen();
		//k = kService.findKitchen(kitchen.getId());
		//model.addAttribute("kitchen", kitchen);
		//redirectAttributes.addFlashAttribute("kitchen", kitchen);
		//System.out.println("Name: " + kitchen.getName());
		//System.out.println("Email: " + kitchen.getEmail());
		//System.out.println("Password: " + kitchen.getPassword());
		//System.out.println("Name: " + k.getName());
		//System.out.println("Email: " + k.getEmail());
		//System.out.println("Password: " + k.getPassword());
		return "kitchen_register_form_2";
	}
	
	@PostMapping("/register-page=2")
	public String submitForm2(@ModelAttribute("kitchen") Kitchen kitchen, SessionStatus status,
			BindingResult bindingResult) {
		//Kitchen kitchen = (Kitchen) model.getAttribute("kitchen");
		//redirectAttributes.addFlashAttribute("kitchen", kitchen);
		if (bindingResult.hasErrors())
			return "kitchen_register_form_2";
		else {
			//kService.saveKitchen(kitchen);
			//redirectAttributes.addFlashAttribute("kitchen", kitchen);
			//System.out.println("Name: " + kitchen.getName());
			//System.out.println("Email: " + kitchen.getEmail());
			//System.out.println("Password: " + kitchen.getPassword());
			//request.getSession().setAttribute("kitchen", null);
			//request.removeAttribute("kitchen", WebRequest.SCOPE_SESSION);
			status.setComplete();
			return "redirect:/?success";
		}
	}
}
