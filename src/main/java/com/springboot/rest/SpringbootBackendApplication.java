package com.springboot.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.springboot.rest.model.Book;
import com.springboot.rest.repository.BookRepository;

@SpringBootApplication
public class SpringbootBackendApplication implements CommandLineRunner{
     
	
	@Autowired
	private BookRepository bookRepository ;
	
	public static void main(String[] args) {
		SpringApplication.run(SpringbootBackendApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		bookRepository.save(new Book( "spring boot", "Rojer penrose"));
		bookRepository.save(new Book( "React ui", "Rojer penrose"));
		bookRepository.save(new Book( "Angular material designe", "Rojer penrose"));
	}

}
