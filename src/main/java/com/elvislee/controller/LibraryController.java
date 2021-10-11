package com.elvislee.controller;

import com.elvislee.repository.LibraryRepository;
import com.elvislee.service.LibraryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@RestController
public class LibraryController {
    @Autowired
    LibraryRepository repository;

    @Autowired
    LibraryService libraryService;

    @PostMapping("/addBook")
    public ResponseEntity<AddResponse> addBookImplementation(@RequestBody Library library) {
        String id = libraryService.buildId(library.getIsbn(), library.getAisle());
        AddResponse addResponse = new AddResponse();

        // Check if the book is already in database
        if(libraryService.checkBookAlreadyExist(id)) {
            addResponse.setMsg("Book already exist");
            addResponse.setId(id);
            return new ResponseEntity<AddResponse>(addResponse, HttpStatus.ACCEPTED);
        }

        library.setId(id);

        repository.save(library);

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("unique", id);


        addResponse.setId(id);
        addResponse.setMsg("Book is added successfully");

        return new ResponseEntity<AddResponse>(addResponse, httpHeaders, HttpStatus.CREATED);
    }

    @GetMapping("/getBooks/{id}")
    public Library getBookById(@PathVariable(value="id") String id) {
        try {
            Library lib = repository.findById(id).get();
            return lib;
        } catch(Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/getBooks/author")
    public void getBooksByAuthorName(@RequestParam(value="authorname") String authorName) {

    }

}
