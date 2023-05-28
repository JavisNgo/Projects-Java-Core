
package com;
import java.util.ArrayList;
import java.util.Scanner;

public class Menu extends ArrayList<String> {

    public Menu() {
        super();
    }
    
    public Menu(String[] items) {
        super();
        for (String item : items) {
            this.add(item);
        }
    }
    
    public int getUserChoice() {
        int choice=0, i;
        System.out.println();
        for (i=0;i<this.size();i++) {
            System.out.println((i+1)+"-"+this.get(i));
        }
        System.out.println("Select an option :1.."+i+":");
        Scanner sc= new Scanner(System.in);
        choice = Integer.parseInt(sc.nextLine());
        return choice;
    }
}
