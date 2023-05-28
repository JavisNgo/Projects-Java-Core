
package ui;

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;
import tools.MyTools;

public class Menu extends Vector <String>{
    List<String> list = new ArrayList<String>();

    public Menu() {
        super();
    }
    
    public Menu(String[] items) {
        super();
        for (String item : items) this.add(item);
    }
    
    public int getChoice(String title) {
        int choice;
        System.out.println(title);
        for (int i=0; i<this.size();i++) {
            System.out.println(this.get(i));
        }
        choice = MyTools.getInt("Enter choice: ");
        return choice;
    }
    
     
    public String getChoice2(String title) {
//        System.out.println("\n---------------------------------------");
        System.out.println("");
        System.out.println("   " + title);
//        System.out.println("---------------------------------------");
        for (int i=0; i<list.size(); i++) {
            System.out.println("   4."+ (i+1) + "- " + list.get(i));   
        }
        System.out.print("   Choose 4.1 or 4.2: ");
//        Scanner sc = new Scanner(System.in);
        return MyTools.SC.nextLine().trim().toUpperCase();
    }
}
