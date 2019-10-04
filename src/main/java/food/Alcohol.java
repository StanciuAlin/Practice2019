package food;

import java.sql.ResultSet;
import java.util.ArrayList;

public class Alcohol {

    int id;
    int quantity;
    int age;

    float price;
    float alcoholLevel;

    String productType;
    String expirationDay;

    public Alcohol() {
    }

    public Alcohol(int id, int quantity, int age, float price, float alcoholLevel, String productType, String expirationDay) {
        this.id = id;
        this.quantity = quantity;
        this.age = age;
        this.price = price;
        this.alcoholLevel = alcoholLevel;
        this.productType = productType;
        this.expirationDay = expirationDay;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public float getAlcoholLevel() {
        return alcoholLevel;
    }

    public void setAlcoholLevel(float alcoholLevel) {
        this.alcoholLevel = alcoholLevel;
    }

    public String getProductType() {
        return productType;
    }

    public void setProductType(String productType) {
        this.productType = productType;
    }

    public String getExpirationDay() {
        return expirationDay;
    }

    public void setExpirationDay(String expirationDay) {
        this.expirationDay = expirationDay;
    }

    public ArrayList<Alcohol> createArrayListFromTable(ResultSet rs) {

        ArrayList<Alcohol> us = null;
        try {
            us = new ArrayList<Alcohol>();

            while (rs.next()) {
                Alcohol u = new Alcohol();
                u.setId(rs.getInt("id"));
                u.setProductType(rs.getString("productType"));
                u.setQuantity(rs.getInt("quantity"));
                u.setPrice(rs.getInt("price"));
                u.setExpirationDay(rs.getString("expirationDay"));
                u.setAlcoholLevel(rs.getFloat("alcoholLevel"));
                u.setAge(rs.getInt("age"));
                us.add(u);
            }

        } catch (Exception e) {
            System.err.println("Got an exception! ");
            System.err.println(e.getMessage());
        }
        return us;
    }
}
