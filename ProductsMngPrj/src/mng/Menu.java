
package mng;

import java.util.ArrayList;
import tools.MyTools;

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
        int choice = 0;
        boolean check = true;
        for (int i=0;i<this.size();i++) {
            System.out.println(this.get(i));
        }
        choice = MyTools.getInt("Enter choice: ", 1, 10);
        return choice;
    }
}
