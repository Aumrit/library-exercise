package com.ibm.library.endpoint;

import java.util.Collection;

import com.ibm.library.model.BookData;

public interface BookInventoryEndpoint {

	public Collection<BookData> getBooks();
	public BookData getBook(String isbn);
}
