package com.sbstransit.booklinkSBS;



import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class CategoryController {
	
	@Autowired
	private CategoryRepository categoryRepository;
	
	@GetMapping("/categories")
	public String viewCategories(Model model) {
		
		List<Category> listOfCategories = categoryRepository.findAll();
		
		model.addAttribute("listCategory", listOfCategories);
		
		return "view_categories";
	}
	
	@GetMapping("/categories/add")
	public String addCategory(Model model) {
		model.addAttribute("category", new Category());
		return "add_category";
	}
	
	@PostMapping("categories/save")
	public String saveCategory(Category category) {
		categoryRepository.save(category);
		return "redirect:/categories";
	}
	
	

}
