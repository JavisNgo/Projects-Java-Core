package data;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import tools.MyTools;

public class ProductList {

    private ArrayList<Product> list = new ArrayList<>();

    public ArrayList<Product> getList() {
        return list;
    }

    public void setList(ArrayList<Product> list) {
        this.list = list;
    }

    public void loadProductsFromFile() {
        String ID;
        String Name;
        int UnitPrice;
        int Quantity;
        String Status;
        String filename = "Products.txt";
        List<String> lines = MyTools.readLinesFromFile(filename);
        for (int i = 0; i < lines.size(); i++) {
            String line = lines.get(i);
            String[] parts = line.split("" + ',');
            ID = parts[0].trim();
            Name = parts[1].trim();
            UnitPrice = Integer.parseInt(parts[2].trim());
            Quantity = Integer.parseInt(parts[3].trim());
            Status = parts[4].trim();
            list.add(new Product(ID, Name, UnitPrice, Quantity, Status));
        }
    }

    public void Print() {
        System.out.println("------List of Product------");
        if (list.isEmpty()) {
            System.out.println("The list is empty. Please create !");
        } else {
            Comparator comparatorQuantity = new Comparator<Product>() {
                @Override
                public int compare(Product t, Product t1) {
                    if (t.getQuantity() > t1.getQuantity()) {
                        return -1;
                    } else if (t.getQuantity() == t1.getQuantity()) {
                        return t1.getID().compareToIgnoreCase(t1.getID());
                    } else {
                        return 1;
                    }
                }
            };
            Collections.sort(list, comparatorQuantity);
            for (Product x : list) {
                System.out.println(x);
            }
        }
    }

    public void createProduct() {
        String ID;
        String Name;
        int UnitPrice;
        int Quantity;
        String Status;
        boolean check = true;
        do {
            ID = MyTools.getStringForm("Enter ID: ", "ID is not empty !", "\\w{5}", "Code is wrong format !");
            if (checkID(ID) >= 0) {
                System.out.println("Exist Product");
            } else {
                check = false;
            }
        } while (check);
        Name = MyTools.getString("Enter product name: ", "Name is not empty !");
        UnitPrice = MyTools.getInt("Enter unit price: ", 0, 10000);
        Quantity = MyTools.getInt("Enter quantity: ", 0, 1000);
        Status = MyTools.getStringForm("Enter status (Y: Avaiable or N: Not-Avaiable): ", "Status is not empty", "^[y|n|Y|N&&[a-zA-Z]]$", "Wrong Status format (Y or N only)");
        if (Status.equalsIgnoreCase("Y")) {
            Status = "Available";
        } else {
            Status = "Not Available";
        }
        list.add(new Product(ID, Name, UnitPrice, Quantity, Status));
        System.out.println("Added successfull !!!");
    }

