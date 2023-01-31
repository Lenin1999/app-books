package com.programacion.config;

import com.programacion.db.Books;
import io.helidon.dbclient.DbMapper;
import io.helidon.dbclient.spi.DbMapperProvider;

import java.util.Optional;

public class BooksMapperProvider implements DbMapperProvider {

    public static final BooksMapper MAPPER = new BooksMapper();


//    public <T> Optional<DbMapper<T>> mapper(Class<T> ) {
//        return type.equals(Books.class) ? Optional.of((DbMapper<T>) MAPPER).empty();
//    }


    @Override
    @SuppressWarnings("unchecked")
    public <T> Optional<DbMapper<T>> mapper(Class<T> type) {
        return type.equals(Books.class) ? Optional.of((DbMapper<T>) MAPPER) : Optional.empty();
    }
}
