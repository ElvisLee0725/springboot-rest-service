package com.elvislee.repository;

import com.elvislee.controller.Library;

import java.util.List;

public interface LibraryRepositoryCustom {
    List<Library> findAllByAuthor(String author);
}
