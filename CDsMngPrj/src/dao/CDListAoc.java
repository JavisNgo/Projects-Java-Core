package dao;

import java.util.ArrayList;
import java.util.List;
import model.CD;
import tools.MyTools;

public class CDListAoc implements CDListDao {

    String filename = "CDList.txt";
    private ArrayList<CD> list = new ArrayList<>();

    public ArrayList<CD> getList() {
        return list;
    }

    public void setList(ArrayList<CD> list) {
        this.list = list;
    }

//    public void loadCDFromFile() {
//        String ID;
//        String name;
//        String type;
//        String title;
//        int unitprice;
//        String publishingyear;
//
//        List<String> lines = MyTools.readLinesFromFile("CD.txt");
//        for (int i = 0; i < lines.size(); i++) {
//            String line = lines.get(i);
//            String[] parts = line.split("" + ',');
//            ID = parts[0].trim();
//            name = parts[1].trim();
//            type = parts[2].trim();
//            title = parts[3].trim();
//            unitprice = Integer.parseInt(parts[4].trim());
//            publishingyear = parts[5].trim();
//            list.add(new CD(ID, name, type, title, unitprice, publishingyear));
//        }
//    }
    public int checkID(String ID) {
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).getID().equals(ID)) {
                return i;
            }
        }
        return -1;
    }

    public void searchByTitle() {
        String search = MyTools.getString("Enter title: ", "Title is not empty");
        List<String> lines = MyTools.readLinesFromFile("CD.txt");
        ArrayList<String> temp = new ArrayList<>();
        for (String line : lines) {
            String[] parts = line.split(",");
            if (parts[1].equalsIgnoreCase(search)) {
                temp.add(line);
            }
        }
        if (temp.isEmpty()) {
            System.out.println("Have no any CD");
        } else {
            for (int i = 0; i < list.size(); i++) {
                if (list.get(i).getTitle().equalsIgnoreCase(search)) {
                    System.out.println(list.get(i).toString());
                }
            }
        }
    }

    public void displayTheCatalog() {
        List<String> lines = MyTools.readLinesFromFile("CD.txt");
        for (String line : lines) {
            System.out.println(line.toString());
        }
    }

    @Override
    public List<CD> getNewCD() {
        if (list.isEmpty()) {
            System.out.println("Empty list !");
        }
        return list;
    }

    @Override
    public List<CD> getAllCD() {
        list = MyTools.loadCDFromFile(filename);
        return list;
    }

    @Override
    public CD searchCDByID(String cdid) {
        for (CD x : list) {
            if (x.getID().equalsIgnoreCase(cdid)) {
                return x;
            }
        }
        return null;
    }

    @Override
    public boolean searchCDByTitle(String cdtitle) {
        for (CD x : list) {
            if (x.getTitle().contains(cdtitle)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public CD getCDByCDID(String cdid) {
        list = MyTools.loadCDFromFile(filename);
        for (CD obj : list) {
            if (obj.getID().equalsIgnoreCase(cdid)) {
                return obj;
            }
        }
        return null;
    }

    @Override
    public List<CD> getCDByTitle(String cdtitle) {
        list = MyTools.loadCDFromFile(filename);
        List<CD> tmp = new ArrayList<>();
        for (CD obj : list) {
            if (obj.getTitle().contains(cdtitle)) {
                tmp.add(obj);
            }
        }
        return tmp;
    }

    @Override
    public void createCD() {
        String ID;
        String name;
        String type;
        String title;
        int unitprice;
        String publishingyear;
        boolean check = true;
        do {
            ID = MyTools.getStringForm("Enter ID: ", "ID is not empty", "^CD\\d{3}$", "Wrong ID");
            if (checkID(ID) >= 0) {
                System.out.println("Existed ID");
            } else {
                check = false;
            }
        } while (check);
        name = MyTools.getStringForm("Enter name: ", "Not empty", "(game|movie|music)", "Wrong name");
        type = MyTools.getStringForm("Enter type: ", "Not empty", "(audio|video)", "Wrong type");
        title = MyTools.getString("Enter title: ", "Title is not empty");
        unitprice = MyTools.getInt("Enter unitprice: ");
        publishingyear = MyTools.parseDate("Enter date: ", "dd/MM/yyyy");

        list.add(new CD(ID, name, type, title, unitprice, publishingyear));
        System.out.println("Added successfull !!!");
    }

    @Override
    public void update(CD CDs) {
        String updateName;
        String updateType;
        String updateTitle;
        int updateUnitprice = 0;
        String updatePublishingyear = null;
        boolean flag = false;
        String ID = MyTools.getStringForm("Enter ID: ", "ID is not empty", "^CD\\d{3}$", "Wrong ID");
        int pos = checkID(ID);
        if (pos >= 0) {

            //Update Name
            do {
                System.out.println("Enter update Name: ");
                updateName = MyTools.SC.nextLine();
                if (updateName.isEmpty()) {
                    flag = false;
                } else if (!updateName.matches("(game|movie|music)")) {
                    System.out.println("Wrong format !!!");
                    flag = true;
                }
            } while (flag);

            //Update Type
            do {
                System.out.println("Enter update Type: ");
                updateType = MyTools.SC.nextLine();
                if (updateType.isEmpty()) {
                    flag = false;
                } else if (!updateType.matches("(audio|video)")) {
                    System.out.println("Wrong format !!!");
                    flag = true;
                }
            } while (flag);

            //Update Title
            do {
                System.out.println("Enter update Title: ");
                updateTitle = MyTools.SC.nextLine();
                if (updateTitle.isEmpty()) {
                    flag = false;
                }
            } while (flag);

            //Update unitprice
            do {
                try {
                    System.out.println("Enter unit price: ");
                    updateUnitprice = Integer.parseInt(MyTools.SC.nextLine());
                } catch (Exception e) {
                    System.out.println("Input number !!!");
                    flag = true;
                }
            } while (flag);

            //Update publishing year
            do {
                try {
                    System.out.println("Enter Update publishing year: ");
                    updatePublishingyear = MyTools.parseDate("Enter date: ", "dd/MM/yyyy");
                } catch (Exception e) {
                    System.out.println("Wrong format !!!");
                    flag = true;
                }
            } while (flag);

            list.get(pos).setName(updateName);
            list.get(pos).setType(updateType);
            list.get(pos).setTitle(updateTitle);
            list.get(pos).setUnitprice(updateUnitprice);
            list.get(pos).setPublishingyear(updatePublishingyear);
            
            
//            //Update in file
//            List<String> lines = MyTools.readLinesFromFile("CD.txt");
//            for (String line : lines) {
//                String[] parts = line.split(",");
//                updateName = parts[1];
//                updateType = parts[2];
//                updateTitle = parts[3];
//            }
        }
    }

    @Override
    public void delete(CD CDs) {
        list = MyTools.loadCDFromFile(filename);
        list.remove(CDs);
        System.out.println(" "+CDs.getID()+" is deleted !!!");
    }

    @Override
    public void saveFile() {
       MyTools.saveToFile(list);
        System.out.println("New CD(s) has been saved to file");
    }
}
