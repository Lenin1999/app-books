package com.programacion.config;

import com.programacion.db.Authors;
import com.programacion.db.Books;
import io.helidon.dbclient.DbMapper;
import io.helidon.dbclient.DbRow;
import jakarta.enterprise.context.ApplicationScoped;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@ApplicationScoped
public class BooksMapper implements DbMapper<Books> {

//    var map = new HashMap<String>;
//        map.put("id", value.getId());
//        map.put("isbn", value.getIsbn());
//        map.put("title", value.getTitle());
//        map.put("price", value.getPrice());


    @Override
    public Map<String, ?> toNamedParameters(Books value) {
        var map = new HashMap<String, Object>();
        map.put("id", value.getId());
        map.put("isbn", value.getIsbn());
        map.put("title", value.getTitle());
        map.put("price", value.getPrice());

        var author = value.getAuthor();
        map.put("authorId", author.getId());
        map.put("firstName", author.getFirstName());
        map.put("lastName", author.getLastName());

        return map;
    }
    @Override
    public Books read(DbRow row) {
        var id = row.column("id");
        var isbn = row.column("isbn");
        var title = row.column("title");
        var price = row.column("price");

        var authorId = row.column("author_id");
        var firstName = row.column("first_name");
        var lastName = row.column("last_name");


        return new Books(
                id.as(Integer.class),
                new Authors(
                        authorId.as(Integer.class),
                        firstName.as(String.class),
                        lastName.as(String.class)
                ),
                isbn.as(String.class),
                title.as(String.class),
                price.as(BigDecimal.class)
        );
    }

//    public List<?> toIndexedParameters(Books value) {
//        ArrayList<Object> list = new ArrayList<>();
//
//    }


    @Override
    public List<?> toIndexedParameters(Books value) {
        ArrayList<Object> list = new ArrayList<>();
        list.add(value.getId());
        list.add(value.getIsbn());
        list.add(value.getTitle());
        list.add(value.getPrice());

        var author = value.getAuthor();
        list.add(author.getId());
        list.add(author.getFirstName());
        list.add(author.getLastName());

        return list;
    }
}
