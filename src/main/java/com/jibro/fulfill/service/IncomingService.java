package com.jibro.fulfill.service;

import com.jibro.fulfill.dto.incoming.IncomingInsertDto;
import com.jibro.fulfill.dto.incoming.IncomingListPageDto;
import com.jibro.fulfill.entity.Incoming;

public interface IncomingService {
	IncomingListPageDto incomingList(String startDate, String endDate, String searchType, String searchId, Integer page) throws Exception;
	Incoming incomingInsert(IncomingInsertDto incomingInsertDto) throws Exception;
}
