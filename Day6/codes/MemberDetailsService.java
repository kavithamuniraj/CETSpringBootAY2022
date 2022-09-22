package com.sbstransit.LP03;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;


public class MemberDetailsService implements UserDetailsService{

	@Autowired
	MemberRepository memberRepository;
	// MemberDetails Service loads the username only if the username exists in the database
		
	@Override
	public MemberDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		Member member = memberRepository.findByUsername(username);
		if (member== null) {
			throw new UsernameNotFoundException("Could not find user");
		}
		
		//if username exists, then new MemberDetails object is created.
		return new MemberDetails(member);
	}
	
	
}
