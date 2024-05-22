package com.jibro.fulfill.service;

import com.jibro.fulfill.dto.incoming.IncomingInsertDto;
import com.jibro.fulfill.dto.incoming.IncomingListPageDto;

public interface IncomingService {
	IncomingListPageDto incomingList(String searchId, Integer page) throws Exception;
	void incomingInsert(IncomingInsertDto incomingInsertDto) throws Exception;
}
