package com.xrb;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan(basePackages="com.xrb.dao")
public class TdSpaceApplication {

	public static void main(String[] args) {
		SpringApplication.run(TdSpaceApplication.class, args);
	}

}
