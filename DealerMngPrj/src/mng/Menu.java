package mng;

import java.util.ArrayList;
import tools.MyTool;

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

    public int getChoice(String title) {
        boolean check = false;
        int choice = 0;
        System.out.println(title);
        for (int i = 0; i < this.size(); i++) {
            System.out.println(this.get(i));
        }
        do {
            try {
                System.out.println("Enter choice: ");
                choice = Integer.parseInt(MyTool.SC.nextLine());
                if (choice > this.size()+1) {
                    System.out.println("Enter smaller!!!");
                    check = true;
                }
            } catch (Exception e) {
                System.out.println("Input number!!!");
                check = true;
            }
        } while (check);
        return choice;
    }
}
