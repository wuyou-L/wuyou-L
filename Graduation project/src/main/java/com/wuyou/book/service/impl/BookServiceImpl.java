package com.wuyou.book.service.impl;

import com.wuyou.book.bean.BookBean;
import com.wuyou.book.bean.BookPageBean;
import com.wuyou.book.bean.vo.BookBeanVO;
import com.wuyou.book.dao.BookMapper;
import com.wuyou.book.service.BookService;
import com.wuyou.bookStock.bean.BookStockBean;
import com.wuyou.bookStock.service.BookStockService;
import com.wuyou.fileInfo.bean.FileInfoBean;
import com.wuyou.fileInfo.bean.FileInfoPageBean;
import com.wuyou.fileInfo.service.FileInfoService;
import com.wuyou.utils.UploadUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;

/**
 * BookService,the service impl class.
 *
 * @author Autocode
 * 2022-02-26 23:14:51
 */
@Service
public class BookServiceImpl implements BookService {

    @Autowired
    private BookMapper bookMapper;

    @Autowired
    private BookStockService bookStockService;

    @Autowired
    private UploadUtil uploadUtil;

    @Autowired
    private FileInfoService fileInfoService;

    @Value("${book.upload.domainPath}")
    private String domainPath;
    @Value("${book.upload.basePath}")
    private String basePath;

    @Transactional
    @Override
    public int addBook(BookBeanVO book) {
        BookBean bean = book.getBookBean();
        BookBean bookBean1 = new BookBean();
        bookBean1.setBook_isbn(book.getBookBean().getBook_isbn());
        BookBean bookBean = this.querySingleBook(bookBean1);
        if (bookBean != null) {
            return -1;
        }
        if (book.getBookBean().getFileInfoBeans() != null && book.getBookBean().getFileInfoBeans().size() > 0) {
            bean.setBook_img_name(book.getBookBean().getFileInfoBeans().get(0).getFile_domain_url());
            bean.setBook_img_url(book.getBookBean().getFileInfoBeans().get(0).getFile_url());
        }

        int i = bookMapper.addBook(bean);
        fileInfoService.addFileInfoList(book.getBookBean().getFileInfoBeans(), bean.getBook_id());
        if (book.getBookStockBean() != null) {
            book.getBookStockBean().setStock_book_id(bean.getBook_id());
            book.getBookStockBean().setStock_occupable(book.getBookStockBean().getStock_num());
            bookStockService.addBookStock(book.getBookStockBean());
        }
        return i;
    }

    @Transactional
    @Override
    public int deleteBook(BookBean book) {
        fileInfoService.deleteFileInfoById(book.getBook_id());
        return bookMapper.deleteBook(book);
    }

    @Transactional
    @Override
    public int updateBook(BookBean book) {
        if(book.getFileInfoBeans() != null && book.getFileInfoBeans().size() > 0){
            fileInfoService.deleteFileInfoById(book.getBook_id());
            fileInfoService.addFileInfoList(book.getFileInfoBeans(), book.getBook_id());
        }
        return bookMapper.updateBook(book);
    }

    @Transactional
    @Override
    public int queryBookCount(BookBean book) {
        return bookMapper.queryBookCount(book);
    }

    @Transactional
    @Override
    public List<BookBeanVO> queryBookList(BookPageBean book) {
        // 查询图书基本信息
        if (book.getPage() != null) {
            int totalRows = bookMapper.queryBookCount(book);
            book.getPage().setTotalRows(totalRows);
            book.getPage().repaginate();
        }

        // 封装图书库存信息
        List<BookBean> bookBeans = bookMapper.queryBookList(book);
        List<BookBeanVO> bookBeanVOS = new ArrayList<>();
        if (bookBeans != null && bookBeans.size() > 0) {
            bookBeans.forEach(data -> {

                FileInfoPageBean fileInfoPageBean = new FileInfoPageBean();
                fileInfoPageBean.setRel_id(book.getBook_id());
                fileInfoPageBean.setFile_kind(1);
                List<FileInfoBean> fileInfoBeans = fileInfoService.queryFileInfoList(fileInfoPageBean);
                data.setFileInfoBeans(fileInfoBeans);
            BookBeanVO bookBeanVO = new BookBeanVO();
//                System.out.println(data.getBook_id());
                bookBeanVO.setBookBean(data);
                BookStockBean bookStockBean = new BookStockBean();
                bookStockBean.setStock_book_id(data.getBook_id());
                BookStockBean bookStockBean1 = bookStockService.querySingleBookStock(bookStockBean);
                if (bookStockBean1 == null) {
                    bookStockBean1 = new BookStockBean();
                    bookStockBean1.setStock_occupable(0);
                    bookStockBean1.setStock_num(0);
                }

                bookBeanVO.setBookStockBean(bookStockBean1);
                bookBeanVOS.add(bookBeanVO);


            });
        }
        return bookBeanVOS;
    }

    @Transactional
    @Override
    public BookBean querySingleBook(BookBean book) {
        return bookMapper.querySingleBook(book);
    }

    @Override
    public Object uploadBookFile(MultipartFile[] files) {
        String property = System.getProperty("user.dir");
        System.out.println(property);
        System.out.println(this.getClass().getClassLoader());
        String suffix = "/src/main/resources/static/uploadimage/";
        System.out.println("basePath" + basePath);
        System.out.println("domainPath" + domainPath);
        return uploadUtil.uploadFile(files, property + suffix, property + suffix);
//        return uploadUtil.uploadFile(files, basePath, domainPath);
    }


}
