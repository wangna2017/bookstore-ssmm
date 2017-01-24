package com.wn.mapper;

import java.util.List;

import com.wn.model.Book;

public interface BookMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Book record);

    int insertSelective(Book record);

    Book selectByPrimaryKey(Integer id);
    
    List<Book> selectSelective(Book record);

    int updateByPrimaryKeySelective(Book record);

    int updateByPrimaryKey(Book record);
}