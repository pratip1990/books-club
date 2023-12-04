package com.ph.bookms.controller.v1;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ph.bookms.service.BookService;
import com.ph.coredtos.dto.BookDto;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("")
@Tag(name = "Book Management - V1")
public class BookControllerV1 {
	private final BookService bookService;

    @Autowired
    public BookControllerV1(BookService bookService) {
        this.bookService = bookService;
    }

    @Operation(summary = "Get all books")
    @GetMapping
    public ResponseEntity<List<BookDto>> getAllBooks() {
        return bookService.getAllBooks();
    }

    @Operation(summary = "Get a book by ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Book found"),
            @ApiResponse(responseCode = "404", description = "Book not found")
    })
    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<BookDto> getBookById(@PathVariable Long id) {
        return bookService.getBookById(id);
    }

    @Operation(summary = "Create a new book")
    @PostMapping
    public ResponseEntity<BookDto> saveBook(@RequestBody BookDto book) {
		return bookService.saveBook(book);
    }

    @Operation(summary = "Delete a book by ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Book deleted"),
            @ApiResponse(responseCode = "404", description = "Book not found")
    })
    @DeleteMapping("/{id}")
    public void deleteBook(@PathVariable Long id) {
        bookService.deleteBook(id);
    }

}