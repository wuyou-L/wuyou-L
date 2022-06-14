package com.wuyou.bookBorrow.dao;
import com.wuyou.bookBorrow.bean.BookBorrowBean;
import com.wuyou.bookBorrow.bean.BookBorrowPageBean;
import java.util.List;
import org.apache.ibatis.annotations.Mapper;


/**
 * BookBorrowMapper,the mappers.
 * @author Autocode
 * 2022-02-26 23:19:40
 */

@Mapper
public interface BookBorrowMapper {

	public int addBookBorrow(BookBorrowBean bookBorrow);

	public int deleteBookBorrow(BookBorrowBean bookBorrow);

	public int updateBookBorrow(BookBorrowBean bookBorrow);

	public int queryBookBorrowCount(BookBorrowBean bookBorrow);

	public List<BookBorrowBean> queryBookBorrowList(BookBorrowPageBean bookBorrow);

	public BookBorrowBean querySingleBookBorrow(BookBorrowBean bookBorrow);


}
