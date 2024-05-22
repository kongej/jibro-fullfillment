package com.jibro.fulfill.service;

import java.util.List;

import com.jibro.fulfill.dto.ongoing.OngoingListResponseDto;
import com.jibro.fulfill.entity.Ongoing;

public interface OngoingService {
	List<OngoingListResponseDto> ongoingList() throws Exception;
	public Ongoing ongoingInsert(String orderId) throws Exception;
}
