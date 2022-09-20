package com.sbstransit.booklinkSBS;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class BooklinkController {
	@GetMapping("/")
	public String homeFunc() {
		return "home";
	}

	@GetMapping("/all") 
	public String viewAll()
	{
		return "view_all";
	}
	
	@GetMapping("/aboutus") 
	public String aboutbooklink()
	{
		return "aboutus";
	}
}
