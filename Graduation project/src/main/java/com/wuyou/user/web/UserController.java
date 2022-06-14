package com.wuyou.user.web;

import com.alibaba.druid.util.StringUtils;
import com.wuyou.base.bean.PaginationBean;
import com.wuyou.base.resp.Result;
import com.wuyou.user.bean.UserBean;
import com.wuyou.user.bean.UserPageBean;
import com.wuyou.user.service.UserService;
import com.wuyou.utils.UploadUtil;
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
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * UserController,the web actions.
 *
 * @author Autocode
 * 2022-02-26 23:22:43
 */
@Api(tags = "用户接口")
@RestController
@RequestMapping("/user")
@CrossOrigin    // 解决跨域
public class UserController {


    @Autowired
    private UserService userService;
    @Autowired
    private UploadUtil uploadUtil;


    @ApiOperation("增加用户")
    @PostMapping(value = "/addUser", produces = {"application/json;charset=UTF-8"})
    public Object addUser(@RequestBody UserBean user) {
        user.setUser_card_no(user.getUser_account());
        return Result.ok().data("rows", userService.addUser(user));
    }

    @ApiOperation("增加用户")
    @PostMapping(value = "/addUserList", produces = {"application/json;charset=UTF-8"})
    public Object addUserList(@RequestBody List<UserBean> user) {
        AtomicInteger i = new AtomicInteger();
        if (user != null && user.size() > 0) {
            user.forEach(data -> {
                int i1 = userService.addUser(data);
                if (i1 > 0) {
                    i.getAndIncrement();
                }
            });
        }
        return Result.ok().data("rows", i);
    }

    @ApiOperation("根据ID删除用户")
    @PostMapping(value = "/deleteUser", produces = {"application/json;charset=UTF-8"})
    public Object deleteUser(@RequestBody UserBean user) {
        return Result.ok().data("rows", userService.deleteUser(user));
    }

    @ApiOperation("根据ID删除用户")
    @PostMapping(value = "/deleteUserList", produces = {"application/json;charset=UTF-8"})
    public Object deleteUserList(@RequestBody List<Integer> delUserById) {
        if (delUserById != null && delUserById.size() > 0) {
            delUserById.forEach(data -> {
                UserBean userBean = new UserBean();
                userBean.setUser_id(data);
                userService.deleteUser(userBean);
            });
        return Result.ok().message("删除成功！");
        }
        return Result.error().message("删除失败");
    }

    @ApiOperation("根据ID修改用户")
    @PostMapping(value = "/updateUser", produces = {"application/json;charset=UTF-8"})
    public Object updateUser(@RequestBody UserBean user) {
        return Result.ok().data("rows", userService.updateUser(user));
    }

    /**
     * @PostMapping(value = "/queryUserCount",produces = {"application/json;charset=UTF-8"})
     * public Object queryUserCount(@RequestBody UserBean user) {
     * return Result.ok().data("rows",userService.queryUserCount(user));
     * }
     */

    @ApiOperation("查询用户列表")
    @PostMapping(value = "/queryUserList", produces = {"application/json;charset=UTF-8"})
    public Object queryUserList(@RequestBody UserPageBean user) {
        List<UserBean> list = userService.queryUserList(user);
        PaginationBean page = user.getPage();
        return Result.ok().data("dataList", list).data("page", page);
    }

    @ApiOperation("根据ID查询单个用户")
    @PostMapping(value = "/querySingleUser", produces = {"application/json;charset=UTF-8"})
    public Object querySingleUser(@RequestBody UserBean user) {
        return Result.ok().data("user", userService.querySingleUser(user));
    }

    @PostMapping(value = "login")
    public Object testCorsFilter() {
        System.out.println("abc");
        return Result.ok().data("data", "abc");
    }

    @PostMapping(value = "importExcel")
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
                    String card_no = cell.getStringCellValue();
//                    String card_no = row.getCell(0).getStringCellValue();
                    XSSFCell cell1 = row.getCell(1);
                    cell1.setCellType(Cell.CELL_TYPE_STRING);
                    String stu_name = cell1.getStringCellValue();
                    XSSFCell cell2 = row.getCell(2);
                    cell2.setCellType(Cell.CELL_TYPE_STRING);
                    String stu_sex =  cell2.getStringCellValue();
                    if (StringUtils.isEmpty(card_no) || StringUtils.isEmpty(stu_name)) {
                        continue;
                    }
                    if(stu_sex == "男" || "男".equals(stu_sex)){
                        addUserList(card_no, stu_name, 0 + "");
                    }else {
                        addUserList(card_no, stu_name, 1 + "");
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
                    String card_no = cell.getStringCellValue();
//                    String card_no = row.getCell(0).getStringCellValue();
                    HSSFCell cell1 = row.getCell(1);
                    cell1.setCellType(Cell.CELL_TYPE_STRING);
                    String stu_name = cell1.getStringCellValue();
                    HSSFCell cell2 = row.getCell(2);
                    cell2.setCellType(Cell.CELL_TYPE_STRING);
                    String stu_sex =  cell2.getStringCellValue();
                    if (StringUtils.isEmpty(card_no) || StringUtils.isEmpty(stu_name)) {
                        continue;
                    }
                    if(stu_sex == "男" || "男".equals(stu_sex)){
                        addUserList(card_no, stu_name, 0 + "");
                    }else {
                        addUserList(card_no, stu_name, 1 + "");
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
                return Result.error().message("导入失败");
            }
        }
        return Result.error().message("请传入excel表格");
    }


    private void addUserList(String card_no, String stu_name, String sex ){
        UserBean userBean = new UserBean();
        userBean.setUser_account(card_no);
        UserBean userBean1 = userService.querySingleUser(userBean);
        if (userBean1 != null) {
            return;
        }
        userBean.setUser_card_no(card_no);
        userBean.setUser_name(stu_name);
        userBean.setUser_pwd("123456");
        userBean.setUser_sex(sex);
        userBean.setUser_role_code("001002");
        userBean.setUser_type(0);
        userBean.setUser_img_name("/2.jpg");
        userService.addUser(userBean);
    }
}
