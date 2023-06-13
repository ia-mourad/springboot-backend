package com.springboot.rest.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.springboot.rest.model.Book;

public interface BookRepository extends JpaRepository<Book,Long> {

}
