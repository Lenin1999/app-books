package com.programacion.service;

import com.programacion.db.Books;

import java.util.List;
import java.util.concurrent.ExecutionException;

public interface BooksService {
    List<Books> findAll() throws ExecutionException, InterruptedException;
    Books findById(int id) throws ExecutionException, InterruptedException;
    void create(Books book);
    void update(Books book);
    void delete(int id);
}