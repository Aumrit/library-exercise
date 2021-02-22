package com.ibm.library.rest;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.Collection;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ibm.library.model.BookData;
import com.ibm.library.service.LibraryService;

@WebMvcTest(LibraryController.class)
class LibraryControllerTest {
	@Autowired
	private MockMvc mockMvc;
	@Autowired
	private ObjectMapper objectMapper;

	@MockBean
	private LibraryService libraryService;

	@Test
	public void getBookWithValidIsbn() throws Exception {
		String isbn = "1234";
    	BookData bookDataMocked = new BookData("FICTION", isbn, "SomeBook", "SomeAuthor");
		when(libraryService.getBook(isbn)).thenReturn(bookDataMocked);
		
		MvcResult result=this.mockMvc.perform(get("/library/book/{isbn}",isbn)).andDo(print()).andExpect(status().isOk()).andReturn();
		/* .andExpect(content().string(containsString("Hello, Mock"))) */;
		String body= result.getResponse().getContentAsString();
		BookData book = objectMapper.readValue(body,BookData.class);
		assertNotNull(book);
		assertEquals(bookDataMocked,book);
		
	}
	@Test
	public void getBooksWithData() throws Exception {
		String isbn = "1234";
		String isbn1="234";
      	BookData bookDataMocked = new BookData("FICTION", isbn, "SomeBook", "SomeAuthor");
      	BookData bookDataMocked1 =new BookData("FICTION", isbn1, "SomeBook", "SomeAuthor");
      	Collection<BookData> Books= new ArrayList<>();
      	Books.add(bookDataMocked1);
      	Books.add(bookDataMocked);
      	
      	when( libraryService.getBooks()).thenReturn(Books);
    	
		
		MvcResult result=this.mockMvc.perform(get("/library/books")).andDo(print()).andExpect(status().isOk()).andReturn();
		/* .andExpect(content().string(containsString("Hello, Mock"))) */;
		String body= result.getResponse().getContentAsString();
		Collection<BookData> booksReturned = objectMapper.readValue(body,ArrayList.class);
		 //booksReturned.removeAll(Books).size();
		assertNotNull(booksReturned);
		assertEquals(Books.size(),booksReturned.size());
		
	}

}
