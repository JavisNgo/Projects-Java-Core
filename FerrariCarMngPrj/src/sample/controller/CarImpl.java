/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sample.controller;

import java.util.ArrayList;
import java.util.List;
import sample.car.FerrariCarDAO;
import sample.car.FerrariCarDTO;
import tool.MyTool;

/**
 *
 * @author Admin
 */
public class CarImpl implements FerrariCarDAO {

    private List<FerrariCarDTO> car;
    String filename = "car.txt";
    public static final String ID_FORMAT = "F\\d{3}";

    public CarImpl() {
        car = new ArrayList<>();
    }

    @Override
    public List<FerrariCarDTO> getNewCars() {
        if (car.isEmpty()) {
            System.out.println("Empty list");
        }
        return car;
    }

    @Override
    public List<FerrariCarDTO> getAllCars() {
        car = MyTool.loadFromFile(filename);
        return car;
    }

    @Override
    public FerrariCarDTO searchCarByID(String carID) {
        for (FerrariCarDTO dto : car) {
            if (dto.getCarID() == carID) {
                return dto;
            }
        }
        return null;
    }

    @Override
    public boolean searchCarByName(String carname) {
        for (FerrariCarDTO dto : car) {
            if (dto.getName() == carname) {
                return true;
            }
        }
        return false;
    }

    @Override
    public FerrariCarDTO getCarByID(String carID) {
        car = MyTool.loadFromFile(filename);
        for (FerrariCarDTO dto : car) {
            if (dto.getCarID() == carID) {
                return dto;
            }
        }
        return null;
    }

    @Override
    public List<FerrariCarDTO> getCarByName(String carname) {
        car = MyTool.loadFromFile(filename);
        List<FerrariCarDTO> list = new ArrayList<>();
        for (FerrariCarDTO dto : car) {
            if (dto.getName() == carname) {
                list.add(dto);
                return list;
            }
        }
        return null;
    }

    @Override
    public void createCar() {
        String CarID;
        String Name;
        boolean Status;
        double Price;
        int Quantity;
        boolean checkname = true;
        System.out.println("Enter new car detail: ");
        CarID = MyTool.getStringForm("Enter carID: ", "Empty please type!!!", ID_FORMAT, "Wrong pattern !!");
        do {
            Name = MyTool.getString("Enter car name: ", "Empty please type!!!").trim().toUpperCase();
            if (searchCarByName(Name)) {
                System.out.println("Car is duplicated");
            } else {
                checkname = false;
            }
        } while (checkname);
        Status = MyTool.readBool("Enter status: ");
        Price = MyTool.getDouble("Enter price: ");
        Quantity = MyTool.getQuantity("Enter Quantity: ", 1, 10);
        car.add(new FerrariCarDTO(CarID, Name, Status, Price, Quantity));
        System.out.println("New car is added !!");
    }

    @Override
    public void updateCar(FerrariCarDTO Car) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void deleteCar(FerrariCarDTO Car) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
