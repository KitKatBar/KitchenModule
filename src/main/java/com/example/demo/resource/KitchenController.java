package com.example.demo.resource;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class KitchenController {
    
	@Autowired
	KitchenServiceImpl kService;
	
	@GetMapping("/register-page=1")
	public String showForm(Model model) {
		Kitchen kitchen = new Kitchen();
		model.addAttribute("kitchen", kitchen);
		return "kitchen_register_form_1";
	}
	
	@PostMapping("/register-page=1")
	public String submitForm(@Valid @ModelAttribute("kitchen") Kitchen kitchen,
			BindingResult bindingResult, RedirectAttributes redirectAttributes) {
		
		if (bindingResult.hasErrors())
			return "kitchen_register_form_1";
		else {
			kService.saveKitchen(kitchen);
			redirectAttributes.addFlashAttribute("kitchen", kitchen);
			return "redirect:/register-page=2";
		}
	}
	
	@GetMapping("/register-page=2")
	public String showForm2(@ModelAttribute("kitchen") Kitchen kitchen, Model model) {
		//Kitchen k = (Kitchen) model.getAttribute("kitchen");
		//model.addAttribute("kitchen", kitchen);
		System.out.println("Name: " + kitchen.getName());
		System.out.println("Email: " + kitchen.getEmail());
		System.out.println("Password: " + kitchen.getPassword());
		return "kitchen_register_form_2";
	}
	
	@PostMapping("/register-page=2")
	public String submitForm2(@ModelAttribute("kitchen") Kitchen kitchen,
			BindingResult bindingResult, RedirectAttributes redirectAttributes) {
		//Kitchen kitchen = (Kitchen) model.getAttribute("kitchen");
		System.out.println("Name: " + kitchen.getName());
		System.out.println("Email: " + kitchen.getEmail());
		System.out.println("Password: " + kitchen.getPassword());
		redirectAttributes.addFlashAttribute("kitchen", kitchen);
		if (bindingResult.hasErrors())
			return "kitchen_register_form_2";
		else {
			//kService.saveKitchen(kitchen);
			//redirectAttributes.addFlashAttribute("kitchen", kitchen);
			return "redirect:/?success";
		}
	}
}
