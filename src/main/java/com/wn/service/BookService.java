package com.wn.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wn.dao.BookDao;
import com.wn.model.Book;

@Service
public class BookService {
	@Autowired
	private BookDao bookDao;

	public boolean addBook(String bookName, String author, Date publishDate) {
		Book book = new Book();
		book.setBookName(bookName);
		book.setAuthor(author);
		book.setPublishDate(publishDate);

		return bookDao.add(book);
	}

	public Book getBook(Integer id) {
		return bookDao.selectById(id);
	}

	public boolean deleteBook(Integer id) {
		return bookDao.delete(id);
	}

	public boolean updateBook(Integer id, String bookName, String author, Date publishDate) {
		Book book = getBook(id);
		book.setBookName(bookName);
		book.setAuthor(author);
		book.setPublishDate(publishDate);
		return bookDao.update(book);
	}
	public List<Book> getBookSelective(String bookName, String author, Date publishDate){
		Book book = new Book();
		book.setAuthor(author);
		book.setBookName(bookName);
		book.setPublishDate(publishDate);
		
		return bookDao.selectByCondition(book);
	}
}
