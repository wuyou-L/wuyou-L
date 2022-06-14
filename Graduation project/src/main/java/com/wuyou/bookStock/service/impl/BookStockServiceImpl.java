package com.wuyou.bookStock.service.impl;

import com.wuyou.bookStock.bean.BookStockBean;
import com.wuyou.bookStock.bean.BookStockPageBean;
import com.wuyou.bookStock.dao.BookStockMapper;
import com.wuyou.bookStock.service.BookStockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * BookStockService,the service impl class.
 * @author Autocode
 * 2022-02-26 23:21:47
 */
@Service
public class BookStockServiceImpl implements BookStockService {

	@Autowired
	private BookStockMapper bookStockMapper;
	@Transactional
	@Override
	public int addBookStock(BookStockBean bookStock) {
		return bookStockMapper.addBookStock(bookStock);
	}
	@Transactional
	@Override
	public int deleteBookStock(BookStockBean bookStock) {
		return bookStockMapper.deleteBookStock(bookStock);
	}
	@Transactional
	@Override
	public int updateBookStock(BookStockBean bookStock) {
		BookStockBean bookStockBean1 = new BookStockBean();
		bookStockBean1.setStock_book_id(bookStock.getStock_book_id());
		BookStockBean bookStockBean = this.querySingleBookStock(bookStockBean1);
		if (bookStockBean == null) {
			return this.addBookStock(bookStock);
		}
		return bookStockMapper.updateBookStock(bookStock);
	}
	@Transactional
	@Override
	public int queryBookStockCount(BookStockBean bookStock) {
		return bookStockMapper.queryBookStockCount(bookStock);
	}
	@Transactional
	@Override
	public List<BookStockBean> queryBookStockList(BookStockPageBean bookStock) {
		if(bookStock.getPage() != null){
			int totalRows = bookStockMapper.queryBookStockCount(bookStock);
			bookStock.getPage().setTotalRows(totalRows);
			bookStock.getPage().repaginate();
		}
		return bookStockMapper.queryBookStockList(bookStock);
	}
	@Transactional
	@Override
	public BookStockBean querySingleBookStock(BookStockBean bookStock) {
		return bookStockMapper.querySingleBookStock(bookStock);
	}


}
