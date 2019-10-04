package food;

import java.sql.ResultSet;
import java.util.ArrayList;

public class Diner {
    int id;
    int quantity;
    int disponibility;

    float price;

    String productType;
    String nutritionalValue;

    public Diner() {
    }

    public Diner(int id, int quantity, int disponibility, float price, String productType, String nutritionalValue) {
        this.id = id;
        this.quantity = quantity;
        this.disponibility = disponibility;
        this.price = price;
        this.productType = productType;
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

    public int getDisponibility() {
        return disponibility;
    }

    public void setDisponibility(int disponibility) {
        this.disponibility = disponibility;
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

    public String getNutritionalValue() {
        return nutritionalValue;
    }

    public void setNutritionalValue(String nutritionalValue) {
        this.nutritionalValue = nutritionalValue;
    }

    public ArrayList<Diner> createArrayListFromTable(ResultSet rs) {

        ArrayList<Diner> us = new ArrayList<Diner>();
        try {

            while (rs.next()) {
                Diner d = new Diner();
                d.setId(rs.getInt("id"));
                d.setProductType(rs.getString("productType"));
                d.setQuantity(rs.getInt("quantity"));
                d.setPrice(rs.getInt("price"));
                d.setDisponibility(rs.getInt("disponibility"));
                d.setNutritionalValue(rs.getString("nutritional Value"));
                us.add(d);
            }

        } catch (Exception e) {
            System.err.println("Got an exception! ");
            System.err.println(e.getMessage());
        }
        return us;
    }
}
