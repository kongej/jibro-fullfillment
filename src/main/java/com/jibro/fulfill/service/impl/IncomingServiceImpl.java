package com.jibro.fulfill.service.impl;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.jibro.fulfill.dto.incoming.IncomingInsertDto;
import com.jibro.fulfill.dto.incoming.IncomingListPageDto;
import com.jibro.fulfill.dto.incoming.IncomingListResponseDto;
import com.jibro.fulfill.entity.Incoming;
import com.jibro.fulfill.repository.IncomingRepository;
import com.jibro.fulfill.service.IncomingService;

@Service
public class IncomingServiceImpl implements IncomingService {
	IncomingRepository incomingRepository;
	
	public IncomingServiceImpl(IncomingRepository incomingRepository) {
		this.incomingRepository = incomingRepository;
	}
	
	@Override
	public IncomingListPageDto incomingList(String startDate, String endDate, String searchType, String searchId, Integer page) throws Exception {
		final Integer pageSize = 10;
		
		page -= 1;
		
		Pageable pageable = PageRequest.of(page, pageSize, Direction.DESC, "createdAt");
		Page<Incoming> incomingPage;
		
		if ((searchId == null || searchId.trim() == "") && ((startDate==null && endDate==null) || (startDate=="" && endDate==""))) {
			incomingPage = this.incomingRepository.findAll(pageable);
		}else if(searchId != null && (startDate==null && endDate==null || (startDate=="" && endDate==""))){
			if(searchType.equals("searchIncomingId")){
				incomingPage = this.incomingRepository.findByIncomingIdContains(searchId, pageable);
				
			}else {
				incomingPage = this.incomingRepository.findByProductProductIdContains(searchId, pageable);
			}
		}else {
			LocalDate localStart = LocalDate.parse(startDate);
			LocalDate localEnd = LocalDate.parse(endDate);
			LocalDateTime start = LocalDateTime.of(localStart, LocalTime.MIN);
			LocalDateTime end = LocalDateTime.of(localEnd, LocalTime.MAX);
		
			incomingPage = this.incomingRepository.findByCreatedAtBetween(start, end, pageable);
		}
		
		
		List<Incoming> incomings = incomingPage.getContent();
		List<IncomingListResponseDto> response = incomings.stream().map(incoming-> new IncomingListResponseDto(incoming)).collect(Collectors.toList());
		return new IncomingListPageDto(response, incomingPage.isLast(), incomingPage.getTotalPages());
	}

	@Override
	public Incoming incomingInsert(IncomingInsertDto incomingInsertDto) throws Exception {
		Incoming incoming = Incoming.builder()
							.incomingCount(incomingInsertDto.getIncomingCount())
							.total(incomingInsertDto.getTotal())
							.product(incomingInsertDto.getProduct())
							.build();

		return this.incomingRepository.save(incoming);
	}

}
