package com.wuyou;

//import com.paul.autotool.OneKeyGenerator;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.sql.SQLException;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = GraduationProjectApplication.class)
public class Creator {

	@Autowired
	JdbcTemplate jdbcTemplate ;

	@Test
	public void test() throws SQLException {
//		Connection conn = jdbcTemplate.getDataSource().getConnection();
//
//		System.out.println(conn.getCatalog()+"==========");
//		OneKeyGenerator.initDB(conn);
//
//
////		OneKeyGenerator.initDB(url, user, pwd);
//		String path = "com.wuyou";
//		String bizName = "fileInfo";
//		String tblName = "t_file_info";
//		OneKeyGenerator.todo(path,bizName,tblName);
//		System.out.println("=============");
	}
	
	
}
