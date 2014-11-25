package com.llamazon.service.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.llamazon.service.dao.BookDao;
import com.llamazon.service.model.LlamazonBook;

@Service("bookService")
@Transactional
public class BookServiceImpl implements BookService{

	@Autowired
	BookDao dao;
	
	@Override
	public String generateBookKey(int bookId, String userCode, String bookTypeFormat) {
		return dao.generateBookKeyForUser(bookId, userCode, bookTypeFormat);
	}

	@Override
	public boolean deleteBookKey(int bookId, String userCode, String bookTypeFormat) {
		return dao.deleteBookKeyForUser(bookId, userCode, bookTypeFormat);
	}

	@Override
	public boolean validateBookKey(int bookId, String userCode, String bookKey, String bookTypeFormat) {
		return dao.validateBookKeyForUser(bookId, userCode, bookKey, bookTypeFormat);
	}
	
	public LlamazonBook getBook(int bookId){
		return dao.getBook(bookId);
	}

}
