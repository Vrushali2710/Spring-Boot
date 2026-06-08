package com.example.demo.Controller;

import com.example.demo.model.Book;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

import static java.rmi.server.LogStream.log;


@Slf4j
@RestController
@RequestMapping("/books")
public class BookController {

    private List<Book> books = new ArrayList<>();

    public BookController(){
        books.add(new Book(1L,"1984","George Orwell"));
        books.add(new Book(2L,"To Kill a Mockingbird","Harper Lee"));

    }

    @GetMapping
    public List<Book> getAllBooks() {
        System.out.println("data found--------------------------------");
        return books;
    }
    @GetMapping("/{id}")
    public Book getBookById(@PathVariable("id") Long id){
        System.out.println("id-----------------"+id);
        return books.stream().filter(book -> book.getId().equals(id)).findFirst().orElse(null);
    }
    @PostMapping
    public Book createBook(Book book){
        book.setId((long) (books.size()+1));
        books.add(book);
        return book;
    }

    public Book updateBook(@PathVariable Long id,@RequestBody Book book){
        Book existingBook = books.stream().filter(b->b.getId().equals(id)).findFirst().orElse(null);
        if(existingBook != null) {
            existingBook.setTitle("eliza");
            existingBook.setAuthor("marks");
            return existingBook;
        }
        return null;
    }

}
