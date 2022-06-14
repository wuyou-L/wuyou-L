package com.wuyou.bookBorrow.web;

import com.alibaba.fastjson.JSONObject;
import com.wuyou.base.bean.PaginationBean;
import com.wuyou.base.resp.Result;
import com.wuyou.bookBorrow.bean.BookBorrowBean;
import com.wuyou.bookBorrow.bean.BookBorrowPageBean;
import com.wuyou.bookBorrow.service.BookBorrowService;
import com.wuyou.bookStock.bean.BookStockBean;
import com.wuyou.bookStock.service.BookStockService;
import com.wuyou.config.utils.JwtTokenUtil;
import com.wuyou.user.bean.UserBean;
import com.wuyou.user.service.UserService;
import com.wuyou.utils.RedisUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * BookBorrowController,the web actions.
 *
 * @author Autocode
 * 2022-02-26 23:19:40
 */
@Api(tags = "借阅信息接口")
@RestController
@CrossOrigin
@RequestMapping("/bookBorrow")
public class BookBorrowController {

    private Logger logger = LoggerFactory.getLogger(BookBorrowController.class);

    @Autowired
    private BookBorrowService bookBorrowService;
    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Autowired
    private BookStockService bookStockService;
    @Autowired
    private RedisUtil redisUtil;
    @Autowired
    private UserService userService;

    @ApiOperation("增加借阅信息")
    @PostMapping(value = "/addBookBorrow", produces = {"application/json;charset=UTF-8"})
    public Object addBookBorrow(@RequestBody BookBorrowBean bookBorrow, HttpServletRequest request) {
        // 设置用户信息
        String token = request.getHeader("X-Token");
        String username = jwtTokenUtil.getUsernameFromToken(token);
        UserBean userBean = null;
        try {
            Object value = redisUtil.getValue(username);
            userBean = JSONObject.parseObject(value.toString(), UserBean.class);
            bookBorrow.setBorrow_user_id(userBean.getUser_id());
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (userBean == null) {
            userBean.setUser_account(username);
            userBean = userService.querySingleUser(userBean);
        }
        BookBorrowPageBean bookBorrowBean = new BookBorrowPageBean();
        bookBorrowBean.setBorrow_status(1);
        bookBorrowBean.setBorrow_user_id(userBean.getUser_id());
        List<BookBorrowBean> bookBorrowBeans = bookBorrowService.queryBookBorrowList(bookBorrowBean);
        if (bookBorrowBeans.size() >= 3) {
            return Result.error().message("每人最多同时借阅3本图书，请先归还！");
        }
        // 修改图书库存
        BookStockBean stockBean = new BookStockBean();
        stockBean.setStock_book_id(bookBorrow.getBorrow_book_id());
        BookStockBean bookStockBean = bookStockService.querySingleBookStock(stockBean);
        if (bookStockBean.getStock_num() > 0 && bookStockBean.getStock_occupable() > 0) {
            bookStockBean.setStock_occupable(bookStockBean.getStock_occupable() - 1);
            bookStockService.updateBookStock(bookStockBean);
        } else {
            return Result.error().message("当前图书库存不足，借阅失败");
        }
        return Result.ok().data("rows", bookBorrowService.addBookBorrow(bookBorrow));
    }

    @ApiOperation("根据ID删除借阅信息")
    @PostMapping(value = "/deleteBookBorrow", produces = {"application/json;charset=UTF-8"})
    public Object deleteBookBorrow(@RequestBody BookBorrowBean bookBorrow) {
        return Result.ok().data("rows", bookBorrowService.deleteBookBorrow(bookBorrow));
    }

    @ApiOperation("根据ID修改借阅信息")
    @PostMapping(value = "/updateBookBorrow", produces = {"application/json;charset=UTF-8"})
    public Object updateBookBorrow(@RequestBody BookBorrowBean bookBorrow) {
        // 修改图书库存
        BookStockBean stockBean = new BookStockBean();
        stockBean.setStock_book_id(bookBorrow.getBorrow_book_id());
        BookStockBean bookStockBean = bookStockService.querySingleBookStock(stockBean);
        bookStockBean.setStock_occupable(bookStockBean.getStock_occupable() + 1);
        bookStockService.updateBookStock(bookStockBean);
        bookBorrow.setBorrow_status(0);
        return Result.ok().data("rows", bookBorrowService.updateBookBorrow(bookBorrow));
    }

    /**
     * @PostMapping(value = "/queryBookBorrowCount",produces = {"application/json;charset=UTF-8"})
     * public Object queryBookBorrowCount(@RequestBody BookBorrowBean bookBorrow) {
     * return Result.ok().data("rows",bookBorrowService.queryBookBorrowCount(bookBorrow));
     * }
     */

    @ApiOperation("查询借阅信息列表")
    @PostMapping(value = "/queryBookBorrowList", produces = {"application/json;charset=UTF-8"})
    public Object queryBookBorrowList(@RequestBody BookBorrowPageBean bookBorrow) {
        List<BookBorrowBean> list = bookBorrowService.queryBookBorrowList(bookBorrow);
        PaginationBean page = bookBorrow.getPage();
        return Result.ok().data("dataList", list).data("page", page);
    }

    @ApiOperation("查询个人借阅信息列表")
    @PostMapping(value = "/queryBookBorrowListSelf", produces = {"application/json;charset=UTF-8"})
    public Object queryBookBorrowListSelf(@RequestBody BookBorrowPageBean bookBorrowPageBean, HttpServletRequest request) {
        String token = request.getHeader("X-Token");
        String username = jwtTokenUtil.getUsernameFromToken(token);
        Object value = redisUtil.getValue(username);
        UserBean userBean = null;
        try {
            userBean = JSONObject.parseObject(value.toString(), UserBean.class);
        } catch (Exception e) {
            e.printStackTrace();
            userBean.setUser_account(username);
            userBean = userService.querySingleUser(userBean);
            redisUtil.setValue(userBean.getUser_account(), userBean);
        }

        bookBorrowPageBean.setBorrow_user_id(userBean.getUser_id());
        List<BookBorrowBean> list = bookBorrowService.queryBookBorrowList(bookBorrowPageBean);
        PaginationBean page = bookBorrowPageBean.getPage();
        return Result.ok().data("dataList", list).data("page", page);
    }

    @ApiOperation("根据ID查询单个借阅信息")
    @PostMapping(value = "/querySingleBookBorrow", produces = {"application/json;charset=UTF-8"})
    public Object querySingleBookBorrow(@RequestBody BookBorrowBean bookBorrow) {
        return Result.ok().data("bookBorrow", bookBorrowService.querySingleBookBorrow(bookBorrow));
    }


}
