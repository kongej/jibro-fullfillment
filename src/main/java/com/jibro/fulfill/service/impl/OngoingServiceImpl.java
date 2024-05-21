package com.jibro.fulfill.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.jibro.fulfill.dto.ongoing.OngoingListResponseDto;
import com.jibro.fulfill.entity.Ongoing;
import com.jibro.fulfill.repository.OngoingRepository;
import com.jibro.fulfill.service.OngoingService;

@Service
public class OngoingServiceImpl implements OngoingService {
	
	OngoingRepository ongoingRepository;
	
	public OngoingServiceImpl(OngoingRepository ongoingRepository) {
		this.ongoingRepository = ongoingRepository;
	}

	@Override
	public List<OngoingListResponseDto> ongoingList() throws Exception {
		List<Ongoing> ongoings;
		ongoings = this.ongoingRepository.findAll();
		return ongoings.stream().map(ongoing-> new OngoingListResponseDto(ongoing)).collect(Collectors.toList());
	}
	
	


}
