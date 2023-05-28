package com;

import linkedlist.LLEmpList;

public class Main {

    public static void main(String[] args) {
        String[] options = {
            "Add new an employee", "Remove an employee",
            "Increase salary of an employee", "Print all employees",
            "Quit"
        };
        Menu menu = new Menu(options);
        LLEmpList empList= new LLEmpList();
        int choice;
        do {
            choice= menu.getUserChoice();
            switch(choice) {
                case 1: empList.add();
                break;
                case 2: empList.remove();
                break;
                case 3: empList.increaseSalary();
                break;
                case 4: empList.print();
                break;
            }
        } while (choice>0 && choice<5);
    }
}
