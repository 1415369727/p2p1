package com.lishan.p2p;

import java.util.Properties;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableScheduling;

import com.github.pagehelper.PageHelper;

@SpringBootApplication
@EnableScheduling
@MapperScan("com.lishan.p2p.mapper")
public class App {
	public static void main(String[] args) {
		SpringApplication.run(App.class, args);
	}
	
	//配置mybatis的分页插件pageHelper
	      @Bean
	      public PageHelper pageHelper(){
	          PageHelper pageHelper = new PageHelper();
	          Properties properties = new Properties();
	          properties.setProperty("offsetAsPageNum","true");
	          properties.setProperty("rowBoundsWithCount","true");
	          properties.setProperty("reasonable","true");
	          properties.setProperty("dialect","mysql");    //配置mysql数据库的方言
	         pageHelper.setProperties(properties);
	         return pageHelper;
	     }
	
}
