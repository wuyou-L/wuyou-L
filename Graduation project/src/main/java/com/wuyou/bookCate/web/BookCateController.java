package com.wuyou.bookCate.web;

import com.wuyou.base.bean.PaginationBean;
import com.wuyou.base.resp.Result;
import com.wuyou.bookCate.bean.BookCateBean;
import com.wuyou.bookCate.bean.BookCatePageBean;
import com.wuyou.bookCate.service.BookCateService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * BookCateController,the web actions.
 * @author Autocode
 * 2022-02-26 23:20:49
 */
@Api(tags = "分类接口")
@RestController
@CrossOrigin
@RequestMapping("/bookCate")
public class BookCateController {


	@Autowired
	private BookCateService bookCateService;

	@ApiOperation("增加分类")
	@PostMapping(value = "/addBookCate",produces = {"application/json;charset=UTF-8"})
	public Object addBookCate(@RequestBody BookCateBean bookCate) {
		return Result.ok().data("rows",bookCateService.addBookCate(bookCate));
	}

	@ApiOperation("根据ID删除分类")
	@PostMapping(value = "/deleteBookCate",produces = {"application/json;charset=UTF-8"})
	public Object deleteBookCate(@RequestBody BookCateBean bookCate) {
		return Result.ok().data("rows",bookCateService.deleteBookCate(bookCate));
	}

	@ApiOperation("根据ID修改分类")
	@PostMapping(value = "/updateBookCate",produces = {"application/json;charset=UTF-8"})
	public Object updateBookCate(@RequestBody BookCateBean bookCate) {
		return Result.ok().data("rows",bookCateService.updateBookCate(bookCate));
	}

	/**

	@PostMapping(value = "/queryBookCateCount",produces = {"application/json;charset=UTF-8"})
	public Object queryBookCateCount(@RequestBody BookCateBean bookCate) {
		return Result.ok().data("rows",bookCateService.queryBookCateCount(bookCate));
	}

	*/

	@ApiOperation("查询分类列表")
	@PostMapping(value = "/queryBookCateList",produces = {"application/json;charset=UTF-8"})
	public Object queryBookCateList(@RequestBody BookCatePageBean bookCate) {
		List<BookCateBean> list = bookCateService.queryBookCateList(bookCate);
		PaginationBean page = bookCate.getPage();
		return Result.ok().data("dataList",list).data("page", page);
	}

	@ApiOperation("根据ID查询单个分类")
	@PostMapping(value = "/querySingleBookCate",produces = {"application/json;charset=UTF-8"})
	public Object querySingleBookCate(@RequestBody BookCateBean bookCate) {
		return Result.ok().data("bookCate",bookCateService.querySingleBookCate(bookCate));
	}

	@ApiOperation("查询分类数树")
	@PostMapping(value = "/queryBookCateTree",produces = {"application/json;charset=UTF-8"})
	public Object queryBookCateTree(@RequestBody BookCateBean bookCate) {
		BookCateBean bookCateBean = bookCateService.querySingleBookCate(bookCate);
		BookCateBean recursion = recursion(bookCateBean);
		List<BookCateBean> bookCateBeans = new ArrayList<>();
		bookCateBeans.add(recursion);
		return Result.ok().data("dataList",bookCateBeans);
	}

	private BookCateBean recursion(BookCateBean bookCate){
		BookCatePageBean bookCatePageBean = new BookCatePageBean();
		bookCatePageBean.setCate_parent_code(bookCate.getCate_code());
//		bookCatePageBean.setIs_using(1);
		List<BookCateBean> bookCateBeans = bookCateService.queryBookCateList(bookCatePageBean);
		if (bookCateBeans != null && bookCateBeans.size() > 0) {
			bookCate.setBookCateBeanList(bookCateBeans);
			for(BookCateBean data : bookCateBeans){
				recursion(data);
			}
		}
		return bookCate;
	}


}
