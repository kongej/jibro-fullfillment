package com.jibro.fulfill.dto.sales;

import java.time.LocalDateTime;

public class SalesSummaryRequestDto {

	private LocalDateTime from;
	private LocalDateTime to;

	public LocalDateTime getFrom() {
		return from;
	}

	public void setFrom(String from) {
		if (from == "") {
			this.from = null;
		} else {
			this.from = LocalDateTime.parse(from + "T00:00:00");
			;
		}
	}

	public LocalDateTime getTo() {
		return to;
	}

	public void setTo(String to) {

		if (to == "") {
			this.to = null;
		} else {
			this.to = LocalDateTime.parse(to + "T00:00:00");
			;
		}
	}
}
