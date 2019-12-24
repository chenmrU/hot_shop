package com.cmr.hotshop;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author cmr
 * @date 2019-12-24
 */
@SpringBootApplication
@MapperScan("com.cmr.hotshop.dao")
public class HotShopApplication {

    public static void main(String[] args) {
        SpringApplication.run(HotShopApplication.class, args);
    }

}
