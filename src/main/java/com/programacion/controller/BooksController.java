package com.programacion.controller;

import com.programacion.db.Books;
import com.programacion.service.BooksService;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;

import java.util.List;
import java.util.concurrent.ExecutionException;

@ApplicationScoped
@Path("/books")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class BooksController {
    @Inject
    private BooksService booksService;

    @GET
    @Path("/{id}")
    public Books findById(@PathParam("id") int id) throws ExecutionException, InterruptedException {
        return this.booksService.findById(id);
    }

    @GET
    public List<Books> findAll() throws ExecutionException, InterruptedException {
        return this.booksService.findAll();
    }

    @POST
    public void create(Books book) {
        booksService.create(book);
    }



    @DELETE
    @Path("/{id}")
    public void delete(@PathParam("id") int id) {
        booksService.delete(id);
    }

    @PUT
    @Path("/{id}")
    public void update(@PathParam("id") int id, Books book ) {
       booksService.update(book);
    }
}
