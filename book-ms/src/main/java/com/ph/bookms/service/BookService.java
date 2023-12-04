/**
 * 
 */
package com.ph.bookms.service;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.ph.coredtos.dto.BookDto;

/**
 * 
 */
public interface BookService {

	ResponseEntity<List<BookDto>> getAllBooks();

	ResponseEntity<BookDto> getBookById(Long id);

	ResponseEntity<BookDto> saveBook(BookDto book);

	void deleteBook(Long id);

}
