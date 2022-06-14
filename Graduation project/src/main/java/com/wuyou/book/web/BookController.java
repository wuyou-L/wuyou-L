package com.wuyou.book.web;

import com.alibaba.druid.util.StringUtils;
import com.wuyou.base.bean.PaginationBean;
import com.wuyou.base.resp.Result;
import com.wuyou.book.bean.BookBean;
import com.wuyou.book.bean.BookPageBean;
import com.wuyou.book.bean.vo.BookBeanVO;
import com.wuyou.book.service.BookService;
import com.wuyou.bookBorrow.bean.BookBorrowBean;
import com.wuyou.bookBorrow.bean.BookBorrowPageBean;
import com.wuyou.bookBorrow.service.BookBorrowService;
import com.wuyou.bookStock.bean.BookStockBean;
import com.wuyou.bookStock.bean.BookStockPageBean;
import com.wuyou.bookStock.service.BookStockService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * BookController,the web actions.
 * @author Autocode
 * 2022-02-26 23:14:51
 */
@Api(tags = "图书接口")
@RestController
@CrossOrigin
@RequestMapping("/book")
public class BookController {


	@Autowired
	private BookService bookService;
	@Autowired
	private BookStockService bookStockService;
	@Autowired
	private BookBorrowService bookBorrowService;

	@ApiOperation("增加图书")
	@PostMapping(value = "/addBook",produces = {"application/json;charset=UTF-8"})
	public Object addBook(@RequestBody BookBeanVO book) {
		System.out.println("---------------------------------------->" + book.getBookBean().getFileInfoBeans().size());
		int value = bookService.addBook(book);
		if (value == -1) {
			return Result.error().message("ISBN号已存在");
		}
		return Result.ok().data("rows", value);
	}

	@ApiOperation("批量增加图书")
	@PostMapping(value = "/addBookList",produces = {"application/json;charset=UTF-8"})
	public Object addBookList(@RequestBody BookBeanVO book) {
		return Result.ok().data("rows",bookService.addBook(book));
	}

	@ApiOperation("根据ID删除图书")
	@PostMapping(value = "/deleteBook",produces = {"application/json;charset=UTF-8"})
	public Object deleteBook(@RequestBody BookBean book) {
		BookStockPageBean bookStockBean = new BookStockPageBean();
		bookStockBean.setStock_book_id(book.getBook_id());
		List<BookStockBean> bookStockBeans = bookStockService.queryBookStockList(bookStockBean);
		if (bookStockBeans.size() > 0 && bookStockBeans != null) {
			if (bookStockBeans.get(0).getStock_num() > 0) {

				return Result.error().message("当前图书存在库存，不可以删除！");
			}
		}else {
			BookBorrowPageBean bookBorrowPageBean = new BookBorrowPageBean();
			bookBorrowPageBean.setBorrow_book_id(book.getBook_id());
			List<BookBorrowBean> bookBorrowBeans = bookBorrowService.queryBookBorrowList(bookBorrowPageBean);
			if(bookBorrowBeans.size() > 0 && bookBorrowBeans != null){
				return Result.error().message("当前图书有用户正在借阅，不可删除！！");
			}
		}
		bookStockService.deleteBookStock(bookStockBean);
		return Result.ok().data("rows",bookService.deleteBook(book));
	}

	@ApiOperation("根据ID修改图书")
	@PostMapping(value = "/updateBook",produces = {"application/json;charset=UTF-8"})
	public Object updateBook(@RequestBody BookBean book) {
		return Result.ok().data("rows",bookService.updateBook(book));
	}

	/**

	@PostMapping(value = "/queryBookCount",produces = {"application/json;charset=UTF-8"})
	public Object queryBookCount(@RequestBody BookBean book) {
		return Result.ok().data("rows",bookService.queryBookCount(book));
	}

	*/

