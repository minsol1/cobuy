package com.cobuy.BE;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
//@MapperScan(value = {"com.cobuy.BE.board.model.mapper", "com.cobuy.BE.event.model.mapper"})
@MapperScan(value = {"com.cobuy.BE.*.**.mapper"})
public class BeApplication {

	public static void main(String[] args) {
		SpringApplication.run(BeApplication.class, args);
	}


}
