package food;

import java.sql.ResultSet;
import java.util.ArrayList;

public class DairyProducts {
    int id;
    int quantity;

    float price;

    String productType;
    String provenance;
    String expirationDay;

    public DairyProducts() {
    }

    public DairyProducts(int id, int quantity, float price, String productType, String provenance, String expirationDay) {
        this.id = id;
        this.quantity = quantity;
        this.price = price;
        this.productType = productType;
        this.provenance = provenance;
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

    public String getProvenance() {
        return provenance;
    }

    public void setProvenance(String provenance) {
        this.provenance = provenance;
    }

    public String getExpirationDay() {
        return expirationDay;
    }

    public void setExpirationDay(String expirationDay) {
        this.expirationDay = expirationDay;
    }

    public ArrayList<DairyProducts> createArrayListFromTable(ResultSet rs) {

        ArrayList<DairyProducts> us = new ArrayList<DairyProducts>();
        try {
            while (rs.next()) {
                DairyProducts d = new DairyProducts();
                d.setId(rs.getInt("id"));
                d.setProductType(rs.getString("productType"));
                d.setQuantity(rs.getInt("quantity"));
                d.setPrice(rs.getInt("price/litre"));
                d.setProvenance(rs.getString("provenance"));
                d.setExpirationDay(rs.getString("expirationDate"));
                us.add(d);
            }

        } catch (Exception e) {
            System.err.println("Got an exception! ");
            System.err.println(e.getMessage());
        }
        return us;
    }

}
