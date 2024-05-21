package com.jibro.fulfill.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

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
	public List<IncomingListResponseDto> incomingList() throws Exception {
		List<Incoming> incomings;
		incomings = this.incomingRepository.findAll();
		return incomings.stream().map(incoming-> new IncomingListResponseDto(incoming)).collect(Collectors.toList());
	}

}
