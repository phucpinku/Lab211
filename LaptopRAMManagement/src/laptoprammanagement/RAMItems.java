/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package laptoprammanagement;

/**
 *
 * @author ACER
 */
import java.time.YearMonth;

public class RAMItems implements java.io.Serializable {

    private String code;
    private String type;
    private String bus;
    private String brand;
    private int quantity;
    private YearMonth product_year;
    private boolean checkActive;

    public RAMItems(String type, String code, String bus, String brand, int quantity, YearMonth product_year, boolean checkActive) {
        this.type = type;
        this.code = code;
        this.bus = bus;
        this.brand = brand;
        this.quantity = quantity;
        this.product_year = product_year;
        this.checkActive = checkActive;
    }

    public RAMItems(String type, String code) {
        this.type = type;
        this.code = code;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getBus() {
        return bus;
    }

    public void setBus(String bus) {
        this.bus = bus;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public YearMonth getProduct_year() {
        return product_year;
    }

    public void setProduct_year(YearMonth product_year) {
        this.product_year = product_year;
    }

    public boolean isCheckActive() {
        return checkActive;
    }

    public void setCheckActive(boolean checkActive) {
        this.checkActive = checkActive;
    }

    @Override
    public String toString() {
        return code + ", " + type + ", " + bus + ", " + brand + ", " + quantity + ", " + product_year;
    }
}
