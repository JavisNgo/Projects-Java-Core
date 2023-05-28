
package data;
import tools.MyTools;

public class Product {
    public static final char SEPARATOR = ',';
    private String ID;
    private String Name;
    private int UnitPrice;
    private int Quantity;
    private String Status;

    public Product(String ID, String Name, int UnitPrice, int Quantity, String Status) {
        this.ID = ID;
        this.Name = Name;
        this.UnitPrice = UnitPrice;
        this.Quantity = Quantity;
        this.Status = Status;
    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public String getName() {
        return Name;
    }

    public void setName(String Name) {
        this.Name = Name;
    }

    public int getUnitPrice() {
        return UnitPrice;
    }

    public void setUnitPrice(int UnitPrice) {
        this.UnitPrice = UnitPrice;
    }

    public int getQuantity() {
        return Quantity;
    }

    public void setQuantity(int Quantity) {
        this.Quantity = Quantity;
    }

    public String getStatus() {
        return Status;
    }

    public void setStatus(String Status) {
        this.Status = Status;
    }

    @Override
    public String toString() {
        return ID +","+Name+","+UnitPrice+","+Quantity+","+Status;
    }

    
}
