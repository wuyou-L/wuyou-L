package com.wuyou.bookCate.service.impl;

import com.wuyou.bookCate.bean.BookCateBean;
import com.wuyou.bookCate.bean.BookCatePageBean;
import com.wuyou.bookCate.dao.BookCateMapper;
import com.wuyou.bookCate.service.BookCateService;
import io.netty.util.internal.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * BookCateService,the service impl class.
 * @author Autocode
 * 2022-02-26 23:20:49
 */
@Service
public class BookCateServiceImpl implements BookCateService {

	@Autowired
	private BookCateMapper bookCateMapper;
	@Transactional
	@Override
	public int addBookCate(BookCateBean bookCate) {
		if (!StringUtil.isNullOrEmpty(bookCate.getCate_parent_code())) {
			String s = bookCateMapper.queryMaxCode(bookCate.getCate_parent_code());
			if (!StringUtil.isNullOrEmpty(s)) {
				String lastCode = s.substring(s.length() - 3, s.length());
				int cateCodeInt = Integer.parseInt(lastCode) + 1;
				String cateCode = s.substring(0, s.length()-3) + String.format("%03d", cateCodeInt);
				bookCate.setCate_code(cateCode);
			}else {
				bookCate.setCate_code(bookCate.getCate_parent_code() + "001");
			}
		}else {
			return -1;
		}
		return bookCateMapper.addBookCate(bookCate);
	}
	@Transactional
	@Override
	public int deleteBookCate(BookCateBean bookCate) {
		if(bookCate.getCate_code() != null){
			BookCateBean bookCateBean = new BookCateBean();
			bookCateBean.setCate_parent_code(bookCate.getCate_code());
			bookCateMapper.deleteBookCate(bookCateBean);
		}
		return bookCateMapper.deleteBookCate(bookCate);
	}
	@Transactional
	@Override
	public int updateBookCate(BookCateBean bookCate) {
		return bookCateMapper.updateBookCate(bookCate);
	}
	@Transactional
	@Override
	public int queryBookCateCount(BookCateBean bookCate) {
		return bookCateMapper.queryBookCateCount(bookCate);
	}
	@Transactional
	@Override
	public List<BookCateBean> queryBookCateList(BookCatePageBean bookCate) {
		if(bookCate.getPage() != null){
			int totalRows = bookCateMapper.queryBookCateCount(bookCate);
			bookCate.getPage().setTotalRows(totalRows);
			bookCate.getPage().repaginate();
		}
		return bookCateMapper.queryBookCateList(bookCate);
	}
	@Transactional
	@Override
	public BookCateBean querySingleBookCate(BookCateBean bookCate) {
		return bookCateMapper.querySingleBookCate(bookCate);
	}


}
