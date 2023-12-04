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

import com.ph.bookms.service.BookStockService;
import com.ph.coredtos.dto.BookStockDto;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

@RestController
@RequestMapping("book-stocks/api/v1")
public class BookStockControllerV1 {
	private final BookStockService bookStockService;

    @Autowired
    public BookStockControllerV1(BookStockService bookStockService) {
        this.bookStockService = bookStockService;
    }

    @Operation(summary = "Get all book stocks")
    @GetMapping(value = {"","/"}, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<BookStockDto>> getAllBookStocks() {
        return bookStockService.getAllBookStocks();
    }

    @Operation(summary = "Get a book stock by ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Book stock found"),
            @ApiResponse(responseCode = "404", description = "Book stock not found")
    })
    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<BookStockDto> getBookStockById(@PathVariable Long id) {
        return bookStockService.getBookStockById(id);
    }
    
    @GetMapping(value = "/sku/{sku-id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<BookStockDto> getBookStockById(@PathVariable(name = "sku-id") String skuId) {
        return bookStockService.getBookStockBySkuId(skuId);
    }

    @Operation(summary = "Create a new book stock")
    @PostMapping(value = {"","/"}, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<BookStockDto> saveBookStock(@RequestBody BookStockDto bookStockDto) {
        return bookStockService.saveBookStock(bookStockDto);
    }

    @Operation(summary = "Delete a book stock by ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Book stock deleted"),
            @ApiResponse(responseCode = "404", description = "Book stock not found")
    })
    
    @DeleteMapping(value = {"/{id}"},produces = MediaType.APPLICATION_JSON_VALUE)
    public void deleteBookStock(@PathVariable Long id) {
        bookStockService.deleteBookStock(id);
    }

    @DeleteMapping(value = {"/sku/{sku-id}"},produces = MediaType.APPLICATION_JSON_VALUE)
    public void deleteBookStockBySkuId(@PathVariable String skuId) {
        bookStockService.deleteBookStock(skuId);
    }
}
