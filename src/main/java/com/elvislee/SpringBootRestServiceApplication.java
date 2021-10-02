package com.elvislee;

import com.elvislee.controller.Library;
import com.elvislee.repository.LibraryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.List;

@SpringBootApplication
public class SpringBootRestServiceApplication {

	@Autowired
	LibraryRepository repository;

	public static void main(String[] args) {
		SpringApplication.run(SpringBootRestServiceApplication.class, args);
	}

/*
	@Override
	public void run(String[] args) throws Exception{
		Library lib = repository.findById("fdsefr343").get();
		System.out.println(lib.getAuthor());

		Library libEntity = new Library();
		libEntity.setAisle(123);
		libEntity.setAuthor("Elvis Lee");
		libEntity.setBook_name("Devops");
		libEntity.setIsbn("dsf45345lds");
		libEntity.setId("abcde1234");

		// repository.save(libEntity);

		List<Library> allRecords = repository.findAll();
		for(Library item : allRecords) {
			System.out.println(item.getAuthor());
		}

		// Delete an item that matches entity
		//repository.delete(libEntity);

		// repository.deleteById("abcde1234");
	}
	*/
}
