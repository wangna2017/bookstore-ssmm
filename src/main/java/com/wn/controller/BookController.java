package com.wn.controller;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.time.DateFormatUtils;
import org.apache.commons.lang3.time.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.wn.model.Book;
import com.wn.resp.BookResp;
import com.wn.service.BookService;

@RestController
@RequestMapping("/book")
public class BookController {
	@Autowired
	private BookService bookService;

	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public boolean add(@RequestParam("bookName") String bookName, @RequestParam("author") String author,
			@RequestParam("publishDate") String publishDateStr) {
		Date publishDate;
		try {
			publishDate = DateUtils.parseDate(publishDateStr, "yyyy-MM-dd HH:mm:ss");
			return bookService.addBook(bookName, author, publishDate);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return false;
	}

	@RequestMapping(value = "/add2", method = RequestMethod.POST)
	public boolean add2(@RequestParam("bookName") String bookName, @RequestParam("author") String author,
			@RequestParam("publishDate") @DateTimeFormat(pattern = "yyyy-mm-dd HH:mm:ss") Date publishDate) {
		return bookService.addBook(bookName, author, publishDate);
	}

	@RequestMapping(value = "/get/{id}", method = RequestMethod.GET)
	public BookResp get(@PathVariable("id") Integer id) {
		Book book = bookService.getBook(id);
		String dateStr = DateFormatUtils.format(book.getPublishDate(), "yyyy-MM-dd HH:mm:ss");
		BookResp resp = new BookResp();
		resp.setId(book.getId());
		resp.setAuthor(book.getAuthor());
		resp.setBookName(book.getBookName());
		resp.setPublishDate(dateStr);
		return resp;
	}

	// @RequestMapping(value="/delete/{id}",method=RequestMethod.DELETE)
	// public boolean delete(@PathVariable("id") Integer id){
	// return bookService.deleteBook(id);
	// }
	@RequestMapping(value = "/delete", method = RequestMethod.DELETE)
	public boolean delete(@RequestParam("id") Integer id) {
		return bookService.deleteBook(id);
	}

	// @RequestMapping(value = "/getBook", method = RequestMethod.GET)
	// public List<Book> getBook(@RequestParam(value="bookName",required=false)
	// String bookName,
	// @RequestParam(value="author",required=false) String author,
	// @RequestParam(value="publishDate",required=false) @DateTimeFormat(pattern
	// = "yyyy-mm-dd HH:mm:ss") Date publishDate){
	// return bookService.getBookSelective(bookName, author, publishDate);
	// }
	@RequestMapping(value = "/getBook", method = RequestMethod.GET)
	public List<BookResp> getBook(@RequestParam(value = "bookName", required = false) String bookName,
			@RequestParam(value = "author", required = false) String author,
			@RequestParam(value = "publishDate", required = false) @DateTimeFormat(pattern = "yyyy-mm") Date publishDate) {
		List<Book> bookList = bookService.getBookSelective(bookName, author, publishDate);

		List<BookResp> respList = new ArrayList<>();// List<Car> carList = new
													// ArrayList<>();

		for (Book book : bookList) {
			BookResp resp = new BookResp();
			resp.setId(book.getId());
			resp.setAuthor(book.getAuthor());
			resp.setBookName(book.getBookName());

			String dateStr = DateFormatUtils.format(book.getPublishDate(), "yyyy-MM");
			resp.setPublishDate(dateStr);
			respList.add(resp);
		}

		return respList;
	}

}
