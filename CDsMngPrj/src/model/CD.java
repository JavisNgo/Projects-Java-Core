
package model;

import java.sql.Date;


public class CD {
    private String ID;
    private String name;
    private String type;
    private String title;
    private int unitprice;
    private String publishingyear;    

    public CD(String ID, String name, String type, String title, int unitprice, String publishingyear) {
        this.ID = ID;
        this.name = name;
        this.type = type;
        this.title = title;
        this.unitprice = unitprice;
        this.publishingyear = publishingyear;
    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getUnitprice() {
        return unitprice;
    }

    public void setUnitprice(int unitprice) {
        this.unitprice = unitprice;
    }

    public String getPublishingyear() {
        return publishingyear;
    }

    public void setPublishingyear(String publishingyear) {
        this.publishingyear = publishingyear;
    }
    
    @Override
    public String toString() {
        return ID+","+name+","+type+","+title+","+unitprice+","+publishingyear;
    }
    
    
}
