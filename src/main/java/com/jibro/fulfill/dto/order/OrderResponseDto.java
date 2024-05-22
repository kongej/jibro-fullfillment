package com.jibro.fulfill.dto.order;

import java.util.List;

import org.springframework.data.domain.Page;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
public class OrderResponseDto {
    private int page;
    private int size;
    private int start;
    private int end;
    private boolean prev;
    private boolean next;
    private int pageBlock = 5;
    private List<OrderListResponseDto> content;

    public OrderResponseDto() {}

    public OrderResponseDto(Page<OrderListResponseDto> orderPage, int pageBlock) {
        this.page = orderPage.getNumber() + 1;
        this.size = orderPage.getSize();
        this.content = orderPage.getContent();
        this.pageBlock = pageBlock;

        int totalPages = orderPage.getTotalPages();

        this.start = ((int) Math.ceil((double) this.page / this.pageBlock) - 1) * this.pageBlock + 1;
        this.end = Math.min(start + pageBlock - 1, totalPages);

        this.prev = this.start > 1;
        this.next = this.end < totalPages;
    }

	@Override
	public String toString() {
		return "OrderResponseDto [page=" + page + ", size=" + size + ", start=" + start + ", end=" + end + ", prev="
				+ prev + ", next=" + next + ", pageBlock=" + pageBlock + ", content=" + content + "]";
	}

}