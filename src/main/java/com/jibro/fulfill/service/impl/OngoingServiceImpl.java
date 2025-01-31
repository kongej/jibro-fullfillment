package com.jibro.fulfill.service.impl;

import java.security.SecureRandom;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.jibro.fulfill.dto.ongoing.OngoingListPageDto;
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
	public OngoingListPageDto ongoingList(String startDate, String endDate, String searchType, String searchId, Integer page) throws Exception {
		final Integer pageSize = 10;
		
		page -= 1;
		
		Pageable pageable = PageRequest.of(page, pageSize, Direction.DESC, "createdAt");
		Page<Ongoing> ongoingPage;
		
		if ((searchId == null || searchId.trim() == "") && ((startDate==null && endDate==null) || (startDate=="" && endDate==""))) {
			ongoingPage = this.ongoingRepository.findAll(pageable);
		}else if(searchId != null && (startDate==null && endDate==null || (startDate=="" && endDate==""))){
			if(searchType.equals("searchOngoingId")){
				ongoingPage = this.ongoingRepository.findByOngoingIdContains(searchId, pageable);
				
			}else if(searchType.equals("searchProductId")){
				ongoingPage = this.ongoingRepository.findByOrderProductProductIdContains(searchId, pageable);
				
			}else if(searchType.equals("searchOrderId")){
				ongoingPage = this.ongoingRepository.findByOrderOrderIdContains(searchId, pageable);
				
			}else {
				ongoingPage = this.ongoingRepository.findByInvcContainingOne(searchId, pageable);
			}
		}else {
			LocalDate localStart = LocalDate.parse(startDate);
			LocalDate localEnd = LocalDate.parse(endDate);
			LocalDateTime start = LocalDateTime.of(localStart, LocalTime.MIN);
			LocalDateTime end = LocalDateTime.of(localEnd, LocalTime.MAX);
		
			ongoingPage = this.ongoingRepository.findByCreatedAtBetween(start, end, pageable);
		}
		
		List<Ongoing> ongoings = ongoingPage.getContent();
		List<OngoingListResponseDto> response = ongoings.stream().map(ongoing-> new OngoingListResponseDto(ongoing)).collect(Collectors.toList());
		return new OngoingListPageDto(response, ongoingPage.isLast(), ongoingPage.getTotalPages());
	}

	@Override
	public Ongoing ongoingInsert(String orderId) throws Exception {
		Order order = orderRepository.getById(orderId);
		String deliverId = "DA003";
		Company deliver = companyRepository.getById(deliverId);
		
		int length = 6; 
        Integer invc = generateRandomInvc(length);
        System.out.println("Generated Invoice Number: " + invc);
		
		Ongoing ongoing = Ongoing.builder()
							.order(order)
							.delever(deliver)
							.invc(invc)
							.build();

		this.ongoingRepository.save(ongoing);
		return ongoing;
	}
	
	private Integer generateRandomInvc(int length) {
		 	String DIGITS = "0123456789";
		    SecureRandom random = new SecureRandom();
		    
	        if (length <= 0) {
	            throw new IllegalArgumentException("Length must be greater than 0");
	        }

	        StringBuilder sb = new StringBuilder(length);
	        for (int i = 0; i < length; i++) {
	            sb.append(DIGITS.charAt(random.nextInt(DIGITS.length())));
	        }
	        return Integer.parseInt("6" + sb.toString());
	  }


}
