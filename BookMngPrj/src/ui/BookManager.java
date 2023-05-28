/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ui;

import dao.BookDao;
import dao.BookDaoImpl;
import java.util.ArrayList;
import java.util.List;
import model.Books;
import tool.MyTool;

public class BookManager {
    public static void main(String[] args) {           
        String[] options = { "Print the list", "Create a Book", 
                            "Check existing Book", 
                            "Search Book information by name", 
                            "Update Book", "Save Books to file",
                            "Print list Books from the file", 
                            "Quit"
                            };
        Menu mnu = new Menu(options);
        int choice=0, inputisbn ;
        String response, inputname;
        Books book; 
        boolean changed = false;
        BookDao bookDao = new BookDaoImpl();      
        do {
            String choice2 = null;
            boolean quit = false;
            choice = mnu.getChoice("BOOK MANAGER");            
            System.out.println("---------------------------------------");
            switch(choice) {
                case 1: for (Books books : bookDao.getNewBooks())                           
                                System.out.println(books.getIsbn() + ", "  
                                + books.getBookName());                          
                        break;
                case 2: bookDao.createBook();
                        changed = true; break;
                case 3: System.out.print("Enter isbn of the book: ");
                        inputisbn = Integer.parseInt(MyTool.sc.nextLine().trim());
                        book = new BookDaoImpl().getBookByIsbn(inputisbn);
                        if (book != null) {
                            System.out.println("Exist Book!");
                        } else {
                            System.out.println("No Book Found!");
                        }                           
                        break;
                case 4: System.out.print("Enter name of the book: ");
                        inputname = MyTool.sc.nextLine().trim().toUpperCase();
                        List<Books> list = new ArrayList<>();
                        list = new BookDaoImpl().getBookByName(inputname);
                        if (list != null) {
                            for (Books books : list) {
                                System.out.println(books.getIsbn() + ", "  
                                + books.getBookName());
                            }                           
                        } else {
                            System.out.println("No Book Found!");
                        }    
                        break;
                case 5: do {                        
                        mnu.list.clear();
                        mnu.list.add("Update a book");
                        mnu.list.add("Delete a book");
                        choice2 = mnu.getChoice2("Update books").trim().toUpperCase();
                        switch(choice2) {
                            case "4.1": 
                                //System.out.print("   Enter isbn of the book: ");
                                
                                inputisbn = MyTool.getInt("   Enter isbn of the book: ");//Integer.parseInt(MyTool.sc.nextLine().trim()); 
                                
                                book = bookDao.seachBookByIsbn(inputisbn);
                                if (book != null) {
                                    bookDao.updateBook(book); 
                                } else {
                                    System.out.println("   No Book Found!");
                                }    
                                System.out.print("   You continue Y/N? ");
                                response = MyTool.sc.nextLine().toUpperCase();
                                if(response.startsWith("N"))
                                    quit = true;
                                changed = true; break;    
                            case "4.2": 
                                System.out.print("   Enter isbn of the book: ");
                                inputisbn = Integer.parseInt(MyTool.sc.nextLine().trim());  
                                book = bookDao.seachBookByIsbn(inputisbn);
                                if (book != null) {
                                    bookDao.deleteBook(book); 
                                } else {
                                    System.out.println("   No Book Found!");
                                }    
                                System.out.print("   You continue Y/N? ");
                                response = MyTool.sc.nextLine().toUpperCase();
                                if(response.startsWith("N"))
                                    quit = true;
                                changed = true; break;
                            default:
                                quit = true;
                        }   
                    } while (!quit); 
                    break;
                case 6: bookDao.saveFile(); 
                        changed = false; break;
                case 7:
                        for (Books books : new BookDaoImpl().getAllBooks())
                            System.out.println(books.getIsbn() + ", "  
                                + books.getBookName());
                        break;
                default: if(changed) {
                    System.out.print("Save changes Y/N? ");
                    response = MyTool.sc.nextLine().toUpperCase();
                    if(response.startsWith("Y"))
                        bookDao.saveFile();
                }
            }
        }
        while (choice>0 && choice <8);
    } 
}
