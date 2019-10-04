package food;

import java.sql.ResultSet;
import java.util.ArrayList;

public class Butchery {
    int id;
    int quantity;

    float price;

    String productType;
    String expirationDay;
    String nutritionalValue;

    public Butchery() {
    }

    public Butchery(int id, int quantity, float price, String productType, String expirationDay, String nutritionalValue) {
        this.id = id;
        this.quantity = quantity;
        this.price = price;
        this.productType = productType;
        this.expirationDay = expirationDay;
        this.nutritionalValue = nutritionalValue;
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

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
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

    public String getNutritionalValue() {
        return nutritionalValue;
    }

    public void setNutritionalValue(String nutritionalValue) {
        this.nutritionalValue = nutritionalValue;
    }

    public ArrayList<Butchery> createArrayListFromTable(ResultSet rs) {
        ArrayList<Butchery> us = new ArrayList<Butchery>();
        try {

            while (rs.next()) {
                Butchery b = new Butchery();
                b.setId(rs.getInt("id"));
                b.setProductType(rs.getString("productType"));
                b.setQuantity(rs.getInt("quantity"));
                b.setPrice(rs.getInt("price/Unit"));
                b.setExpirationDay(rs.getString("expirationDay"));
                b.setNutritionalValue(rs.getString("nutritional Value"));
                us.add(b);
            }

        } catch (Exception e) {
            System.err.println("Got an exception! ");
            System.err.println(e.getMessage());
        }
        return us;
    }
}
