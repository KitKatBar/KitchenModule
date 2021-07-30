package com.example.demo.resource;

import java.security.Principal;
import java.util.List;

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
	
	@ModelAttribute("kitchen_dto")
	public KitchenRegistrationDto kitchenDto() {
		return new KitchenRegistrationDto();
	}
	
	@GetMapping("/register-page=1")
	public String showForm(@ModelAttribute("kitchen_dto") KitchenRegistrationDto kitchenDto) {
		/*Kitchen k = new Kitchen();
		List<Kitchen> list = kService.getKitchenList();
		for (int i = 0; i < list.size(); i++) {
			if (list.get(i).equals(kitchen)) {
				k = list.get(i);
				break;
			}
		}*/
		//Kitchen kitchen = new Kitchen();
		//model.addAttribute("kitchen", kitchen);
		//Kitchen k = (Kitchen) model.getAttribute("kitchen");
		//kitchen.setId(kService.getKitchenId(kitchen));
		//System.out.println("id in page 1: " + k.getId());
		//System.out.println("id in page 1: " + kitchen.getId());
		return "kitchen_register_form_1";
	}
	
	@PostMapping("/register-page=1")
	public String submitForm(@Valid @ModelAttribute("kitchen_dto") KitchenRegistrationDto kitchenDto,
			Model model, BindingResult bindingResult, RedirectAttributes redirectAttributes) {
		
		if (bindingResult.hasErrors())
			return "kitchen_register_form_1";
		else {
			kService.save(kitchenDto);
			Kitchen k = new Kitchen();
			List<Kitchen> list = kService.getKitchenList();
			for (int i = 0; i < list.size(); i++) {
				if (list.get(i).getEmail().equals(kitchenDto.getEmail())) {
					k = list.get(i);
					//System.out.println("Id:" + k.getId());
					//System.out.println("Name: " + k.getName());
					//System.out.println("Email: " + k.getEmail());
					//System.out.println("Password: " + k.getPassword());
					break;
				}
			//model.addAttribute("kitchen", k);
			redirectAttributes.addFlashAttribute("kitchen", k);
			}
			return "redirect:/register-page=2";
		}
	}
	
	@GetMapping("/register-page=2")
	public String showForm2(Principal principal, Model model) {
		String email = principal.getName();
		//System.out.println("email is: " + email);
		Kitchen k = new Kitchen();
		List<Kitchen> list = kService.getKitchenList();
		for (int i = 0; i < list.size(); i++) {
			if (list.get(i).getEmail().equals(email)) {
				k = list.get(i);
				model.addAttribute("kitchen", k);
				break;
			}
		}
		//Kitchen k = (Kitchen) model.getAttribute("kitchen");
		//Kitchen k = (Kitchen) redirectAttributes.getAttribute("kitchen");
		//Kitchen k = new Kitchen();
		//k = kService.findKitchen(kitchen.getId());
		//model.addAttribute("kitchen", kitchen);
		//redirectAttributes.addFlashAttribute("kitchen", kitchen);
		//System.out.println("Id:" + kitchen.getId());
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
			BindingResult bindingResult, RedirectAttributes redirectAttributes) {
		//Kitchen kitchen = (Kitchen) model.getAttribute("kitchen");
		//redirectAttributes.addFlashAttribute("kitchen", kitchen);
		if (bindingResult.hasErrors())
			return "kitchen_register_form_2";
		else {
			//kService.saveKitchen(kitchen);
			//redirectAttributes.addFlashAttribute("kitchen", kitchen);
			System.out.println("id before pass: " + kitchen.getId());
			//System.out.println("Name: " + kitchen.getName());
			//System.out.println("Email: " + kitchen.getEmail());
			//System.out.println("Password: " + kitchen.getPassword());
			//request.getSession().setAttribute("kitchen", null);
			//request.removeAttribute("kitchen", WebRequest.SCOPE_SESSION);
			//status.setComplete();
			return "redirect:/register-page=2?saved";
		}
	}
}
