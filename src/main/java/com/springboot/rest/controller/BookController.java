package com.springboot.rest.controller;

import java.net.URI;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.springboot.rest.exception.ResourceNotFoundException;
import com.springboot.rest.model.Book;
import com.springboot.rest.repository.BookRepository;


@CrossOrigin(origins = "*")
@RestController
@RequestMapping(value= "/api")
public class BookController {
	/*private final BookService bookRepository;*/
	/*public BookController(BookService bookRepository) {
		this.bookservice = bookRepository;
	}*/
	
	
	
	@Autowired
	private BookRepository bookRepository ;
	
	
	//Get  Book Details By Id
	@GetMapping("/books/{id}")
	public ResponseEntity<Book> findBookById(@PathVariable Long id){
		Book book = bookRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Book not exist with id:" + id));
		return ResponseEntity.ok().body(book);	
	}
	
	
	//Get all Book
	@GetMapping("/books")
	public ResponseEntity<List<Book>> findAllBooks(){
		List<Book> books = bookRepository.findAll();
		return ResponseEntity.ok().body(books);
	}
	
	
	
	@PostMapping("/books")
	public ResponseEntity<Void> insertBook(@Validated @RequestBody Book book){
		book = bookRepository.save(book);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
				.path("/{id}").buildAndExpand(book.getId()).toUri();
		return ResponseEntity.created(uri).build();
	}
	
	
	// update Book
	@PutMapping("/books/{id}")
	public ResponseEntity<?> updateBook(@PathVariable Long id, @RequestBody Book updatebook){
		Book books = bookRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Book not exist with id:" + id));
		books.setTitle(updatebook.getTitle());
		books.setAuthor(updatebook.getAuthor());
		Book updatedbook = bookRepository.save(books);
		return ResponseEntity.ok(updatedbook);	
	}
	
	
	
	 // delete Book 
	@DeleteMapping("/books/{id}")
	public ResponseEntity<Map<String,Boolean>> deleteEmployee(@PathVariable Long id){
		Book book= bookRepository.findById(id)
				.orElseThrow(()->new ResourceNotFoundException("Book not exist with id:" + id));
		
		bookRepository.delete(book);
		Map<String,Boolean> response = new HashMap<>();
		response.put("Book with ID " + id + " was successfully deleted!", Boolean.TRUE);
		return ResponseEntity.ok(response);
	}
	
	
	
	
}
