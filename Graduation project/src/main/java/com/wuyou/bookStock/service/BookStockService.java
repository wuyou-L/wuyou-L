package com.wuyou.bookStock.service;
import com.wuyou.bookStock.bean.BookStockBean;
import com.wuyou.bookStock.bean.BookStockPageBean;
import java.util.List;
/**
 * BookStockService,the services.
 * @author Autocode
 * 2022-02-26 23:21:47
 */
public interface BookStockService {

	public int addBookStock(BookStockBean bookStock);

	public int deleteBookStock(BookStockBean bookStock);

	public int updateBookStock(BookStockBean bookStock);

	public int queryBookStockCount(BookStockBean bookStock);

	public List<BookStockBean> queryBookStockList(BookStockPageBean bookStock);

	public BookStockBean querySingleBookStock(BookStockBean bookStock);


}
