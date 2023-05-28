
package dao;

import model.Books;
import java.util.List;

public interface BookDao {    
    List<Books> getNewBooks();
    List<Books> getAllBooks();
    Books seachBookByIsbn(int isbn);
    boolean seachBookByName(String bookname);
    Books getBookByIsbn(int isbn);
    List<Books> getBookByName(String bookname);
    void createBook();
    void updateBook(Books book);
    void deleteBook(Books book);
    void saveFile();
    
}
