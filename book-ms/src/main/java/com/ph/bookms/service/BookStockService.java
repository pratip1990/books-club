package com.ph.bookms.service;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.ph.coredtos.dto.BookStockDto;

public interface BookStockService {

	ResponseEntity<List<BookStockDto>> getAllBookStocks();

	ResponseEntity<BookStockDto> getBookStockById(Long id);

	ResponseEntity<BookStockDto> saveBookStock(BookStockDto bookStockDto);

	void deleteBookStock(Long id);

	ResponseEntity<BookStockDto> getBookStockBySkuId(String skuId);

	void deleteBookStock(String skuId);

	void deleteBookAllStocks(Long bookId);

}
