    /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import data.ProductList;
import ui.Menu;

/**
 *
 * @author trien
 */
public class Main {
    public static void main(String[] args) {
        ProductList  pList = new ProductList();
        pList.readDataFromFile();
        Menu menu = new Menu("Product Management.");
        menu.addNewOption("         1-Print all lists new product.");
        menu.addNewOption("         2-Create a Product.");
        menu.addNewOption("         3-Check to exist Product.");
        menu.addNewOption("         4-Search Product information by name.");
        menu.addNewOption("         5-Update Product.");
        menu.addNewOption("         6-Save to file.");
        menu.addNewOption("         7-Print all lists from file");
        menu.addNewOption("         8-Exit..");
        int choice;
        do{
            menu.printMenu();
            choice = menu.getChoice();
            switch(choice){
                case 1:
                    pList.printAllListNewProduct();
                    break;
                case 2:
                    pList.createAProduct();
                    break;
                case 3:
                    pList.checkExitProduct();
                    break;
                case 4:
                    pList.searchProduct();
                    break;
                case 5:
                    pList.updateProduct();
                    break;
                case 6:
                    pList.saveToFile();
                    break;
                case 7:
                    pList.printAllProductFromFile();
                    break;
                case 8:
                    System.out.println("Bye,bye.See you next time.");
                    break;
            }
        }while(choice != 8);
    }
}
