package mng;

import data.ProductList;

public class Main {

    public static void main(String[] args) {
        String[] options = {
            "1. Print", "2. Create a Product", "3. Check exits Product", "4. Search Product information by Name", 
            "5. Update Product", "6. Delete Product", "7. Save Products to file"
        };
        
        Menu menu = new Menu(options);
        ProductList obj = new ProductList();
        int choice = 0;
        boolean flag = true;
        obj.loadProductsFromFile();
        do {
            choice = menu.getChoice("Managing dealers");
            switch (choice) {
                case 1:
                    System.out.println("===========================");
                    obj.Print();
                    break;
                case 2:
                    System.out.println("===========================");
                    obj.createProduct();
                    break;
                case 3:
                    System.out.println("===========================");
                    obj.checkExist();
                    break;
                case 4:
                    System.out.println("===========================");
                    obj.searchByName();
                    break;
                case 5:
                    System.out.println("===========================");
                    obj.updateByCode();
                    break;
                case 6:
                    System.out.println("===========================");
                    obj.removeByCode();
                    break;
                case 7:
                    System.out.println("===========================");
                    obj.writeFile();
                    break;
                default:
                    System.out.println("===========================");
                    System.out.println("Program closed!!!");
                    flag = false;
                    break;
            }
        } while (flag);
    }
}
