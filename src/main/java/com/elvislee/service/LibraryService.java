package com.elvislee.service;

import com.elvislee.controller.Library;
import com.elvislee.repository.LibraryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class LibraryService {
    @Autowired
    LibraryRepository repository;

    public String buildId(String isbn, int aisle) {
        return isbn + aisle;
    }

    public boolean checkBookAlreadyExist(String id) {
        Optional<Library> lib = repository.findById(id);
        return lib.isPresent();
    }

}
