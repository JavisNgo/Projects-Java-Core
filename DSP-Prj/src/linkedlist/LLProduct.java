package linkedlist;

import java.util.Date;
import java.util.Scanner;

public class LLProduct extends SLL {

    Scanner sc = null;

    public LLProduct() {
        super();
        sc = new Scanner(System.in);
    }

    private SLLNode<Product> find(String code) {
        SLLNode<Product> ref;
        for (ref = this.getHead(); ref != null; ref = ref.next) {
            if (ref.info.getCode().equals(code)) {
                return ref;
            }
        }
        return null;
    }

    public void add() {
        String code = "", name = "";
        int price = 0;
        String guaranty = "";
        Date d;
        System.out.println("Add new product");
        boolean proceed = false;

        do {
            System.out.println("Enter product's code: ");
            code = sc.nextLine().toUpperCase();
            proceed = find(code) != null;
            if (proceed == true) {
                System.out.println("This code is duplicated !");
            }
        } while (proceed);

        do {
            System.out.println("Enter product's name: ");
            name = sc.nextLine().toUpperCase();
            proceed = (name.length() == 0);
            if (proceed == true) {
                System.out.println("Name must be inputted. ");
            }
        } while (proceed);

        do {
            System.out.println("Enter product's price: ");
            price = Integer.parseInt(sc.nextLine());
            if (price <= 0) {
                System.out.println("Price must be greater than 0");
            }
        } while (price <= 0);

        do {
            try {
                System.out.println("Enter product's guaranty : ");
                System.out.println("Day/Month/Year");
                d = Product.parseDate(sc.nextLine(), "dd/MM/yyyy");
                guaranty = Product.dataToStr(d, "dd/MM/yyyy");
                proceed = (guaranty.equals(true));
            } catch (Exception e) {
                System.out.println("Wrong format");
                proceed = true;
            }
        } while (proceed);
        Product p = new Product(code, name, price, guaranty);
        this.addToTail(p);
        System.out.println("Added Successful");
    }

    public void remove() {
        if (this.isEmpty()) {
            System.out.println("The list is empty.");
        } else {
            String code = "";
            System.out.print("Enter the code of employee who will be removed: ");
            code = sc.nextLine().toUpperCase();
            SLLNode<Product> ref = find(code);
            if (ref == null) {
                System.out.println("Product does not exist.");
            } else {
                this.delete(ref.info);
                System.out.print("This product has been removed.");
            }
        }
    }

    public void updatePrice() {
        if (this.isEmpty()) {
            System.out.println("The list is empty.");
        } else {
            String code = "";
            System.out.print("Enter the code of product which will be promoted: ");
            code = sc.nextLine().toUpperCase();
            SLLNode<Product> ref = find(code);
            if (ref == null) {
                System.out.println("Product does not exist.");
            } else {
                int oldSal = ref.info.getSalary();
                int newSal;
                do {
                    System.out.print("Old price: " + oldSal + ", new price: ");
                    newSal = Integer.parseInt(sc.nextLine());
                } while (newSal <= oldSal);
                ref.info.setSalary(newSal);
                System.out.print("Product's price has been updated.");
            }
        }
    }

    public void updateGuaranty() {
        if (this.isEmpty()) {
            System.out.println("The list is empty.");
        } else {
            boolean check = true;
            Date d;
            String code = "";
            System.out.print("Enter the code of product which will be promoted: ");
            code = sc.nextLine().toUpperCase();
            SLLNode<Product> ref = find(code);
            if (ref == null) {
                System.out.println("Product does not exist.");
            } else {
                String oldGDate = ref.info.getGuaranty();
                String newGDate = "";
                do {
                    System.out.print("Old guaranty: " + oldGDate + ", new guaranty: ");
                    try {
                        d = Product.parseDate(sc.nextLine(), "dd/MM/yyyy");
                        newGDate = Product.dataToStr(d, "dd/MM/yyyy");
                        check = false;
                    } catch (Exception e) {
                        System.out.println("Wrong format");
                        check = true;
                    }
                } while (check);
                ref.info.setGuaranty(newGDate);
                System.out.print("Product's guaranty has been updated.");
            }
        }
    }

    public void print() {
        if (this.isEmpty()) {
            System.out.println("The list is emmpty");
        } else {
            System.out.println("PRODUCTS LIST");
            this.printAll();
        }
    }
}
