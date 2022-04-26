package com.techelevator.controller;


import com.techelevator.dao.BookDao;
import com.techelevator.dao.UserDao;
import com.techelevator.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * REST API
 * Routes
 *  - GET /book/{id} - returns single book
 *  - POST /book - create new book in db
 */

@PreAuthorize("isAuthenticated()")
@RestController
@CrossOrigin
public class BookController {

    @Autowired
    private BookDao bookDao;
    @Autowired
    private UserDao userDao;

    public BookController(BookDao bookDao){
        this.bookDao = bookDao;
    }

    //@GetMapping("/book/{id}")
    @RequestMapping(path="/book/{id}", method= RequestMethod.GET)
    public Book getBook(@PathVariable int id) {
        return bookDao.getBookById(id);

        /*
        try {
            User user = userDao.findByUsername(newUser.getUsername());
        } catch (UsernameNotFoundException e) {
            System.out.println("failed to return book");
        }
         */
    }

    @ResponseStatus(HttpStatus.CREATED)
    @RequestMapping(path="/bookshelf/{id}", method = RequestMethod.POST)
    public Book createBook(@RequestBody Book book, @PathVariable String id){
        long readerId = Long.parseLong(id);
        return bookDao.createBook(book, readerId);
    }

    @RequestMapping(value = "/bookshelf/{id}", method = RequestMethod.GET)
    public List<Book> getBooksByUser(@PathVariable int id) {
        return bookDao.getBooksByUserId(id);
    }

    @RequestMapping(value="/bookshelf", method = RequestMethod.GET)
    public List<Book> getAllBooks() {
        return bookDao.getAllBooks();
    }

    @ResponseStatus(HttpStatus.CREATED)
    @RequestMapping(value="/readinglog", method = RequestMethod.POST)
    public boolean createLogEntry(@RequestBody CreateLogReadingDTO entry) {
        return bookDao.createLogEntry(entry);
    }

    @RequestMapping(value="/readinglog", method = RequestMethod.GET)
    public List<LogReadingDTO> getUserBook(@RequestBody UserIdDTO userIdDto){
        long readerId = userIdDto.getUser_id();
        return bookDao.getUserBooks(readerId);
    }

    @RequestMapping(value="/readinglog/family/{id}", method = RequestMethod.GET)
    public List<LogReadingDTO> getFamilyUserBook(@PathVariable int id){
        return bookDao.getFamilyUserBooks(id);
    }

    @RequestMapping(value = "/readinglog/books/{id}", method = RequestMethod.GET)
    public List<Book> getBooksByFamily(@PathVariable String id) {
        return bookDao.getBooksByFamilyId(id);
    }

    @RequestMapping(value = "/user/dashboard/detail/{id}", method = RequestMethod.GET)
    public List<UserDetailDTO> getUserDetail(@PathVariable String id) {
        long detail_id = Long.parseLong(id);
        return bookDao.getUserDetails(detail_id);
    }

    @RequestMapping(value = "/readinglog/{id}", method = RequestMethod.GET)
    public List <UserHistoryDTO> getReadingLog(@PathVariable String id) {
        long detail_id = Long.parseLong(id);
        return bookDao.getReadingLog(detail_id);
    }
}
