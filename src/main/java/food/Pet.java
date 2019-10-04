package food;
import java.sql.ResultSet;
import java.util.ArrayList;

public class Pet {
    int id;

    float price;

    String productType;
    String expirationDay;
    String category;

    public Pet() {
    }

    public Pet(int id, float price, String productType, String expirationDay, String category) {
        this.id = id;
        this.price = price;
        this.productType = productType;
        this.expirationDay = expirationDay;
        this.category = category;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }
    public ArrayList<Pet> createArrayListFromTable(ResultSet rs) {

        ArrayList<Pet> us = null;
        try {
            us = new ArrayList<Pet>();

            while (rs.next()) {
                Pet p = new Pet();
                p.setId(rs.getInt("id"));
                p.setProductType(rs.getString("productType"));
                p.setCategory(rs.getString("category"));
                p.setPrice(rs.getInt("price"));
                p.setExpirationDay(rs.getString("expirationDay"));
                us.add(p);
            }

        } catch (Exception e) {
            System.err.println("Got an exception! ");
            System.err.println(e.getMessage());
        }
        return us;
    }
}
