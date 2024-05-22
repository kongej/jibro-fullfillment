package com.jibro.fulfill.service.impl;

import java.security.SecureRandom;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jibro.fulfill.dto.ongoing.OngoingListResponseDto;
import com.jibro.fulfill.entity.Company;
import com.jibro.fulfill.entity.Ongoing;
import com.jibro.fulfill.entity.Order;
import com.jibro.fulfill.repository.CompanyRepository;
import com.jibro.fulfill.repository.OngoingRepository;
import com.jibro.fulfill.repository.OrderRepository;
import com.jibro.fulfill.service.OngoingService;

@Service
public class OngoingServiceImpl implements OngoingService {
	@Autowired
	OngoingRepository ongoingRepository;
	
	@Autowired
	OrderRepository orderRepository;
	
	@Autowired
	CompanyRepository companyRepository;
	
	public OngoingServiceImpl(OngoingRepository ongoingRepository) {
		this.ongoingRepository = ongoingRepository;
	}

	@Override
	public List<OngoingListResponseDto> ongoingList() throws Exception {
		List<Ongoing> ongoings;
		ongoings = this.ongoingRepository.findAll();
		return ongoings.stream().map(ongoing-> new OngoingListResponseDto(ongoing)).collect(Collectors.toList());
	}

	@Override
	public Ongoing ongoingInsert(String orderId) throws Exception {
		Order order = orderRepository.getById(orderId);
		String deliverId = "DA003";
		Company deliver = companyRepository.getById(deliverId);
		
		int length = 16; 
        String invc = generateRandomInvc(length);
        System.out.println("Generated Invoice Number: " + invc);
		
		Ongoing ongoing = Ongoing.builder()
							.order(order)
							.delever(deliver)
							.invc(invc)
							.build();

		this.ongoingRepository.save(ongoing);
		return ongoing;
	}
	
	 public static String generateRandomInvc(int length) {
		 	String DIGITS = "0123456789";
		    SecureRandom random = new SecureRandom();
		    
	        if (length <= 0) {
	            throw new IllegalArgumentException("Length must be greater than 0");
	        }

	        StringBuilder sb = new StringBuilder(length);
	        for (int i = 0; i < length; i++) {
	            sb.append(DIGITS.charAt(random.nextInt(DIGITS.length())));
	        }
	        return sb.toString();
	  }


}
