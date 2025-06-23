package com.awbd.musicshop;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@SpringBootApplication
@EnableAspectJAutoProxy
public class MusicShopApplication {

	public static void main(String[] args) {
		SpringApplication.run(MusicShopApplication.class, args);
	}

}
