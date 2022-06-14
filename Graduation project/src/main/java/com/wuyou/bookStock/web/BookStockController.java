package com.wuyou.bookStock.web;
import java.util.List;

import com.wuyou.base.bean.PaginationBean;
import com.wuyou.base.resp.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.wuyou.bookStock.bean.BookStockBean;
import com.wuyou.bookStock.bean.BookStockPageBean;
import com.wuyou.bookStock.service.BookStockService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * BookStockController,the web actions.
 * @author Autocode
 * 2022-02-26 23:21:47
 */
@Api(tags = "库存接口")
@RestController
@CrossOrigin
@RequestMapping("/bookStock")
public class BookStockController {


	@Autowired
	private BookStockService bookStockService;

	@ApiOperation("增加库存")
	@PostMapping(value = "/addBookStock",produces = {"application/json;charset=UTF-8"})
	public Object addBookStock(@RequestBody BookStockBean bookStock) {
		return Result.ok().data("rows",bookStockService.addBookStock(bookStock));
	}

	@ApiOperation("根据ID删除库存")
	@PostMapping(value = "/deleteBookStock",produces = {"application/json;charset=UTF-8"})
	public Object deleteBookStock(@RequestBody BookStockBean bookStock) {
		return Result.ok().data("rows",bookStockService.deleteBookStock(bookStock));
	}

	@ApiOperation("根据ID修改库存")
	@PostMapping(value = "/updateBookStock",produces = {"application/json;charset=UTF-8"})
	public Object updateBookStock(@RequestBody BookStockBean bookStock) {
		return Result.ok().data("rows",bookStockService.updateBookStock(bookStock));
	}

	/**

	@PostMapping(value = "/queryBookStockCount",produces = {"application/json;charset=UTF-8"})
	public Object queryBookStockCount(@RequestBody BookStockBean bookStock) {
		return Result.ok().data("rows",bookStockService.queryBookStockCount(bookStock));
	}

	*/

	@ApiOperation("查询库存列表")
	@PostMapping(value = "/queryBookStockList",produces = {"application/json;charset=UTF-8"})
	public Object queryBookStockList(@RequestBody BookStockPageBean bookStock) {
		List<BookStockBean> list = bookStockService.queryBookStockList(bookStock);
		PaginationBean page = bookStock.getPage();
		return Result.ok().data("dataList",list).data("page", page);
	}

	@ApiOperation("根据ID查询单个库存")
	@PostMapping(value = "/querySingleBookStock",produces = {"application/json;charset=UTF-8"})
	public Object querySingleBookStock(@RequestBody BookStockBean bookStock) {
		return Result.ok().data("bookStock",bookStockService.querySingleBookStock(bookStock));
	}


}
