package com.example.demo.resource;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class MenuItemController {

	@Autowired
	MenuItemServiceImpl mService;
	
	@GetMapping("/edit_menu")
	public String editMenu(@ModelAttribute("kitchen") Kitchen k, Model model) {
		MenuItem menu = new MenuItem();
		model.addAttribute("menu", menu);
		return "kitchen_register_form_menu";
	}
	
	@GetMapping("/add_menu_item")
	public String addItem(@ModelAttribute("menu") MenuItem menu, Model model) {
		MenuItem m = new MenuItem();
		m.setId(menu.getId());
		model.addAttribute("menu", m);
		return "kitchen_register_form_menu_add_item";
	}
	
	@RequestMapping("/list_items")
	public String listMenuItems(@ModelAttribute("kitchen") Kitchen k, Model model) {
		List<MenuItem> list = mService.findMenu(k);
		model.addAttribute("items", list);
		return "kitchen_registration_form_2";
	}
}
