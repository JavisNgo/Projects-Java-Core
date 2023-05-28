/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package data;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.StringTokenizer;
import utils.Validation;

/**
 *
 * @author trien
 */
public class ProductList {

    private ArrayList<Product> list = new ArrayList();
    private ArrayList<Product> listDataFile = new ArrayList();
    private int count = 0;
    
    public void addDataFromFileToList(){
        listDataFile.clear();
        try {
            FileReader fr = new FileReader("Product.dat");
            BufferedReader bf = new BufferedReader(fr);
            String details;
            while ((details = bf.readLine()) != null) {
                StringTokenizer stk = new StringTokenizer(details, ",");
                String ID = stk.nextToken();
                String name = stk.nextToken();
                double price = Double.parseDouble(stk.nextToken());
                int quantity = Integer.parseInt(stk.nextToken());
                String status = stk.nextToken();
                listDataFile.add(new Product(ID, name, price, quantity, status));
            }
            bf.close();
            fr.close();
        } catch (Exception e) {
            System.out.println("Read data from Product.dat fail");
        }
    }
    public void readDataFromFile() {
        try {
            FileReader fr = new FileReader("Product.dat");
            BufferedReader bf = new BufferedReader(fr);
            String details;
            while ((details = bf.readLine()) != null) {
                StringTokenizer stk = new StringTokenizer(details, ",");
                String ID = stk.nextToken();
                String name = stk.nextToken();
                double price = Double.parseDouble(stk.nextToken());
                int quantity = Integer.parseInt(stk.nextToken());
                String status = stk.nextToken();
                list.add(new Product(ID, name, price, quantity, status));
                count++;
            }
            bf.close();
            fr.close();
        } catch (Exception e) {
            System.out.println("Read data from Product.dat fail");
        }
    }

