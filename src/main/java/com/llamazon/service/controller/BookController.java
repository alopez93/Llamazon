package com.llamazon.service.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.llamazon.service.service.BookService;

@Controller
@RequestMapping("/book")
public class BookController {

	@Autowired BookService bookService;
	
	@RequestMapping("/display")
	public ModelAndView displayBook(@RequestParam(value = "bookId", required = true)Integer bookId, 
							  @RequestParam(value = "userCode", required = true)String userCode,
							  @RequestParam(value = "bookKey", required = true)String bookKey,	  
							  @RequestParam(value = "bookFormatType", required = true)String bookFormatType){
		boolean success = bookService.validateBookKey(bookId, userCode, bookKey, bookFormatType);
		if(!success){
			ModelAndView mv = new ModelAndView("llamazon/error");
			return mv;
		}
		ModelAndView mv = new ModelAndView("llamazon/book");
		mv.addObject("book", bookService.getBook(bookId));
		return mv;
	}
	
}
