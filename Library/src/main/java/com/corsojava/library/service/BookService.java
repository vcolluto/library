package com.corsojava.library.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.corsojava.library.model.Book;
import com.corsojava.library.repository.BookRepository;

@Service
public class BookService {
	
	@Autowired
	private BookRepository bookRepository;
	
	public List<Book> findAllBooks() {
		return bookRepository.findAll();
	}
	
	public List<Book> findAllBooksByWord(String word) {
		return bookRepository.findByTitleContainingIgnoreCase(word);
	}
	
	public Book create(Book book) {
		return bookRepository.save(book);
	}
	
	public Optional<Book> findById(int bookId) {
		return bookRepository.findById(bookId);
	}

}
