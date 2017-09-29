package com.osiykm.flist;

import com.osiykm.flist.services.programs.BookParserProgram;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class FlistApplication {
	private static Class applicationClass = FlistApplication.class;
	public static void main(String[] args) {
		SpringApplication.run(FlistApplication.class, args);
	}
}
