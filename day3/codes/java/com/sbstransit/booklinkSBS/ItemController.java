package com.sbstransit.booklinkSBS;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class ItemController {
	
	@Autowired
	private ItemRepository itemRepository;
	
	@GetMapping("/items")
	public String viewItems(Model model) {
		
		List<Item> listOfItems = itemRepository.findAll();
		
		model.addAttribute("listItems", listOfItems);
		
		return "view_items";
	}
	
	@GetMapping("/items/add")
	public String addItem(Model model) {
		model.addAttribute("items", new Item());
		return "add_item";
	}
	
	@PostMapping("items/save")
	public String saveItem(Item item) {
		itemRepository.save(item);
		return "redirect:/items";
	}

}
