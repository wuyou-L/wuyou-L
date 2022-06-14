package com.wuyou.book.service;
import com.wuyou.book.bean.BookBean;
import com.wuyou.book.bean.BookPageBean;
import com.wuyou.book.bean.vo.BookBeanVO;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
/**
 * BookService,the services.
 * @author Autocode
 * 2022-02-26 23:14:51
 */
public interface BookService {

	public int addBook(BookBeanVO book);

	public int deleteBook(BookBean book);

	public int updateBook(BookBean book);

	public int queryBookCount(BookBean book);

	public List<BookBeanVO> queryBookList(BookPageBean book);

	public BookBean querySingleBook(BookBean book);

	Object uploadBookFile(MultipartFile[] files);
}
