package com.wuyou.book.dao;
import com.wuyou.book.bean.BookBean;
import com.wuyou.book.bean.BookPageBean;
import java.util.List;
import org.apache.ibatis.annotations.Mapper;


/**
 * BookMapper,the mappers.
 * @author Autocode
 * 2022-02-26 23:14:51
 */

@Mapper
public interface BookMapper {

	public int addBook(BookBean book);

	public int deleteBook(BookBean book);

	public int updateBook(BookBean book);

	public int queryBookCount(BookBean book);

	public List<BookBean> queryBookList(BookPageBean book);

	public BookBean querySingleBook(BookBean book);


}
