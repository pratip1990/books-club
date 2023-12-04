package com.ph.bookms.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ph.bookms.entity.Book;

public interface BookRepository extends JpaRepository<Book, Long>{

}
