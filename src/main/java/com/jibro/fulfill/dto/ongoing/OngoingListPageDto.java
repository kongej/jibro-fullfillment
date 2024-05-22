package com.jibro.fulfill.dto.ongoing;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class OngoingListPageDto {
	private List<OngoingListResponseDto> Ongoings;
	private boolean lastPage;
	private int totalPage;
	
	public OngoingListPageDto(List<OngoingListResponseDto> Ongoings,
								boolean lastPage,
								int totalPage) {
		this.Ongoings = Ongoings;
		this.lastPage = lastPage;
		this.totalPage = totalPage;
	}
}
