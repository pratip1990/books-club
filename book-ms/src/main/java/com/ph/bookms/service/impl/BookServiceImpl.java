package com.ph.bookms.service.impl;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.ph.bookms.entity.Book;
import com.ph.bookms.exception.NotFoundException;
import com.ph.bookms.repository.BookRepository;
import com.ph.bookms.service.BookService;
import com.ph.bookms.service.BookStockService;
import com.ph.coredtos.dto.BookDto;

@Service
public class BookServiceImpl implements BookService {

	private final BookRepository bookRepository;
	private final ModelMapper modelMapper;

	@Autowired
	public BookServiceImpl(BookRepository bookRepository, BookStockService bookStockService, ModelMapper modelMapper) {
		this.bookRepository = bookRepository;
		this.modelMapper = modelMapper;
	}

	@Override
	public ResponseEntity<List<BookDto>> getAllBooks() {
		List<Book> book = bookRepository.findAll();
		List<BookDto> bookDto = book.stream().map(a -> modelMapper.map(a, BookDto.class)).toList();
		return ResponseEntity.ok(bookDto);
	}

	@Override
	public ResponseEntity<BookDto> getBookById(Long id) {
		Book book = bookRepository.findById(id)
				.orElseThrow(() -> new NotFoundException("BookStock not found with id: " + id));

		BookDto bookDto = modelMapper.map(book, BookDto.class);
		return ResponseEntity.ok(bookDto);
	}

	@Override
	public ResponseEntity<BookDto> saveBook(BookDto bookDto) {
		Book book = modelMapper.map(bookDto, Book.class);
		bookRepository.save(book);
		return ResponseEntity.ok(bookDto);
	}

	@Override
	public void deleteBook(Long id) {
		if (!bookRepository.existsById(id)) {
			throw new NotFoundException("Book not found with id: " + id);
		}
		bookRepository.deleteById(id);

	}

}
