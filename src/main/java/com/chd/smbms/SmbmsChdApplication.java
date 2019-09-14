package com.chd.smbms;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@MapperScan("com.chd.smbms.dao")
@EnableTransactionManagement
public class SmbmsChdApplication {

	public static void main(String[] args) {
		SpringApplication.run(SmbmsChdApplication.class, args);
	}

}
