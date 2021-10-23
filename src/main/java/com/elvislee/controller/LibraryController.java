package com.elvislee.controller;

import com.elvislee.repository.LibraryRepository;
import com.elvislee.service.LibraryService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
public class LibraryController {
    @Autowired
    LibraryRepository repository;

    @Autowired
    LibraryService libraryService;

    // Implement logger in LibraryController
    private static final Logger logger = LoggerFactory.getLogger(LibraryController.class);

    @PostMapping("/addBook")
    public ResponseEntity<AddResponse> addBookImplementation(@RequestBody Library library) {
        String id = libraryService.buildId(library.getIsbn(), library.getAisle());
        AddResponse addResponse = new AddResponse();

        // Check if the book is already in database
        if(libraryService.checkBookAlreadyExist(id)) {
            logger.info("Book already exist, skipping creation");

            addResponse.setMsg("Book already exist");
            addResponse.setId(id);
            return new ResponseEntity<AddResponse>(addResponse, HttpStatus.ACCEPTED);
        }

        logger.info("Book does not exist yet, creating one");

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
    public List<Library> getBooksByAuthorName(@RequestParam(value="authorname") String authorName) {
        return repository.findAllByAuthor(authorName);
    }

    @PutMapping("/updateBook/{id}")
    public ResponseEntity<Library> updateBook(@PathVariable(value="id") String id,
                                              @RequestBody Library library) {
        Library curBook = repository.findById(id).get();

        curBook.setAisle(library.getAisle());
        curBook.setAuthor(library.getAuthor());
        curBook.setBook_name(library.getBook_name());

        repository.save(curBook);

        return new ResponseEntity<Library>(curBook, HttpStatus.OK);
    }

    @DeleteMapping("/deleteBook/{id}")
    public ResponseEntity<String> deleteBookById(@PathVariable(value="id") String id) {
        Library libToDelete = repository.findById(id).get();
        repository.delete(libToDelete);

        return new ResponseEntity<>("Book is deleted", HttpStatus.CREATED);
    }

    @GetMapping("/getAllBooks")
    public List<Library> getAllBooks() {
        List<Library> allBooks = repository.findAll();
        return allBooks;
    }
}
