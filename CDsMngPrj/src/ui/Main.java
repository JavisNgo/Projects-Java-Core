package ui;

import dao.CDListAoc;
import dao.CDListDao;
import java.util.ArrayList;
import java.util.List;
import model.CD;
import tools.MyTools;

public class Main {

    public static void main(String[] args) {
        String[] options = {
            "1. Add CD to the catalog", "2. Search CD by CD title", "3. Display the catalog", "4. Update CD",
            "5. Save to file", "6. Print CD from file"
        };

        Menu menu = new Menu(options);
        int choice;
        String back, inputtitle, inputid;
        boolean changed = false;
        CD cd;
        CDListDao cdDao = new CDListAoc();
        do {
            String choice2 = null;
            boolean flag = false;
            choice = menu.getChoice("\nCD MANAGER");
            switch (choice) {
                case 1:
                    cdDao.createCD();
                    changed = true;
                    break;
                case 2:
                    System.out.println("Enter title of the CD: ");
                    inputtitle = MyTools.SC.nextLine();
                    List<CD> list = new ArrayList<>();
                    list = new CDListAoc().getCDByTitle(inputtitle);
                    if (list != null) {
                        for (CD x : list) {
                            System.out.println(x.getID() + ", " + x.getName() + ", " + x.getType() + ", " + x.getTitle() + ", " + x.getUnitprice() + ", " + x.getPublishingyear());
                        }
                    } else {
                        System.out.println("No CD found !");
                    }
                    break;
                case 3:
                    for (CD x : cdDao.getNewCD()) {
                        System.out.println(x.getID() + ", " + x.getName() + ", " + x.getType() + ", " + x.getTitle() + ", " + x.getUnitprice() + ", " + x.getPublishingyear());
                    }
                    break;
                case 4:
                    do {
                        menu.list.clear();
                        menu.list.add("Update a CD");
                        menu.list.add("Delete a CD");
                        choice2 = menu.getChoice2("Update CDs");
                        switch (choice2) {
                            case "4.1":
                                inputid = MyTools.getString("Enter ID: ", "Not blank");
                                cd = cdDao.searchCDByID(inputid);
                                if (cd != null) {
                                    cdDao.update(cd);
                                } else {
                                    System.out.println(" No VC found");
                                }
                                System.out.println("  You continue Y/N ?");
                                back = MyTools.SC.nextLine().toUpperCase();
                                if (back.startsWith("N")) {
                                    flag = true;
                                }
                                changed = true;
                                break;
                            case "4.2":
                                System.out.println("Enter id of the CS: ");
                                inputid = MyTools.getString("Enter String: ", "Not blank");
                                cd = cdDao.searchCDByID(inputid);
                                if (cd != null) {
                                    cdDao.delete(cd);
                                } else {
                                    System.out.println(" No CD found");
                                }
                                System.out.println(" You continue Y/N ?");
                                back = MyTools.SC.nextLine().toUpperCase();
                                if (back.startsWith("N")) {
                                    flag = true;
                                }
                                changed = true;
                                break;
                            default:
                                flag = true;
                        }
                    } while (!flag);
                    break;
                case 5:
                    cdDao.saveFile();
                    flag = false;
                    break;
                case 6:
                    for (CD x : new CDListAoc().getAllCD()) {
                        System.out.println(x.getID() + ", " + x.getName() + ", " + x.getType() + ", " + x.getTitle() + ", " + x.getUnitprice() + ", " + x.getPublishingyear());
                    }
                    break;
                default:
                    if (changed) {
                        System.out.println("Save changes Y/N ?");
                        back = MyTools.SC.nextLine().toUpperCase();
                        if (back.startsWith("Y")) {
                            cdDao.saveFile();
                        }
                    }
            }
        } while (choice > 0 && choice < 7);
    }
}
