package com.llamazon.service.service;

import com.llamazon.service.model.LlamazonBook;

public interface BookService {
	
	public String generateBookKey(int bookId, String userCode, String bookTypeFormat);
	
	public boolean deleteBookKey(int bookId, String userCode, String bookTypeFormat);
	
	public boolean validateBookKey(int bookId, String userCode, String bookKey, String bookTypeFormat);
	
	public LlamazonBook getBook(int bookId);
}
