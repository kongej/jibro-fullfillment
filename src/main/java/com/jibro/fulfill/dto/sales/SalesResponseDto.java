package com.jibro.fulfill.dto.sales;

import java.util.List;

import org.springframework.data.domain.Page;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
public class SalesResponseDto {

	 private int page;
	    private int size;
	    private int start;
	    private int end;
	    private boolean prev;
	    private boolean next;
	    private int pageBlock = 5;
	    private List<SalesSummaryResponseDto> content;

	    public SalesResponseDto() {}

	    public SalesResponseDto(Page<SalesSummaryResponseDto> salesPage, int pageBlock) {
	        this.page = salesPage.getNumber() + 1;
	        this.size = salesPage.getSize();
	        this.content = salesPage.getContent();
	        this.pageBlock = pageBlock;

	        int totalPages = salesPage.getTotalPages();

	        this.start = ((int) Math.ceil((double) this.page / this.pageBlock) - 1) * this.pageBlock + 1;
	        this.end = Math.min(start + pageBlock - 1, totalPages);

	        this.prev = this.start > 1;
	        this.next = this.end < totalPages;
	    }

		@Override
		public String toString() {
			return "SalesResponseDto [page=" + page + ", size=" + size + ", start=" + start + ", end=" + end + ", prev="
					+ prev + ", next=" + next + ", pageBlock=" + pageBlock + ", content=" + content + "]";
		}
	    
	    
}
