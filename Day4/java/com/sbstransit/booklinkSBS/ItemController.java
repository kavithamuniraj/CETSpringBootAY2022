package com.sbstransit.booklinkSBS;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

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
	public String saveItem(Item item, @RequestParam("itemImage")  MultipartFile itemImage) {
		
		String imageName = itemImage.getOriginalFilename();
		
		item.setImgName(imageName);
		
		Item savedItem =	itemRepository.save(item);
		
		try {
			String uploadDir = "uploads/items/" + savedItem.getId();
			Path uploadPath = Paths.get(uploadDir);
			
			if (!Files.exists(uploadPath)) {
				//if the folder path does not exist create the directory
				Files.createDirectories(uploadPath);			
			}
			
			//creates a path with the image name
			Path fileToCreatePath = uploadPath.resolve(imageName);
			
			//copies the file from the input to the output path and replaces the existing file if it is exists
			Files.copy(itemImage.getInputStream(), fileToCreatePath, StandardCopyOption.REPLACE_EXISTING);
			
		} catch(IOException io) {
			io.printStackTrace();
			
		}
		return "redirect:/items";
	}
	
	@GetMapping("/search")
	public String searchItems(Model model, String searchStr) {
		
		//http:localhost:8080/search?searchStr=and
		
		List<Item> listOfItems = itemRepository.searchItems(searchStr);
		
		model.addAttribute("listItems", listOfItems);
		
		return "view_items";
	}
	
	

}
