package com.llamazon.service.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.supercsv.io.CsvBeanWriter;
import org.supercsv.io.ICsvBeanWriter;
import org.supercsv.prefs.CsvPreference;

import com.llamazon.service.model.LlamazonBook;
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
		LlamazonBook lb = bookService.getBook(bookId);
		lb.setBookFormat(bookFormatType);
		mv.addObject("book", lb);
		return mv;
	}
	
	@RequestMapping(value = "/downloadBook")
	public void downloadBook(@RequestParam(value = "format", required=true)String format,
							   @RequestParam(value = "bookId", required = true)Integer bookId,
							   HttpServletResponse response) throws IOException{
		System.out.println("DEBUG - In DownloadBook");
		LlamazonBook book = bookService.getBook(bookId.intValue());
		String csvFileName = book.getBookTitle() + format + ".csv";
		csvFileName = csvFileName.replace(' ', '_');
		
		String headerKey = "Content-Disposition";
        String headerValue = String.format("attachment;filename=\"%s\"", csvFileName);
        System.out.println(String.format("attachment;filename=\"%s\"", csvFileName));
        response.setHeader(headerKey, headerValue);
		System.out.println("DEBUG - Headers set. File name is " + csvFileName);
        ICsvBeanWriter csvWriter = new CsvBeanWriter(response.getWriter(), CsvPreference.STANDARD_PREFERENCE);
        
        String[] header = { "BookTitle", "Description", "BookImage", "Author"};
        
        csvWriter.writeHeader(header);
        
        csvWriter.write(book, header);
        csvWriter.flush();
        System.out.println("DEBUG - Downloading file");
        csvWriter.close();
	}
	
}
