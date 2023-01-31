package com.programacion.service;

import com.programacion.config.BooksMapper;
import com.programacion.db.Books;
import io.helidon.common.reactive.Multi;
import io.helidon.dbclient.DbClient;
import io.helidon.dbclient.DbRow;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.ExecutionException;
import java.util.stream.Collectors;

@ApplicationScoped
public class BooksServiceImpl implements BooksService {

    @Inject
    private DbClient dbClient;
    @Inject
    private BooksMapper booksMapper;

    @Override
    public List<Books> findAll() throws ExecutionException, InterruptedException {
        Multi<DbRow> execute = this.dbClient
                .execute(exe -> exe.createQuery("select a.id as author_id, a.first_name, a.last_name, b.id, b.title, b.isbn, b.price FROM books b JOIN authors a ON b.author_id = a.id").execute());
        return execute.collectList().get().stream().map(this.booksMapper::read).collect(Collectors.toList());
    }

    @Override
    public Books findById(int id) throws ExecutionException, InterruptedException {
        Optional<DbRow> dbRow = this.dbClient
                .execute(exe -> exe.createGet("select a.id as author_id, a.first_name, a.last_name, b.id, b.title, b.isbn, b.price FROM books b JOIN authors a ON b.id = a.id WHERE b.id = :id").addParam("id", id).execute()).get();

        return dbRow.isPresent() ? this.booksMapper.read(dbRow.get()) : new Books();
    }

    @Override
    public void create(Books bookss) {
        try {
            this.dbClient
                    .execute(exec -> exec
                            .insert("insert into books (isbn, title,author_id, price) VALUES(?, ?, ?, ?)",
                                     bookss.getIsbn(), bookss.getTitle(),bookss.getAuthor().getId(), bookss.getPrice()
                            )).get();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }



    @Override
    public void delete(int id) {

        try {
          this.dbClient
                    .execute(exec -> exec
                            .delete("delete from books where id = ?",
                                    id
                            )).get();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }


    @Override
    public void update(Books books) {

        try {
            this.dbClient
                    .execute(exec -> exec
                            .update("update books set  isbn = ?, title = ?,author_id = ?, price = ? WHERE id = ?",
                                     books.getIsbn(), books.getTitle(),books.getAuthor().getId(), books.getPrice(), books.getId()
                            )).get();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
