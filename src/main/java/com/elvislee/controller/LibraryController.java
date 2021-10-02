package com.elvislee.controller;

import com.elvislee.repository.LibraryRepository;
import jdk.jfr.internal.Repository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LibraryController {
    @Autowired
    LibraryRepository repository;

    @PostMapping("/addBook")
    public void addBookImplementation(@RequestBody Library library) {
        library.setId(library.getIsbn() + library.getAisle());

        repository.save(library);
    }
}
