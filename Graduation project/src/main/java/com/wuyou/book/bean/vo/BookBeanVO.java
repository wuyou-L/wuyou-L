package com.wuyou.book.bean.vo;

import com.wuyou.book.bean.BookBean;
import com.wuyou.bookStock.bean.BookStockBean;
import com.wuyou.fileInfo.bean.FileInfoBean;

import java.util.List;

public class BookBeanVO {

    private BookBean bookBean;

    private BookStockBean bookStockBean;

    private List<BookBean> bookBeanList;

    private List<BookStockBean> bookStockBeanListl;


    public BookBean getBookBean() {
        return bookBean;
    }

    public void setBookBean(BookBean bookBean) {
        this.bookBean = bookBean;
    }

    public BookStockBean getBookStockBean() {
        return bookStockBean;
    }

    public void setBookStockBean(BookStockBean bookStockBean) {
        this.bookStockBean = bookStockBean;
    }
}
