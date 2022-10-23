package com.corsojava.library.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.corsojava.library.exceptions.NotEnoughCopiesException;
import com.corsojava.library.model.Book;
import com.corsojava.library.model.Borrowing;
import com.corsojava.library.repository.BookRepository;
import com.corsojava.library.repository.BorrowingRepository;

@Service
public class BorrowingService {
	@Autowired
	private BorrowingRepository borrowingRepository;
	
	@Autowired
	private BookRepository bookRepository;


	public Borrowing create(Borrowing borrowing) 
			throws NotEnoughCopiesException {
		Book bookToBorrow=borrowing.getBook();
		if (bookToBorrow.getAvailableCopies()>0)
		{
			bookToBorrow.setAvailableCopies(
					bookToBorrow.getAvailableCopies()-1);
			borrowing.setStartDate(LocalDate.now());
			bookRepository.save(bookToBorrow);
			return borrowingRepository.save(borrowing);
		} else
			throw new NotEnoughCopiesException(bookToBorrow);
		
	}
	
	
	
	public List<Borrowing> list() {
		return borrowingRepository.findAll();
	}
	
}
