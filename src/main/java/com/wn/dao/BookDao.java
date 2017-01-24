package com.wn.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.wn.mapper.BookMapper;
import com.wn.model.Book;

@Repository
public class BookDao {
	@Autowired
	private BookMapper bookMapper;

	public boolean add(Book book) {
		return bookMapper.insert(book) == 1 ? true : false;
	}

	public Book selectById(Integer id) {
		return bookMapper.selectByPrimaryKey(id);
	}

	public boolean delete(Integer id) {
		return bookMapper.deleteByPrimaryKey(id) == 1 ? true : false;
	}

	public boolean update(Book book) {
		return bookMapper.updateByPrimaryKey(book) == 1 ? true : false;
	}

	public List<Book> selectByCondition(Book book) {
		return bookMapper.selectSelective(book);
	}
}
