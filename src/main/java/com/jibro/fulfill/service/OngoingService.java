package com.jibro.fulfill.service;

import com.jibro.fulfill.dto.ongoing.OngoingListPageDto;
import com.jibro.fulfill.entity.Ongoing;

public interface OngoingService {
	OngoingListPageDto ongoingList(String searchId, Integer page) throws Exception;
	Ongoing ongoingInsert(String orderId) throws Exception;
}
