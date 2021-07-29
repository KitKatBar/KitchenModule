package com.example.demo.resource;

import java.util.ArrayList;
import java.util.List;

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
import org.springframework.web.bind.annotation.SessionAttributes;

@Controller
@Transactional
@SessionAttributes("kitchen")
public class MenuItemController {
	
	@Autowired
	MenuItemServiceImpl mService;
	
	@GetMapping("/edit_menu")
	public String editMenu(@ModelAttribute("kitchen") Kitchen kitchen, Model model) {
		//MenuItem menu = new MenuItem();
		//model.addAttribute("menu", menu);
		List<MenuItem> list = mService.findMenu(kitchen);
		model.addAttribute("items", list);
		//System.out.println("Name: " + kitchen.getName());
		//System.out.println("Email: " + kitchen.getEmail());
		//System.out.println("Password: " + kitchen.getPassword());
		return "kitchen_register_form_menu";
	}
	
	@GetMapping("/add_menu_item")
	public String addItem(@ModelAttribute("kitchen") Kitchen kitchen, Model model) {
		MenuItem menu = new MenuItem();
		model.addAttribute("menu", menu);
		//System.out.println("Name: " + kitchen.getName());
		//System.out.println("Email: " + kitchen.getEmail());
		//System.out.println("Password: " + kitchen.getPassword());
		return "kitchen_register_form_menu_add_item";
	}
	
	@PostMapping("/add_menu_item")
	public String saveItem(@ModelAttribute("kitchen") Kitchen kitchen, @Valid @ModelAttribute("menu") MenuItem menu,
			BindingResult bindingResult) {
		System.out.println("ID: " + kitchen.getId());
		System.out.println("Name: " + kitchen.getName());
		System.out.println("Email: " + kitchen.getEmail());
		System.out.println("Password: " + kitchen.getPassword());
		System.out.println("error in savemenu");
		if (bindingResult.hasErrors())
			return "kitchen_register_form_menu_add_item";
		else {
			System.out.println("error in savemenu save");
			//mService.saveMenuItem(kitchen.getId(), menu);
			//List<MenuItem> temp = new ArrayList<MenuItem>();
			//temp = kitchen.getMenu();
			//temp.add(menu);
			//kitchen.setMenu(temp);
			kitchen.addMenuItem(menu);
			mService.saveMenuItem(kitchen.getId(), menu);
			System.out.println("Item Name: " + menu.getItemName());
			System.out.println("Veg: " + menu.isVeg());
			System.out.println("Price: " + menu.getPrice());
			//kitchen.getMenu().add(menu);
			System.out.println("error in savemenu redirect");
			return "redirect:/edit_menu";
		}
		//model.addAttribute("menu", menu);
		//return "kitchen_register_form_menu_add_item";
	}
	
	@RequestMapping("/list_items")
	public String listMenuItems(@ModelAttribute("kitchen") Kitchen k, Model model) {
		List<MenuItem> list = mService.findMenu(k);
		model.addAttribute("items", list);
		return "kitchen_registration_form_2";
	}
}
