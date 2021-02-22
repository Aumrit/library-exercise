package com.ibm.library.rest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PathVariable;

import com.ibm.library.service.LibraryService;

import java.util.Collection;

import com.ibm.library.model.BookData;

import org.springframework.beans.factory.annotation.Autowired;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@RestController
@RequestMapping(value = "/library")
public class LibraryController
{
	private final Logger logger = LoggerFactory.getLogger(LibraryController.class);
	
	@Autowired
	private LibraryService libraryService;
	
	public LibraryController() {
	}
	
    @RequestMapping(value = "/book/{isbn}")
	public ResponseEntity<?> getBook(@PathVariable(value="isbn") String isbn) {
    	
    	logger.info("Entered LibraryController.getBook().  isbn=" + isbn);

    	BookData book = this.libraryService.getBook(isbn);
    	ResponseEntity<BookData> responseEntity = new ResponseEntity<BookData>(book, HttpStatus.OK);
    	
    	logger.info("Leaving LibraryController.getBook().  isbn=" + isbn);
    	
		return responseEntity;
	}

    @RequestMapping(value = "/books")
	public ResponseEntity<?> getBooks() {
    	
    	logger.info("Entered LibraryController.getBooks()");

    	Collection<BookData> books = this.libraryService.getBooks();
    	ResponseEntity<Collection<BookData>> responseEntity = new ResponseEntity<Collection<BookData>>(books, HttpStatus.OK);
    	
    	logger.info("Leaving LibraryController.getBooks()");
    	
		return responseEntity;
	}
    @RequestMapping(value = "/hello")
    public ResponseEntity<?> hello() {
    	ResponseEntity<String> responseEntity= new ResponseEntity<String>("Hello", HttpStatus.OK);
    	return responseEntity;
    	}
}
