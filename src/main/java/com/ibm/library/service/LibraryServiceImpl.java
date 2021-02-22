package com.ibm.library.service;

import java.util.Collection;

import com.ibm.library.model.BookData;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ibm.library.endpoint.BookInventoryEndpoint;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Service
public class LibraryServiceImpl implements LibraryService {
	
	private final Logger logger = LoggerFactory.getLogger(LibraryServiceImpl.class);
	
	@Autowired
	private BookInventoryEndpoint bookInventoryEndpoint;

	public LibraryServiceImpl() {
	}
	
	@Override
	public BookData getBook(String isbn) {
		
		logger.info("Entered LibraryServiceImpl.getBook().  isbn=" + isbn);

		BookData book = this.bookInventoryEndpoint.getBook(isbn);
		
		
		logger.info("Leaving LibraryServiceImpl.getBook().  isbn=" + isbn);
		return book;
	}

	@Override
	public Collection<BookData> getBooks() {
		
		logger.info("Entered LibraryServiceImpl.getBooks()");

		Collection<BookData> books = this.bookInventoryEndpoint.getBooks();
		
		logger.info("Leaving LibraryServiceImpl.getBooks()");
		return books;
	}
}
