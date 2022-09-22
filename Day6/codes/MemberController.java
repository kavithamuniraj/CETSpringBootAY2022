package com.sbstransit.LP03;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class MemberController {
	
	@Autowired
	private MemberRepository memberRepository;

	@GetMapping("/members")
	public String viewMembers(Model model) {

		List<Member> listMembers = memberRepository.findAll();
		model.addAttribute("listMembers", listMembers);
		return "view_members";

	}


	// View single item
	@GetMapping("/members/{id}")
	public String viewSingleItem(@PathVariable("id") Integer id, Model model) {

		Member singleMember = memberRepository.getById(id);
		model.addAttribute("member", singleMember);

		return "view_member_details";
	}

	// add
	@GetMapping("/members/add")
	public String addMember(Model model) {
		model.addAttribute("member", new Member());
		return "add_member";
	}

	@PostMapping("/members/save")
	public String saveMember(Member member, RedirectAttributes redirectAttribute) {
		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		
		String encodedPassword = passwordEncoder.encode(member.getPassword());
		member.setPassword(encodedPassword);
		member.setRole("ROLE_USER");
		
		redirectAttribute.addFlashAttribute("success", "Member registered");
		memberRepository.save(member);
		return "redirect:/members";
	}

	// edit
	@GetMapping("/members/edit/{id}")
	public String editMember(@PathVariable("id") Integer id, Model model) {

		Member member = memberRepository.getById(id);
		model.addAttribute("member", member);

		return "edit_member";
	}

	@PostMapping("/members/edit/{id}")
	public String saveUpdatedCategory(@PathVariable("id") Integer id, Member member) {

		memberRepository.save(member);
		return "redirect:/members";
	}

	// delete

	@GetMapping("/members/delete/{id}")
	public String deleteCategory(@PathVariable("id") Integer id) {

		memberRepository.deleteById(id);
		return "redirect:/members";
	}

}
