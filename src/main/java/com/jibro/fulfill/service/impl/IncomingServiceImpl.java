package com.jibro.fulfill.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.domain.Sort.Order;
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
	public IncomingListPageDto incomingList(String searchType, String searchId, Integer page) throws Exception {
		final Integer pageSize = 10;
		
		page -= 1;
		
		Pageable pageable = PageRequest.of(page, pageSize, Direction.DESC, "createdAt");
		Page<Incoming> incomingPage;
		
		if (searchId == null || searchId.trim() == "") {
			incomingPage = this.incomingRepository.findAll(pageable);
		}else {
			if(searchType.equals("searchIncomingId")){
				incomingPage = this.incomingRepository.findByIncomingIdContains(searchId, pageable);
				
			}else {
				incomingPage = this.incomingRepository.findByProductProductIdContains(searchId, pageable);
			}
		}
		
		
		List<Incoming> incomings = incomingPage.getContent();
		List<IncomingListResponseDto> response = incomings.stream().map(incoming-> new IncomingListResponseDto(incoming)).collect(Collectors.toList());
		return new IncomingListPageDto(response, incomingPage.isLast(), incomingPage.getTotalPages());
	}

	@Override
	public void incomingInsert(IncomingInsertDto incomingInsertDto) throws Exception {
		Incoming incoming = Incoming.builder()
							.incomingCount(incomingInsertDto.getIncomingCount())
							.total(incomingInsertDto.getTotal())
							.product(incomingInsertDto.getProduct())
							.build();

		this.incomingRepository.save(incoming);
	}

}
