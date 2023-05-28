/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import model.Books;
import java.util.ArrayList;
import java.util.List;
import tool.MyTool;
import static tool.MyTool.sc;

public class BookDaoImpl implements BookDao {

    private List<Books> books;
    String filename = "book.txt";

    public BookDaoImpl() {
        books = new ArrayList<>();
    }
    
    @Override
    public List<Books> getNewBooks() {
        if(books.isEmpty()) {
            System.out.println("Empty List.");
        } 
        return books;
    }
    
    @Override
    public List<Books> getAllBooks() {
        books = MyTool.loadFromFile(filename);
        return books;
    }

    @Override
    public Books seachBookByIsbn(int isbn) {
        for (Books book : books) 
            if (book.getIsbn()==isbn) return book;
        return null;
    }
    
    @Override
    public boolean seachBookByName(String bookname) {
        for (Books book : books) 
            if (book.getBookName().equalsIgnoreCase(bookname)) return true;
        return false;
    }
    
    @Override
    public Books getBookByIsbn(int isbn) {
        books = MyTool.loadFromFile(filename);
        for (Books book : books) 
            if (book.getIsbn()==isbn) return book;
        return null;
    }

    @Override
    public List<Books> getBookByName(String bookname) {
        books = MyTool.loadFromFile(filename);
        List<Books> list = new ArrayList<>();
        for (Books book : books) 
            if (book.getBookName().contains(bookname)) list.add(book);
        return list;     
    }
    
    @Override
    public void createBook() {
        int isbn; 
        String bookname; 
        boolean checkname = true;
        System.out.println("Enter New Book Detail: ");
        System.out.print("   - isbn: ");
        isbn = Integer.parseInt(sc.nextLine());
        do {  
            System.out.print("   - book name: ");
            bookname = MyTool.sc.nextLine().trim().toUpperCase();
            if (seachBookByName(bookname)) {
                System.out.println("   Book name is duplicate!");
            } else {
                checkname = false;
            }                                       
        } while (checkname);
        books.add(new Books(isbn,bookname));
        System.out.println("New book has been created.");
    }

    @Override
    public void updateBook(Books book) {
        String bookname;   
        boolean checkname = true;        
        do {  
            System.out.print("   Enter name of book: ");
            bookname = MyTool.sc.nextLine().trim().toUpperCase();
            if (seachBookByName(bookname)) {
                System.out.println("   Book name is duplicate!");
            } else {
                book.setBookName(bookname);
                System.out.println("   " + book.getIsbn() + " is updated!");
                checkname = false;
            }                                       
        } while (checkname);
    }
    
    @Override
    public void deleteBook(Books book) {
        books.remove(book);
        System.out.println("   " + book.getIsbn() + " is deleted!");   
    }
    
    @Override
    public void saveFile() {
        MyTool.saveToFile(books);
        System.out.println("New book(s) has been saved to the file.");
    }
    
}