	@ApiOperation("查询图书列表")
	@PostMapping(value = "/queryBookList",produces = {"application/json;charset=UTF-8"})
	public Object queryBookList(@RequestBody BookPageBean book) {
		List<BookBeanVO> list = bookService.queryBookList(book);
		PaginationBean page = book.getPage();
		return Result.ok().data("dataList",list).data("page", page);
	}

	@ApiOperation("根据ID查询单个图书")
	@PostMapping(value = "/querySingleBook",produces = {"application/json;charset=UTF-8"})
	public Object querySingleBook(@RequestBody BookBean book) {
		return Result.ok().data("book",bookService.querySingleBook(book));
	}


	@PostMapping(value = "upLoadBookImg",produces = {"application/json;charset=UTF-8"})
	public Object upLoadBookImg(@RequestBody MultipartFile[] files){
		return Result.ok().data("data", bookService.uploadBookFile(files));
	}

	@PostMapping(value = "importBookList")
	public Object importExcel(@RequestBody MultipartFile excel){
		if(excel.getOriginalFilename().endsWith(".xlsx")){
			try {
				XSSFWorkbook workbook = new XSSFWorkbook(excel.getInputStream());
				XSSFSheet sheetAt = workbook.getSheetAt(0);
				int lastRowNum = sheetAt.getLastRowNum();   // 最后一行下标

				for(int i = 1; i < lastRowNum + 1; i ++ ){
					XSSFRow row = sheetAt.getRow(i);
					XSSFCell cell = row.getCell(0);
					cell.setCellType(Cell.CELL_TYPE_STRING);
					String isbn = cell.getStringCellValue();
					if (StringUtils.isEmpty(isbn) || StringUtils.isEmpty(isbn)) {
						continue;
					}
//                    String card_no = row.getCell(0).getStringCellValue();
					XSSFCell cell1 = row.getCell(1);
					cell1.setCellType(Cell.CELL_TYPE_STRING);
					String book_name = cell1.getStringCellValue();
					if (StringUtils.isEmpty(book_name) || StringUtils.isEmpty(book_name)) {
						continue;
					}
					XSSFCell cell2 = row.getCell(2);
					String book_enlish_name = null;
					try {
						cell2.setCellType(Cell.CELL_TYPE_STRING);
						book_enlish_name = cell2.getStringCellValue();
					} catch (Exception e) {
						book_enlish_name = book_name;
					}
					XSSFCell cell3 = row.getCell(3);
					cell3.setCellType(Cell.CELL_TYPE_STRING);
					String book_publisher = null;
					String book_publisher_time = null;
					try {
						book_publisher = cell3.getStringCellValue();
					} catch (Exception e) {
						e.printStackTrace();
					}
					XSSFCell cell4 = row.getCell(4);
					cell4.setCellType(Cell.CELL_TYPE_STRING);
					book_publisher_time = cell4.getStringCellValue();
					XSSFCell cell5 = row.getCell(5);
					cell5.setCellType(Cell.CELL_TYPE_STRING);
					String book_author =  cell5.getStringCellValue();
					XSSFCell cell6 = row.getCell(6);
					cell6.setCellType(Cell.CELL_TYPE_STRING);
					String book_price =  cell6.getStringCellValue();
					XSSFCell cell7 = row.getCell(7);
					cell7.setCellType(Cell.CELL_TYPE_STRING);
					String book_stock =  cell7.getStringCellValue();
					XSSFCell cell8 = row.getCell(8);
					cell8.setCellType(Cell.CELL_TYPE_STRING);
					String book_desc =  cell8.getStringCellValue();
					try {
						addBookList(isbn, book_name, book_enlish_name, book_publisher, book_publisher_time, book_author, book_price, book_stock, book_desc);
					} catch (ParseException e) {
						e.printStackTrace();
						continue;
					}
				}
			} catch (IOException e) {
				e.printStackTrace();
				return Result.error().message("导入失败");
			}
		}else if(excel.getOriginalFilename().endsWith(".xls")){
			try {
				HSSFWorkbook workbook = new HSSFWorkbook(excel.getInputStream());
				HSSFSheet sheetAt = workbook.getSheetAt(0);
				int lastRowNum = sheetAt.getLastRowNum();
				for(int i = 1; i < lastRowNum + 1; i ++ ){
					HSSFRow row = sheetAt.getRow(i);
					HSSFCell cell = row.getCell(0);
					cell.setCellType(Cell.CELL_TYPE_STRING);
					String isbn = cell.getStringCellValue();
					if (StringUtils.isEmpty(isbn) || StringUtils.isEmpty(isbn)) {
						continue;
					}
//                    String card_no = row.getCell(0).getStringCellValue();
					HSSFCell cell1 = row.getCell(1);
					cell1.setCellType(Cell.CELL_TYPE_STRING);
					String book_name = cell1.getStringCellValue();
					if (StringUtils.isEmpty(book_name) || StringUtils.isEmpty(book_name)) {
						continue;
					}
					HSSFCell cell2 = row.getCell(2);
					cell2.setCellType(Cell.CELL_TYPE_STRING);
					String book_enlish_name =  cell2.getStringCellValue();
					HSSFCell cell3 = row.getCell(3);
					cell3.setCellType(Cell.CELL_TYPE_STRING);
					String book_publisher =  cell3.getStringCellValue();
					HSSFCell cell4 = row.getCell(4);
					cell4.setCellType(Cell.CELL_TYPE_STRING);
					String book_publisher_time =  cell4.getStringCellValue();
					HSSFCell cell5 = row.getCell(5);
					cell5.setCellType(Cell.CELL_TYPE_STRING);
					String book_author =  cell5.getStringCellValue();
					HSSFCell cell6 = row.getCell(6);
					cell6.setCellType(Cell.CELL_TYPE_STRING);
					String book_price =  cell6.getStringCellValue();
					HSSFCell cell7 = row.getCell(7);
					cell7.setCellType(Cell.CELL_TYPE_STRING);
					String book_stock =  cell7.getStringCellValue();
					HSSFCell cell8 = row.getCell(8);
					cell8.setCellType(Cell.CELL_TYPE_STRING);
					String book_desc =  cell8.getStringCellValue();
					try {
						addBookList(isbn, book_name, book_enlish_name, book_publisher, book_publisher_time, book_author, book_price, book_stock, book_desc);
					} catch (ParseException e) {
						e.printStackTrace();
						continue;
					}
				}
			} catch (IOException e) {
				e.printStackTrace();
				return Result.error().message("导入失败");
			}
		}
		return Result.error().message("请传入excel表格");
	}


	private void addBookList(String isbn, String book_name, String book_english_name, String publisher, String time, String book_author, String book_price, String book_stock, String book_desc ) throws ParseException {
		BookBean bookBean = new BookBean();
		bookBean.setBook_isbn(isbn);
		bookBean.setBook_name(book_name);
		bookBean.setBook_name_english(book_english_name);
		bookBean.setBook_publisher(publisher);
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
		if (StringUtils.isEmpty(time)) {
			bookBean.setBook_publisher_time(new Timestamp(new Date().getTime()));
		}else {
			bookBean.setBook_publisher_time(new Timestamp(simpleDateFormat.parse(time).getTime()));
		}
		bookBean.setBook_author(book_author);
		bookBean.setBook_price(Double.valueOf(book_price));
		bookBean.setBook_desc(book_desc);
		BookStockBean bookStockBean = new BookStockBean();
		bookStockBean.setStock_num(Integer.valueOf(book_stock));
		bookStockBean.setStock_occupable(Integer.valueOf(book_stock));

		BookBeanVO bookBeanVO = new BookBeanVO();
		bookBeanVO.setBookBean(bookBean);
		bookBeanVO.setBookStockBean(bookStockBean);
		bookService.addBook(bookBeanVO);
	}
}
