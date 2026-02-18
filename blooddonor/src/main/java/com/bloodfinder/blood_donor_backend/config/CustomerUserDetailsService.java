package com.bloodfinder.blood_donor_backend.config;


import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.bloodfinder.blood_donor_backend.entity.Donor;
import com.bloodfinder.blood_donor_backend.repository.DonorRepository;

@Service
public class CustomerUserDetailsService implements UserDetailsService{
	
	private DonorRepository donorRepository;

	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		
		Donor donor = donorRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
		
		return User.builder()
				.username(donor.getName())
				.password(donor.getPassword())
				.roles(donor.getRole().name())
				.build();
	}

}
