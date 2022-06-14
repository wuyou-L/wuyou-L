package com.wuyou.bookCate.dao;
import com.wuyou.bookCate.bean.BookCateBean;
import com.wuyou.bookCate.bean.BookCatePageBean;
import java.util.List;
import org.apache.ibatis.annotations.Mapper;


/**
 * BookCateMapper,the mappers.
 * @author Autocode
 * 2022-02-26 23:20:49
 */

@Mapper
public interface BookCateMapper {

	public int addBookCate(BookCateBean bookCate);

	public int deleteBookCate(BookCateBean bookCate);

	public int updateBookCate(BookCateBean bookCate);

	public int queryBookCateCount(BookCateBean bookCate);

	public List<BookCateBean> queryBookCateList(BookCatePageBean bookCate);

	public BookCateBean querySingleBookCate(BookCateBean bookCate);


	String queryMaxCode(String cateCode);
}
