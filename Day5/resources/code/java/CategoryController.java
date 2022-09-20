package e62f.bobochan.lp03;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class CategoryController {

	@Autowired
	private CategoryRepository categoryRepository;

	@GetMapping("/categories")
	public String viewCategories(Model model) {

		List<Category> listCategories = categoryRepository.findAll();
		model.addAttribute("listCategories", listCategories);
		return "view_categories";

	}

	@GetMapping("/snack")
	public String viewSnacks(Model model) {
		Category cat = categoryRepository.findByName("Snacks");
		model.addAttribute("cat", cat);
		return "view_snack";
	}

	// add
	@GetMapping("/categories/add")
	public String addCategory(Model model) {
		model.addAttribute("category", new Category());
		return "add_category";
	}

	@PostMapping("/categories/save")
	public String saveCategory(Category category) {
		categoryRepository.save(category);
		return "redirect:/categories";
	}

	// edit

	@GetMapping("/categories/edit/{id}")
	public String editCategory(@PathVariable("id") Integer id, Model model) {

		Category category = categoryRepository.getById(id);
		model.addAttribute("category", category);

		return "edit_category";
	}

	@PostMapping("/categories/edit/{id}")
	public String saveUpdatedCategory(@PathVariable("id") Integer id, Category category) {

		categoryRepository.save(category);
		return "redirect:/categories";
	}

	// delete

	@GetMapping("/categories/delete/{id}")
	public String deleteCategory(@PathVariable("id") Integer id) {

		categoryRepository.deleteById(id);
	

		return "redirect:/categories";
	}

}
