package com.ph.bookms.service.impl;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;

import com.ph.bookms.entity.BookStock;
import com.ph.bookms.exception.NotFoundException;
import com.ph.bookms.repository.BookStockRepository;
import com.ph.bookms.service.BookService;
import com.ph.bookms.service.BookStockService;
import com.ph.coredtos.dto.BookStockDto;

public class BookStockServiceImpl implements BookStockService {

	private final BookStockRepository bookStockRepository;
	private final ModelMapper modelMapper;

	@Autowired
	public BookStockServiceImpl(BookStockRepository bookStockRepository, BookService bookService,
			ModelMapper modelMapper) {
		this.bookStockRepository = bookStockRepository;
		this.modelMapper = modelMapper;
	}

	@Override
	public ResponseEntity<List<BookStockDto>> getAllBookStocks() {
		List<BookStock> bookStocks = bookStockRepository.findAll();
		List<BookStockDto> stockDtos = bookStocks.stream().map(a -> modelMapper.map(a, BookStockDto.class)).toList();
		return ResponseEntity.ok(stockDtos);
	}

	@Override
	public ResponseEntity<BookStockDto> getBookStockById(Long id) {
		BookStock bookStock = bookStockRepository.findById(id)
				.orElseThrow(() -> new NotFoundException("BookStock not found with id: " + id));
		
		BookStockDto stockDto= modelMapper.map(bookStock, BookStockDto.class);
		return ResponseEntity.ok(stockDto);
	}

	@Override
	public ResponseEntity<BookStockDto> saveBookStock(BookStockDto bookStockDto) {
		BookStock bookStock =  modelMapper.map(bookStockDto, BookStock.class);
		bookStockRepository.save(bookStock);
		return ResponseEntity.ok(bookStockDto);
	}

	@Override
	public void deleteBookStock(Long id) {
		if (!bookStockRepository.existsById(id)) {
			throw new NotFoundException("BookStock not found with id: " + id);
		}
		bookStockRepository.deleteById(id);
	}

	@Override
	public ResponseEntity<BookStockDto> getBookStockBySkuId(String skuId) {
		BookStock bookStock = bookStockRepository.findBySkuId(skuId)
				.orElseThrow(() -> new NotFoundException("BookStock not found with id: " + skuId));
		
		BookStockDto stockDto= modelMapper.map(bookStock, BookStockDto.class);
		return ResponseEntity.ok(stockDto);
	}

	@Override
	public void deleteBookStock(String skuId) {
		bookStockRepository.deleteBySkuId(skuId);
	}
	
	@Override
	public void deleteBookAllStocks(Long bookId) {
		bookStockRepository.deleteByBookId(bookId);
	}

}
