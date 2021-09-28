package com.elvislee;

import com.elvislee.controller.Library;
import com.elvislee.repository.LibraryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SpringBootRestServiceApplication implements CommandLineRunner {

	@Autowired
	LibraryRepository repository;

	public static void main(String[] args) {
		SpringApplication.run(SpringBootRestServiceApplication.class, args);


	}


	@Override
	public void run(String[] args) throws Exception{
		Library lib = repository.findById("fdsefr343").get();
//		System.out.println(lib.getAuthor());
	}
}
