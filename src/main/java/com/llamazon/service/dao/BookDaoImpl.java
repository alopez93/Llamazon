package com.llamazon.service.dao;

import java.util.UUID;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.llamazon.service.model.LlamazonBook;
import com.llamazon.service.model.LlamazonBookUser;

@Repository("bookDao")
public class BookDaoImpl extends AbstractDao implements BookDao{

	@Override
	public LlamazonBook getBook(int bookId) {
		LlamazonBook book = (LlamazonBook) getSession().get(LlamazonBook.class, bookId);
		return book;
	}

	@Override
	public String generateBookKeyForUser(int bookId, String userCode, String bookTypeFormat) {
		LlamazonBookUser lbu = new LlamazonBookUser(bookId, userCode, bookTypeFormat);
		//Validate book exists...
		if(!verifyBookExists(bookId)){
			return "NoBookFound";
		}
		
		//Verify user doesn't already have this book...
		LlamazonBookUser alreadyCreatedLbu = getLlamazonBookUser(bookId,userCode,bookTypeFormat);
		if(alreadyCreatedLbu != null){
			return "UserAlreadyHasBook";
		}
		
		//Generate Key For User...
		String bookKey = getUniqueKey();
		lbu.setBookKey(bookKey);
		
		//Add it to the database...
		persistBookKeyWithUser(lbu);
		
		return bookKey;
	}

	@Override
	public boolean deleteBookKeyForUser(int bookId, String userCode, String bookTypeFormat) {
		LlamazonBookUser lbu = getLlamazonBookUser(bookId, userCode, bookTypeFormat);
		try{
			delete(lbu);
		}catch(Exception e){
			System.out.println("Could not be deleted");
			return false;
		}
		return true;
	}

	@Override
	public boolean validateBookKeyForUser(int bookId, String userCode, String bookKey, String bookTypeFormat) {
		Criteria crit = getSession().createCriteria(LlamazonBookUser.class);
		crit.add(Restrictions.and(Restrictions.eq("llamazonBook.llamazonBookId", bookId), Restrictions.eq("userCode", userCode), Restrictions.eq("bookKey", bookKey), Restrictions.eq("bookFormat", bookTypeFormat)));
		LlamazonBookUser lbu = (LlamazonBookUser) crit.uniqueResult();
		return (lbu != null);
	}
	
	@Override
	public void persistBookKeyWithUser(LlamazonBookUser lbu) {
		persist(lbu);
	}
	
	public boolean verifyBookExists(int bookId){
		Criteria criteria = getSession().createCriteria(LlamazonBook.class);
		criteria.add(Restrictions.eq("llamazonBookId", bookId));
		return (((LlamazonBook) criteria.uniqueResult()) != null);
	}
	

	public String getUniqueKey() 
	{
	   return UUID.randomUUID().toString();
	}
	
	public LlamazonBookUser getLlamazonBookUser(int bookId, String userCode, String bookTypeFormat){
		Criteria crit = getSession().createCriteria(LlamazonBookUser.class);
		crit.add(Restrictions.and(Restrictions.eq("llamazonBook.llamazonBookId", bookId), Restrictions.eq("userCode", userCode), Restrictions.eq("bookFormat", bookTypeFormat)));
		return (LlamazonBookUser) crit.uniqueResult();
	}

}
