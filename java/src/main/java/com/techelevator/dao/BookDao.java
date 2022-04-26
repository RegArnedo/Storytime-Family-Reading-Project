package com.techelevator.dao;

import com.techelevator.model.*;

import java.util.List;

public interface BookDao {

    Book createBook(Book book, Long readerId);

    Book getBookById(int id);

    List <Book> getBooksByUserId(int id);

    List <Book> getAllBooks();

    boolean createLogEntry(CreateLogReadingDTO entry);

    List <LogReadingDTO> getUserBooks(long id);

    List <LogReadingDTO> getFamilyUserBooks(int id);

    List<Book> getBooksByFamilyId(String familyId);

    List<UserDetailDTO> getUserDetails(long detail_id);

    List <UserHistoryDTO> getReadingLog (long user_id);
}
