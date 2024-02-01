package com.unir.products.service;

import com.unir.products.data.BookRepository;
import com.unir.products.model.pojo.Book;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@Slf4j
public class ProductsServiceImpl {

    @Autowired
    private BookRepository repository;

    public List<Book> getProducts(String author, String title, String isbn, Short age, String synapsis, Short stock){

        if(StringUtils.hasLength(author) || StringUtils.hasLength(title) || StringUtils.hasLength(isbn)
                || age != null || StringUtils.hasLength(synapsis) || stock != null ){
            return repository.search(author,title,isbn,age,synapsis,stock);
        }
        List<Book> books = repository.getBooks();
        return books.isEmpty() ? null : books;
    }

}
