
package linkedlist;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class Product {

    String code = "";
    String name = "";
    int price = 0;
    String guaranty;

    public Product() {
    }
    
    public Product(String c, String n, int p, String g) {
        code = c;
        name = n;
        price = p;
        guaranty = g;
    }
    
//    public Product(String c, String n, int p, Date g) {
//        code = c;
//        name = n;
//        price = p;
//        guaranty = g;
//    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getSalary() {
        return price;
    }

    public void setSalary(int price) {
        this.price = price;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getGuaranty() {
        return guaranty;
    }

    public void setGuaranty(String guaranty) {
        this.guaranty = guaranty;
    }
    
    

//    public Date getGuaranty() {
//        return guaranty;
//    }
//
//    public void setGuaranty(Date guaranty) {
//        this.guaranty = guaranty;
//    }
    
    public boolean equalS(Product p) {
        return p.getCode().equals(this.getCode())
                && p.getName().equals(this.getName())
                && p.getSalary() == this.getSalary();
    }
    
    public static Date parseDate(String dateStr, String dateFormat) {
        SimpleDateFormat dF = (SimpleDateFormat) SimpleDateFormat.getInstance();
        dF.applyPattern(dateFormat);
        try {
            long t = dF.parse(dateStr).getTime();
            return new Date(t);
        } catch (ParseException e) {
            System.out.println(e);
        }
        return null;
    }
    
    public static String dataToStr(java.util.Date date, String dateFormat) {
        SimpleDateFormat dF = (SimpleDateFormat) SimpleDateFormat.getInstance();
        dF.applyPattern(dateFormat);
        return dF.format(date);
    }
    
    @Override
    public String toString() {
        return code +"\t"+name+"\t"+price+"\t"+guaranty;
    }
}
