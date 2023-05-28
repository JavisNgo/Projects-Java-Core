package com;

import linkedlist.LLProduct;

public class Main {

    public static void main(String[] args) {
        String[] options = {
            "Add new a Product", "Remove a Product",
            "Update price of a Product", "Update guaranty of a Product", "Print all Product",
            "Quit"
        };
        Menu menu = new Menu(options);
        LLProduct pList= new LLProduct();
        int choice;
        do {
            choice= menu.getUserChoice();
            switch(choice) {
                case 1: pList.add();
                break;
                case 2: pList.remove();
                break;
                case 3: pList.updatePrice();
                break;
                case 4: pList.updateGuaranty();
                break;
                case 5: pList.print();
                break;
            }
        } while (choice>0 && choice<6);
    }
}
