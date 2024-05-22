package com.jibro.fulfill.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.jibro.fulfill.dto.incoming.IncomingInsertDto;
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
		System.out.println(incomings.toString());
		return incomings.stream().map(incoming-> new IncomingListResponseDto(incoming)).collect(Collectors.toList());
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
