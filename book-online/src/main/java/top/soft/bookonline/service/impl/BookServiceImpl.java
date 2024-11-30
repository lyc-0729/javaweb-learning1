package top.soft.bookonline.service.impl;
import top.soft.bookonline.dao.BookDao;
import top.soft.bookonline.dao.impl.BookDaoImpl;
import top.soft.bookonline.entity.Book;
import top.soft.bookonline.service.BookService;
import java.util.List;

/**
 * @author BookServiceImpl
 * @description: TODO
 * @date 2024/10/26 14:27
 */
public class BookServiceImpl implements BookService {
    private final BookDao bookDao=new BookDaoImpl();
    @Override
    public List<Book> getBooks(){
        return bookDao.selectAll();
    }
    @Override
    public Book getBookById(int id){
        return bookDao.getBookById(id);
    }
}
