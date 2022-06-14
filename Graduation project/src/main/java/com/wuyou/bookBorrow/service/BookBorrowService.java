package com.wuyou.bookBorrow.service;
import com.wuyou.bookBorrow.bean.BookBorrowBean;
import com.wuyou.bookBorrow.bean.BookBorrowPageBean;
import java.util.List;
/**
 * BookBorrowService,the services.
 * @author Autocode
 * 2022-02-26 23:19:40
 */
public interface BookBorrowService {

	public int addBookBorrow(BookBorrowBean bookBorrow);

	public int deleteBookBorrow(BookBorrowBean bookBorrow);

	public int updateBookBorrow(BookBorrowBean bookBorrow);

	public int queryBookBorrowCount(BookBorrowBean bookBorrow);

	public List<BookBorrowBean> queryBookBorrowList(BookBorrowPageBean bookBorrow);

	public BookBorrowBean querySingleBookBorrow(BookBorrowBean bookBorrow);


}
