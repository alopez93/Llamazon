package com.llamazon.service.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "LlamazonBooksToUsers")
public class LlamazonBookUser {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@OneToOne
	@JoinColumn(name = "llamazonBookId")
	private LlamazonBook llamazonBook;

	@Column(name = "bookKey")
	private String bookKey;

	@Column(name = "userCode")
	private String userCode;

	@Column(name = "bookFormat")
	private String bookFormat;

	public LlamazonBookUser() {
	}

	public LlamazonBookUser(int bookId, String userCode, String bookFormat) {
		this.llamazonBook = new LlamazonBook(bookId);
		this.userCode = userCode;
		this.bookFormat = bookFormat;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getBookKey() {
		return bookKey;
	}

	public void setBookKey(String bookKey) {
		this.bookKey = bookKey;
	}

	public String getUserCode() {
		return userCode;
	}

	public void setUserCode(String userCode) {
		this.userCode = userCode;
	}

	public LlamazonBook getLamazonBook() {
		return llamazonBook;
	}

	public void setLamazonBook(LlamazonBook llamazonBook) {
		this.llamazonBook = llamazonBook;
	}

	public String getBookFormat() {
		return bookFormat;
	}

	public void setBookFormat(String bookFormat) {
		this.bookFormat = bookFormat;
	}

}
