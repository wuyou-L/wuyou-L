package com.wuyou.bookBorrow.service.impl;

import com.wuyou.bookBorrow.bean.BookBorrowBean;
import com.wuyou.bookBorrow.bean.BookBorrowPageBean;
import com.wuyou.bookBorrow.dao.BookBorrowMapper;
import com.wuyou.bookBorrow.service.BookBorrowService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * BookBorrowService,the service impl class.
 * @author Autocode
 * 2022-02-26 23:19:40
 */
@Service
public class BookBorrowServiceImpl implements BookBorrowService {

	@Autowired
	private BookBorrowMapper bookBorrowMapper;

	@Transactional
	@Override
	public int addBookBorrow(BookBorrowBean bookBorrow) {
		return bookBorrowMapper.addBookBorrow(bookBorrow);
	}
	@Transactional
	@Override
	public int deleteBookBorrow(BookBorrowBean bookBorrow) {
		return bookBorrowMapper.deleteBookBorrow(bookBorrow);
	}
	@Transactional
	@Override
	public int updateBookBorrow(BookBorrowBean bookBorrow) {
		return bookBorrowMapper.updateBookBorrow(bookBorrow);
	}
	@Transactional
	@Override
	public int queryBookBorrowCount(BookBorrowBean bookBorrow) {
		return bookBorrowMapper.queryBookBorrowCount(bookBorrow);
	}
	@Transactional
	@Override
	public List<BookBorrowBean> queryBookBorrowList(BookBorrowPageBean bookBorrow) {
		if(bookBorrow.getPage() != null){
			int totalRows = bookBorrowMapper.queryBookBorrowCount(bookBorrow);
			bookBorrow.getPage().setTotalRows(totalRows);
			bookBorrow.getPage().repaginate();
		}
		return bookBorrowMapper.queryBookBorrowList(bookBorrow);
	}
	@Transactional
	@Override
	public BookBorrowBean querySingleBookBorrow(BookBorrowBean bookBorrow) {
		return bookBorrowMapper.querySingleBookBorrow(bookBorrow);
	}


}
