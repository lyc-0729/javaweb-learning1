package top.soft.bookonline.dao;

import top.soft.bookonline.entity.Book;

import java.util.List;

public interface BookDao {

    List<Book> selectAll();

    Book getBookById(int id);

}
