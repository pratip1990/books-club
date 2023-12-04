package com.ph.bookms.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ph.bookms.entity.BookStock;

public interface BookStockRepository extends JpaRepository<BookStock, Long>{

	void deleteBySkuId(String skuId);

	Optional<BookStock> findBySkuId(String skuId);

	void deleteByBookId(Long bookId);

}
