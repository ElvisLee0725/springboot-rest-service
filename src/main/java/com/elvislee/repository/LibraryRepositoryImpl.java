package com.elvislee.repository;

import com.elvislee.controller.Library;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

public class LibraryRepositoryImpl implements LibraryRepositoryCustom{
    @Autowired
    LibraryRepository repository;

    @Override
    public List<Library> findAllByAuthor(String author) {
        List<Library> books = repository.findAll();
        List<Library> result = new ArrayList<>();
        for(Library book : books) {
            if(book.getAuthor().equalsIgnoreCase(author)) {
                result.add(book);
            }
        }
        return result;
    }
}