    public int checkID(String ID) {
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).getID().equals(ID)) {
                return i;
            }
        }
        return -1;
    }

    public void checkExist() {
        String ID = MyTools.getStringForm("Enter ID: ", "ID is not empty !", "\\w{5}", "Code is wrong format !");
        String filename = "Products.txt";
        List<String> lines = MyTools.readLinesFromFile(filename);
        ArrayList<String> temp = new ArrayList<>();
        for (String line : lines) {
            String[] parts = line.split(",");
            if (parts[0].equalsIgnoreCase(ID)) {
                temp.add(line);
            }
        }
        if (!temp.isEmpty()) {
            System.out.println("Exist Product ");
        } else {
            System.out.println("No Product Found !");
        }
    }

    public void searchByName() {
        String search = MyTools.getString("Enter the product want to search: ", "Not empty");
        String filename = "Products.txt";
        List<String> lines = MyTools.readLinesFromFile(filename);
        ArrayList<String> temp = new ArrayList<>();
        for (String line : lines) {
            String[] parts = line.split(",");
            if (parts[1].equalsIgnoreCase(search)) {
                temp.add(line);
            }
        }
        if (temp.isEmpty()) {
            System.out.println("Have no any Product");
        } else {
            for (int i = 0; i < list.size(); i++) {
                if (list.get(i).getName().equalsIgnoreCase(search)) {
                    System.out.println(list.get(i).toString());
                }
            }

        }

    }

    public void updateByCode() {
        String updateName;
        int updateUnitPrice;
        int updateQuantity;
        String updateStatus;
        boolean check = true;

        if (list.isEmpty()) {
            System.out.println("The list is empty. Please create !");
        } else {
            String ID = MyTools.getStringForm("Enter ID: ", "ID is not empty !", "\\w{5}", "Code is wrong format !");;
            int pos = checkID(ID);
            if (pos >= 0) {
                if (ID.equalsIgnoreCase(list.get(pos).getID())) {

                    //Update Name
                    System.out.print("Enter updated Product Name (Enter to keep Produce Name data): ");
                    updateName = MyTools.SC.nextLine();
                    if (updateName.isEmpty()) {
                        updateName = list.get(pos).getName();
                    }

                    //Update Unit Price
                    do {
                        try {
                            System.out.print("Enter updated UnitPrice (Enter to keep data): ");
                            updateUnitPrice = Integer.parseInt(MyTools.SC.nextLine());
                            if (updateUnitPrice > 10000) {
                                System.out.println("UnitPrice must be smaller 10000");
                            } else if (updateUnitPrice < 0) {
                                System.out.println("UnitPrice must be larger 0");
                            } else {
                                check = false;
                            }
                        } catch (Exception e) {
                            updateUnitPrice = list.get(pos).getUnitPrice();
                            check = false;
                        }
                    } while (check || updateUnitPrice > 10000 || updateUnitPrice < 0);

                    //Update Quantity
                    do {
                        try {
                            System.out.print("Enter updated Quantity (Enter to keep Quantity data): ");
                            updateQuantity = Integer.parseInt(MyTools.SC.nextLine());
                            if (updateQuantity > 1000) {
                                System.out.println("Quantity must be smaller 1000");
                            } else if (updateQuantity < 0) {
                                System.out.println("Quantity must be larger 0");
                            } else {
                                check = false;
                            }
                        } catch (Exception e) {
                            updateQuantity = list.get(pos).getQuantity();
                            check = false;
                        }
                    } while (check || updateQuantity > 1000 || updateQuantity < 0);

                    //Update Status
                    do {
                        System.out.println("Enter updated Status (Y or N) (Enter to keep status data): ");
                        updateStatus = MyTools.SC.nextLine();
                        if (updateStatus.isEmpty()) {
                            updateStatus = list.get(pos).getStatus();
                            check = false;
                        } else if (!updateStatus.matches("^[y|n|Y|N&&[a-zA-Z]]$")) {
                            System.out.println("Wrong format Status (Y or N)");
                        } else {
                            if (updateStatus.equalsIgnoreCase("Y")) {
                                updateStatus = "Available";
                            } else {
                                updateStatus = "Not Available";
                            }
                            check = false;
                        }
                    } while (check);

                    list.get(pos).setName(updateName);
                    list.get(pos).setUnitPrice(updateUnitPrice);
                    list.get(pos).setQuantity(updateQuantity);
                    list.get(pos).setStatus(updateStatus);

                    System.out.println("Updated sucessful !");
                }

            }
        }
    }

    public void removeByCode() {
        if (list.isEmpty()) {
            System.out.println("The list is empty. Please create !");
        } else {
            boolean check = true;
            String ID = MyTools.getStringForm("Enter ID: ", "ID is not empty !", "\\w{5}", "Code is wrong format !");;
            int pos = checkID(ID);
            if (pos >= 0) {
                if (ID.equalsIgnoreCase(list.get(pos).getID())) {
                    list.remove(pos);
                    System.out.println("Code is removed !!");
                } else {
                    System.out.println("Not found !!");
                }
            }
        }
    }

    public void writeFile() {
        String filename = "Products.txt";
        try {
            if (list.isEmpty()) {
                System.out.println("There is no product to save");
                return;
            }
            FileWriter fw = new FileWriter(filename);
            PrintWriter pw = new PrintWriter(fw);
            pw.println("");
            for (int i = 0; i < list.size(); i++) {
                pw.println(list.get(i));
            }
            pw.close();
            fw.close();
            System.out.println("Data saved");
        } catch (Exception e) {
            System.out.println(e);
        }
    }

}
