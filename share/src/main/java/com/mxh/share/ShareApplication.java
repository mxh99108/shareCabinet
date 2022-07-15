package com.mxh.share;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.mxh.share.mapper")
public class ShareApplication {

	public static void main(String[] args) {
		SpringApplication.run(ShareApplication.class, args);
		// push test
		System.out.println("启动成功");
	}

}
