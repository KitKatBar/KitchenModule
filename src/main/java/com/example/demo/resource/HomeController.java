package com.example.demo.resource;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class HomeController {

	@Autowired
	KitchenServiceImpl kService;
	
    @GetMapping("/")
    public String root() {
        return "index";
    }
    
    @GetMapping("/login")
    public String login(Model model) {
    	Kitchen k = new Kitchen();
    	model.addAttribute("login", k);
        return "login";
    }
    
    @PostMapping("/login")
    public String loginSubmit(@ModelAttribute("login") Kitchen k, Model model,
    		RedirectAttributes redirectAttributes) {
    	List<Kitchen> list = kService.getKitchenList();
    	//System.out.println("inputted kitchen: " + k.toString());
    	System.out.println("input email: " + k.getEmail());
    	System.out.println("input password: " + k.getPassword());
    	for (int i = 0; i < list.size(); i++) {
    		System.out.println("list of kitchen: " + list.get(i).toString());
    		if (list.get(i).equals(k)) {
    			System.out.println("ID: " + list.get(i).getId());
    			System.out.println("Name: " + list.get(i).getName());
    			System.out.println("Email: " + list.get(i).getEmail());
    			System.out.println("Password: " + list.get(i).getPassword());
    			redirectAttributes.addFlashAttribute("kitchen", list.get(i));
    			return "redirect:/edit_menu";
    		}
    	}
    	
    	return "redirect:/login?error";
    }
}
