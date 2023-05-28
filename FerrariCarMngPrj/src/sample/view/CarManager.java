/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sample.view;

import java.util.ArrayList;
import java.util.List;
import sample.car.FerrariCarDAO;
import sample.car.FerrariCarDTO;
import sample.controller.CarImpl;
import tool.MyTool;

/**
 *
 * @author Admin
 */
public class CarManager {

    public static void main(String[] args) {
        String[] options = {"Print the list", "Create a Car",
            "Check existing Car",
            "Search Car information by name",
            "Update Car", "Save Car to file",
            "Print list Car from the file",
            "Quit"
        };
        Menu mnu = new Menu(options);
        int choice = 0;
        String reponse, inputname, inputcarID;
        FerrariCarDTO car;
        boolean change = false;
        FerrariCarDAO dao = new CarImpl();
        do {
            String choice2 = null;
            boolean quit = false;
            choice = mnu.getChoice("CAR MANAGER");
            switch (choice) {
                case 1:
                    for (FerrariCarDTO dto : dao.getNewCars()) {
                        System.out.println(dto.getCarID() + ", " + dto.getName() + ", " + dto.getPrice() + ", " + dto.getQuantity());
                    }
                    break;
                case 2:
                    dao.createCar();
                    change = true;
                    break;
                case 3:
                    inputcarID = MyTool.getString("Enter CarID: ", "EMPTY !!!");
                    if(dao.getCarByID(inputcarID)!=null) {
                        System.out.println("Exist");
                    } else {
                        System.out.println("Not exist");
                    }
                case 4:
                    inputname = MyTool.getString("Enter Car name: ", "EMPTY !!!");
                    List<FerrariCarDTO> list = new ArrayList<>();
                    list = new CarImpl().getCarByName(inputname);
                    if(list!=null) {
                        for(FerrariCarDTO dto : list) {
                            System.out.println(dto.getCarID() + ", " + dto.getName() + ", " + dto.getPrice() + ", " + dto.getQuantity());
                        }
                    } else {
                        System.out.println("Not found !!");
                    }
            }
        } while (choice > 0 && choice < 8);
    }
}
