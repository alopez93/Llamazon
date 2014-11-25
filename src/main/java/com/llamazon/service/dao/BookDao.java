package com.llamazon.service.dao;

import com.llamazon.service.model.LlamazonBook;
import com.llamazon.service.model.LlamazonBookUser;

public interface BookDao {

	public LlamazonBook getBook(int bookId);
	
	public String generateBookKeyForUser(int bookId, String userCode, String bookTypeFormat);
	
	public boolean deleteBookKeyForUser(int bookId, String userCode, String bookTypeFormat);
	
	public boolean validateBookKeyForUser(int bookId, String userCode, String bookKey, String bookTypeFormat);
	
	public void persistBookKeyWithUser(LlamazonBookUser lbu);
	
	public boolean verifyBookExists(int bookId);
	
}