    public void createAProduct() {
        String productID, productName, status;
        int index;
        do {
            productID = Validation.regexString("Enter ID Product(Pxxx): ", "Wrong format.Input again", "^[P|p]\\d{3}$");
            index = checkID(productID);
            if (index >= 0) {
                System.out.println("Product ID has been already in list.Input again.");
            }
        } while (index >= 0);
        boolean checkName;
        do {
            checkName = false;
            productName = Validation.getString("Enter Name Product: ", "Not blank or empty.");
            if (productName.length() < 5) {
                System.out.println("Product's name more than five characters.");
            }
            checkName = checkExistName(productName);
            if(checkName == true){
                System.out.println("Product's name has been alredy in list.Input again!");
            }
        } while (productName.length() < 5 || checkName == true);
        double price = Validation.getADouble("Enter Price Product[0..10000]: ", "Just from 0 to 10000", 0, 10000);
        int quantity = Validation.getAnInteger("Enter Quantity Product: ", "Just from 0 to 1000", 0, 1000);
        int choice = Validation.getAnInteger("Enter choice status(1.Avaiable or 2.Not Avaiable): ", "Just 1 or 2", 1, 2);
        if (choice == 1) {
            status = "Avaiable";
        } else {
            status = "Not Avaiable";
        }
        list.add(new Product(productID, productName, price, quantity, status));
        System.out.println("The Product's information has been added..");
    }
    public boolean checkExistName(String name){
        for (int i = 0; i < list.size(); i++) {
            if(list.get(i).getName().equalsIgnoreCase(name)){
                return true;
            }
        }
        return false;
    }
    public int checkID(String id) {
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).getId().equalsIgnoreCase(id)) {
                return i;
            }
        }
        return -1;
    }

    public void checkExitProduct() {
        addDataFromFileToList();
        boolean check = false;
        String find = Validation.getString("Enter Name Product To Search: ", "Not blank or empty.");
        for (int i = 0; i < listDataFile.size(); i++) {
            if(listDataFile.get(i).getName().toLowerCase().contains(find.toLowerCase())){
                check = true;
            }
        }
        if(check == true){
            System.out.println("Exit Product");
        }else{
            System.out.println("No Product Found!");
        }
        
    }

    public void searchProduct() {
        ArrayList<Product> listSP = new ArrayList();
        boolean flag = false;
        String name = Validation.getString("Enter Name Product: ", "Not blank or empty.");
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).getName().toLowerCase().contains(name.toLowerCase())) {
                listSP.add(list.get(i));
                flag = true;
            }
        }
        if (flag == false) {
            System.out.println("Have no any Product.");
            return;
        }
        Collections.sort(listSP, (o1, o2) -> {
            return o1.getName().compareToIgnoreCase(o2.getName());
        });
        System.out.println("+----------+--------------------+----------+----------+---------------+");
        System.out.println("|    ID    |        NAME        |   PRICE  | QUANTITY |     STATUS    |");
        System.out.println("+----------+--------------------+----------+----------+---------------+");
        for (int i = 0; i < listSP.size(); i++) {
            listSP.get(i).showInfor();
        }
        System.out.println("+----------+--------------------+----------+----------+---------------+");
    }

    public void updateProduct() {
        int choice = Validation.getAnInteger("Enter your choice(1.Update Product OR 2.Delete Product): ", "Just 1 or 2.", 1, 2);
        if (choice == 1) {
            String productID = Validation.regexString("Enter ID Product(Pxxx): ", "Wrong format.Input again", "^[P|p]\\d{3}$");
            int index = checkID(productID);
            if (index >= 0) {
                boolean check,checkName;
                String newName;
                do {
                    check = true;
                    checkName = false;
                    newName = Validation.updateString("Enter New Name Product: ", list.get(index).getName());
                    if(!newName.isEmpty() && newName.length() < 5){
                        System.out.println("Product's Name more than five characters.");
                        check = false;
                    }
                    if(!newName.isEmpty()){
                        checkName = checkExistName(newName);
                        if(checkName == true){
                            System.out.println("Product's name has been already in list.Input again!");
                        }
                    }
                }while(check == false || checkName == true);
                double newPrice = Validation.updateADouble("Enter New Price Product: ", 0, 10000, list.get(index).getPrice());
                int newQuantity = Validation.updateAnInteger("Enter New Quantity Product: ", 0, 1000, list.get(index).getQuantity());
                int choiceStatus = Validation.getAnInteger("Do you want change status(1.Yes OR 2.No): ", "Just 1 or 2", 1, 2);
                list.get(index).setName(newName);
                list.get(index).setPrice(newPrice);
                list.get(index).setQuantity(newQuantity);
                if(choiceStatus == 1){
                    if(list.get(index).getStatus().equals("Avaiable"))
                        list.get(index).setStatus("Not Avaiable");
                    else
                        list.get(index).setStatus("Avaiable");
                }
                System.out.println("The Product's information has been updated..Successfully.");
            } else {
                System.out.println("Product name does not exist.");
            }
        } else {
            String productID = Validation.getString("Enter ID Product: ", "Not blank or empty.");
            int index = checkID(productID);
            if (index >= 0) {
                list.remove(index);
                System.out.println("The product's information has been deleted..Successfully.");
            } else {
                System.out.println("Product name does not exist.");
            }
        }
    }

    public void saveToFile() {
        try {
            File f = new File("Product.dat");
            FileWriter fw = new FileWriter(f);
            PrintWriter pw = new PrintWriter(fw);
            for (int i = 0; i < list.size(); i++) {
                pw.println(list.get(i).toString());
            }
            pw.close();
            fw.close();
        } catch (Exception e) {
            System.out.println("Write data to Product.dat fail");
        }
        System.out.println("Save to file Successfully.");
    }

    public void printAllListNewProduct() {
        if (list.isEmpty()) {
            System.out.println("List empty.Nothing to print.");
        } else {
            if(count == list.size()){
                System.out.println("Not found New Product's Information.Nothing to print.");
                return;
            }
            System.out.println("HERE IS INFORMATION OF LIST NEW PRODUCT:");
            System.out.println("+----------+--------------------+----------+----------+---------------+");
            System.out.println("|    ID    |        NAME        |   PRICE  | QUANTITY |     STATUS    |");
            System.out.println("+----------+--------------------+----------+----------+---------------+");
            for (int i = count; i < list.size(); i++) {
                list.get(i).showInfor();
            }
            System.out.println("+----------+--------------------+----------+----------+---------------+");
        }
    }
    public void printAllProductFromFile(){
        addDataFromFileToList();
        if (listDataFile.isEmpty()) {
            System.out.println("List empty.Nothing to print.");
        } else {
            Collections.sort(listDataFile, Comparator.comparingInt(listDataFile -> ((Product)listDataFile).getQuantity()).reversed().thenComparingDouble(listDataFile -> ((Product)listDataFile).getPrice()));
            System.out.println("HERE IS INFORMATION OF LIST PRODUCT FROM FILE:");
            System.out.println("+----------+--------------------+----------+----------+---------------+");
            System.out.println("|    ID    |        NAME        |   PRICE  | QUANTITY |     STATUS    |");
            System.out.println("+----------+--------------------+----------+----------+---------------+");
            for (int i = 0; i < listDataFile.size(); i++) {
                listDataFile.get(i).showInfor();
            }
            System.out.println("+----------+--------------------+----------+----------+---------------+");
        }
    }
}
