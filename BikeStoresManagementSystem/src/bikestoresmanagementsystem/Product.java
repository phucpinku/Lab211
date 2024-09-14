package bikestoresmanagementsystem;

public class Product {
    private final String id;
    private String name;
    private String brandID;
    private String categoryID;
    private int year;
    private int listPrice;
    
    public Product(String id) {
        this.id = id;
    }
    
    public Product(String id, String name, String brandID, String categoryID, int year, int listPrice) {
        this.id = id;
        this.name = name;
        this.brandID = brandID;
        this.categoryID = categoryID;
        this.year = year; 
        this.listPrice = listPrice;
    }
    
    public String getId() {
        return id;
    }
    
    public String getName() {
        return name;
    }

    public String getBrandID() {
        return brandID;
    }

    public String getCategoryID() {
        return categoryID;
    }

    public int getYear() {
        return year;
    }

    public int getListPrice() {
        return listPrice;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setBrandID(String brandID) {
        this.brandID = brandID;
    }

    public void setCategoryID(String categoryID) {
        this.categoryID = categoryID;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public void setListPrice(int listPrice) {
        this.listPrice = listPrice;
    }
    
    @Override         
    public String toString() {
        return id + ", " + name + ", " + brandID + ", " + categoryID + ", " + year + ", " + listPrice;
    }
}
