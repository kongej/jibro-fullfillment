package com.jibro.fulfill.service;

import java.util.List;

import com.jibro.fulfill.dto.incoming.IncomingListResponseDto;

public interface IncomingService {
	List<IncomingListResponseDto> incomingList() throws Exception;
}
