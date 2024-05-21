package com.jibro.fulfill.service;

import java.util.List;

import com.jibro.fulfill.dto.ongoing.OngoingListResponseDto;

public interface OngoingService {
	List<OngoingListResponseDto> ongoingList() throws Exception;
}
