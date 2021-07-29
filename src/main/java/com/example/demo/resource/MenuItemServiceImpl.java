package com.example.demo.resource;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MenuItemServiceImpl {

	@Autowired
	KitchenRepo kRepo;
	
	public List<MenuItem> findMenu(Kitchen k) {
		return kRepo.findById(k.getId()).get().getMenu();
	}
	
	public MenuItem findMenuItem(Kitchen k, Long id) {
		MenuItem m = new MenuItem();
		m.setId(id);
		return kRepo.findById(k.getId()).get().getMenu().get(kRepo.findById(k.getId()).get().getMenu().indexOf(m));
	}
	
	public void saveMenuItem(Long id, MenuItem m) {
		Kitchen k = kRepo.findById(id).get();
		//kRepo.findById(id).get().getMenu().add(m);
		kRepo.save(k);
	}
	
	public void deleteMenuItem(Long k_id, Long m_id) {
		Kitchen k = new Kitchen();
		k.setId(k_id);
		MenuItem m = new MenuItem();
		m.setId(m_id);
		kRepo.findById(k_id).get().getMenu().remove(kRepo.findById(k_id).get().getMenu().indexOf(m));
		kRepo.save(k);
	}
}
