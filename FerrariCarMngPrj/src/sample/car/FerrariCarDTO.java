/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sample.car;

/**
 *
 * @author Admin
 */
public class FerrariCarDTO {
    private String CarID;
    private String Name;
    private boolean Status;
    private double Price;
    private int Quantity;
    

    public FerrariCarDTO() {
    }

    public FerrariCarDTO(String CarID, String Name, boolean Status, double Price, int Quantity) {
        this.CarID = CarID;
        this.Name = Name;
        this.Status = Status;
        this.Price = Price;
        this.Quantity = Quantity;
    }

    public String getCarID() {
        return CarID;
    }

    public void setCarID(String CarID) {
        this.CarID = CarID;
    }

    public String getName() {
        return Name;
    }

    public void setName(String Name) {
        this.Name = Name;
    }

    public boolean isStatus() {
        return Status;
    }

    public void setStatus(boolean Status) {
        this.Status = Status;
    }

    public double getPrice() {
        return Price;
    }

    public void setPrice(double Price) {
        this.Price = Price;
    }

    public int getQuantity() {
        return Quantity;
    }

    public void setQuantity(int Quantity) {
        this.Quantity = Quantity;
    }

    @Override
    public String toString() {
        return super.toString(); //To change body of generated methods, choose Tools | Templates.
    }
    
    
}
