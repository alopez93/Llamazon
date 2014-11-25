package com.llamazon.service.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.llamazon.service.service.BookService;

@RestController
@RequestMapping("/data")
public class DataController {
	
	@Autowired BookService bookService;
	
	@RequestMapping("/create")
	public String createBookKey(@RequestParam(value = "bookId", required = true)Integer bookId, 
								  @RequestParam(value = "userCode", required = true)String userCode, 
								  @RequestParam(value = "bookFormatType", required = true)String bookFormatType){
		String bookKey = bookService.generateBookKey(bookId, userCode, bookFormatType);
		return bookKey;
	}
	
	@RequestMapping("/delete")
	public boolean deleteBookKey(@RequestParam(value = "bookId", required = true)Integer bookId, 
								  @RequestParam(value = "userCode", required = true)String userCode, 
								  @RequestParam(value = "bookFormatType", required = true)String bookFormatType){
		return bookService.deleteBookKey(bookId, userCode, bookFormatType);
	}
	
	@RequestMapping("/validate")
	public boolean validateBookKey(@RequestParam(value = "bookId", required = true)Integer bookId, 
								  @RequestParam(value = "userCode", required = true)String userCode,
								  @RequestParam(value = "bookKey", required = true)String bookKey,	
								  @RequestParam(value = "bookFormatType", required = true)String bookFormatType){
		return bookService.validateBookKey(bookId, userCode, bookKey, bookFormatType);
	}
	
	
	
}
