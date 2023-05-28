/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sample.car;

import java.util.List;

/**
 *
 * @author Admin
 */
public interface FerrariCarDAO {
    List<FerrariCarDTO> getNewCars();
    List<FerrariCarDTO> getAllCars();
    FerrariCarDTO searchCarByID(String carID);
    boolean searchCarByName(String carname);
    FerrariCarDTO getCarByID(String carID);
    List<FerrariCarDTO> getCarByName(String carname);
    void createCar();
    void updateCar(FerrariCarDTO Car);
    void deleteCar(FerrariCarDTO Car);
    
}
