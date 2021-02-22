package com.ibm.library.service;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
//import org.mockito.stubbing.OngoingStubbing;

import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Collection;

import static org.mockito.Mockito.verify;

import com.ibm.library.endpoint.BookInventoryEndpoint;
import com.ibm.library.model.BookData;

import junit.framework.Assert;

/**
 * @author AumritSarangi
 *
 */
class LibraryServiceImplTest {

    @Mock 
    private BookInventoryEndpoint bookInventoryEndpoint;

    @InjectMocks  
    private LibraryServiceImpl libraryService;
    
    @BeforeEach
    public void init() {
       MockitoAnnotations.initMocks(this);
    }
    
    /**
     * 
     */
    @DisplayName("Test libraryservice getBook with valid isbn")
	@Test
	void testGetBookExistingIsbn() {
		
    	// Set the parameter values and mock the methods for this test case
    	String isbn = "1234";
    	BookData bookDataMocked = new BookData("FICTION", isbn, "SomeBook", "SomeAuthor");
    	when(bookInventoryEndpoint.getBook(isbn)).thenReturn(bookDataMocked);
    	
    	// Call the method being tested and save the response
    	BookData bookData = libraryService.getBook(isbn);
    	
    	// Check the results of the method being tested
    	assertNotNull(bookData, "bookData should not be null");
    	assertEquals(bookData, bookDataMocked, "bookData should be the same as: " + bookDataMocked);
    	
		// Verify that the mocked methods were called
    	verify(bookInventoryEndpoint).getBook(isbn);
	}
    
    @DisplayName("Test libraryservice getBook with invalid isbn")
	@Test
	void testGetBookNonExistingIsbn() {
		
    	// Set the parameter values and mock the methods for this test case
    	String isbn = "1234";
    	BookData bookDataMocked = new BookData("FICTION", isbn, "SomeBook", "SomeAuthor");
    	when(bookInventoryEndpoint.getBook(isbn)).thenReturn(null);
    	
    	// Call the method being tested and save the response
    	BookData bookData = libraryService.getBook(isbn);
    	
    	// Check the results of the method being tested
    	assertNull( bookData,"bookData should  be null");
    	//assertEquals(bookData, bookDataMocked, "bookData should be the same as: " + bookDataMocked);
    	
		// Verify that the mocked methods were called
    	verify(bookInventoryEndpoint).getBook(isbn);
	}
    @DisplayName("Test libraryservice getBooks with data")
  	@Test
  	void testGetBooksWithData() {
  		
      	// Set the parameter values and mock the methods for this test case
      	String isbn = "1234";
      	String isbn1="234";
      	BookData bookDataMocked = new BookData("FICTION", isbn, "SomeBook", "SomeAuthor");
      	BookData bookDataMocked1 =new BookData("FICTION", isbn1, "SomeBook", "SomeAuthor");
      	Collection<BookData> Books= new ArrayList<>();
      	Books.add(bookDataMocked1);
      	Books.add(bookDataMocked);
      	
      	when( bookInventoryEndpoint.getBooks()).thenReturn(Books);
      	
      	// Call the method being tested and save the response
       Collection<BookData> bookData = libraryService.getBooks();
      	
      	// Check the results of the method being tested
      	assertNotNull( bookData,"bookData should not be null");
      	assertEquals(bookData, Books, "bookData should be the same as: " + Books);
      	
  		// Verify that the mocked methods were called
      	verify(bookInventoryEndpoint).getBooks();
  	}
    @DisplayName("Test libraryservice getBooks without data")
  	@Test
  	void testGetBooksWithoutData() {
  		
      	// Set the parameter values and mock the methods for this test case
      	String isbn = "1234";
      	String isbn1="234";
      	BookData bookDataMocked = new BookData("FICTION", isbn, "SomeBook", "SomeAuthor");
      	BookData bookDataMocked1 =new BookData("FICTION", isbn1, "SomeBook", "SomeAuthor");
      	Collection<BookData> Books= new ArrayList<>();
      	Books.add(bookDataMocked1);
      	Books.add(bookDataMocked);
      	
      	when( bookInventoryEndpoint.getBooks()).thenReturn(null);
      	
      	// Call the method being tested and save the response
       Collection<BookData> bookData = libraryService.getBooks();
      	
      	// Check the results of the method being tested
      	assertNull( bookData,"bookData should not be null");
      	//assertEquals(bookData, Books, "bookData should be the same as: " + Books);
      	
  		// Verify that the mocked methods were called
      	verify(bookInventoryEndpoint).getBooks();
  	}
}
