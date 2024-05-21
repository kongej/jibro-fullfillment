package com.jibro.fulfill.service;

import java.util.List;
import java.util.NoSuchElementException;

import com.jibro.fulfill.dto.seller.SellerInsertDto;
import com.jibro.fulfill.dto.seller.SellerListResponseDto;
import com.jibro.fulfill.dto.seller.SellerModDto;
import com.jibro.fulfill.dto.seller.SellerModResponseDto;

public interface SellerService {
	List<SellerListResponseDto> sellerList();
	String insert(SellerInsertDto sellerInsertDto);
	SellerModResponseDto edit(String companyId) throws NoSuchElementException;
	void update(SellerModDto sellerModDto) throws NoSuchElementException;
	void delete(String companyId) throws NoSuchElementException;
}
