package com.ibm.library.service;

import java.util.Collection;
import com.ibm.library.model.BookData;

public interface LibraryService {
	
	public Collection<BookData> getBooks();
	public BookData getBook(String isbn);
}
