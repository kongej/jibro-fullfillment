package com.jibro.fulfill.dto.incoming;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class IncomingListPageDto {
	private List<IncomingListResponseDto> Incomings;
	private boolean lastPage;
	private int totalPage;
	
	public IncomingListPageDto(List<IncomingListResponseDto> Incomings,
								boolean lastPage,
								int totalPage) {
		this.Incomings =Incomings;
		this.lastPage = lastPage;
		this.totalPage = totalPage;
	}
}
