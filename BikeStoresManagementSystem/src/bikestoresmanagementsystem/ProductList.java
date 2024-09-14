package bikestoresmanagementsystem;

import java.io.*;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Scanner;
import static BikeStoresManagementSystem.Tool.*;

public class ProductList extends ArrayList<Product> {
    ArrayList<Brand> bList;
    ArrayList<Category> cList;
    ArrayList<Product> pList;
    ArrayList<Product> pfList;
    
    public ProductList() {
        bList = new ArrayList<>();
        cList = new ArrayList<>();
        pList = new ArrayList<>();
        pfList = new ArrayList<>();
    }
    
    public boolean checkUniqueID(String id) {
        for (Product x : pList) {
            if(x.getId().equals(id)) {
                return false;
            }
        }
        return true;
    }
    
    public boolean checkBrandID(String brand) {
        for (Brand x : bList) {
            if(x.getBrandID().equals(brand)) {
                return true;
            }
        }
        return false;
    }
    
    public boolean checkCategoryID(String category) {
        for (Category x : cList) {
            if(x.getCategoryID().equals(category)) {
                return true;
            }
        }
        return false;
    }
    
    public void addToBrand(String filename) {
        try (Scanner s = new Scanner(new File(filename))) {
            while (s.hasNext()) {
                String[] data = s.nextLine().split(", ");
                if (data.length < 3) {
                    continue;
                }
                Brand brand = new Brand();
                brand.setBrandID(data[0]);
                brand.setBrandName(data[1]);
                brand.setBrandCountry(data[2]);
                bList.add(brand);
            }
        } catch (FileNotFoundException e) {
            System.out.println("Not found" + filename);
            throw new RuntimeException(e);
        }
    }
    
    public void addToCategory(String filename) {
        try (Scanner s = new Scanner(new File(filename))) {
            while(s.hasNext()) {
                String[] data = s.nextLine().split(", ");
                if (data.length < 2) {
                    continue;
                }
                Category category = new Category(data[0], data[1]);
                cList.add(category);
            }
        } catch (FileNotFoundException e) {
            System.out.println("Not found" + filename);
            throw new RuntimeException(e);
        }
    }
    
    public void CreateProduct() {
        String id;
        String name;
        String brandID;
        String categoryID;
        int year;
        int listPrice;
        do {
            id = generateCodeFromStr();
            if(!checkUniqueID(id)) {
                System.out.println("Please enter valid UID ");
            }
        } while (!checkUniqueID(id));
        Product product = new Product(id);
        
        do {
            name = readStr("Enter name: ");
            if (name.isEmpty()) {
                System.out.println("Enter valid name ");
            }
        } while (name.isEmpty());
        product.setName(name);
        
        do {
            brandID = readStr("Enter brandID: ").toUpperCase();
            if (!checkBrandID(brandID)) {
                System.out.println("Enter valid ID");
            }
        } while (!checkBrandID(brandID));
        product.setBrandID(brandID);
        
        do {
            categoryID = readStr("Enter categoryID: ").toUpperCase();
            if (!checkCategoryID(categoryID)) {
                System.out.println("Enter valid ID");
            }
        } while (!checkCategoryID(categoryID));
        product.setCategoryID(categoryID);
        
        do {
            year = readInt("Enter year: ");
            if(year < 1000 || year > 9999) {
                System.out.println("Enter valid year");
            } 
        } while(year < 1000 || year > 9999);
        product.setYear(year);
        
        do {
            listPrice = readInt("Enter price: ");
            if (listPrice < 0) {
                System.out.println("Enter valid price: ");
            }
        } while (listPrice < 0);
        product.setListPrice(listPrice);
        pList.add(product);
    }
    
    public void searchNameProduct() {
        String searchName = readStr("Enter name for search: ").toLowerCase();
        ArrayList<Product> search = new ArrayList<>();
        for (Product x : pList) {
            if (x.getName().toLowerCase().contains(searchName)) {
                search.add(x);
            }
        }
        if(search.isEmpty()) {
            System.out.println("Have no any Product");
            return;
        }
        search.sort(Comparator.comparing(Product::getYear));
        for (Product product : search) {
            System.out.println(product);
        }
    }
    
    public void updateProductInf() {
        String id = generateCodeFromStr();
        if (checkUniqueID(id)) {
            System.out.println("Product does not exist");
            return;
        }   
        // Looking for product
        int index = -1;
        for (int i = 0; i < pList.size(); i++) {
            if(pList.get(i).getId().equals(id)) {
                index = i;
                break;
            }
        }
        
        if (index == -1) {
            System.out.println("Product not found.");
            return;
        }
        
        String name = readStr("Enter name: ");
        if(!name.isEmpty()) {
            pList.get(index).setName(name);
            System.out.println("Success");
        }
        
        String brandID = readStr("Enter brandID: ").toUpperCase();
        if(!brandID.isEmpty()) {
            if (checkBrandID(brandID)) {
                pList.get(index).setBrandID(brandID);
                System.out.println("Success");
            } else {
                System.out.println("Invalid BrandID");
                System.out.println("Failled");
            }
        }
        String categoryID = readStr("Enter categoryID: ").toUpperCase();
        if(!categoryID.isEmpty()) {
            if(checkCategoryID(categoryID)) {
                pList.get(index).setCategoryID(categoryID);
                System.out.println("Success");
            } else {
                System.out.println("Invalid categoryID");
                System.out.println("Failled");
            }
        }
        String year = readStr("Enter year: ");
        if (!year.isEmpty()) {
            int years = Integer.parseInt(year);
            if (years > 1000 && years <= 9999) {
                pList.get(index).setYear(years);
                System.out.println("Success");
            } else {
                System.out.println("Invalid year");
                System.out.println("Failled");
            }
        }
        String price = readStr("Enter price: ");
        if(!price.isEmpty()) {
            int prices = Integer.parseInt(price);
            if(prices > 0) {
                pList.get(index).setListPrice(prices);
                System.out.println("Success");
            } else {
                System.out.println("Invalid price");
                System.out.println("Failled");
            }
        }
    }
    
    public void deleteProductInf() {
        String id = readStr("Enter product ID:");
        if (checkUniqueID(id)) {
            System.out.println("Product does not exist");
            return;
        }
        
        int index = -1;
        for (int i = 0; i < pList.size(); i++) {
            if (pList.get(i).getId().equals(id)) {
                index = i;
                break;
            }
        }
        if (index == -1) {
            System.out.println("Product not found.");
            return;
        }
        
        String choice = readStr("Are you sure? (Y/N)").toLowerCase();
        if (choice.equalsIgnoreCase("y")) {
            pList.remove(index);
            System.out.println("Success");
        } else if (choice.equalsIgnoreCase("n")) {
            System.out.println("Failled");
        } else {
            System.out.println("Invalid input");
            System.out.println("Failled");
        }
    }
    
    public void saveToFile(String filename) {
        if (pList.isEmpty()) {
            System.out.println("List is empty");
            return;
        }
        
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename))) {
            for (Product product : pList) {
                writer.write(product.toString());
                writer.newLine();
            }
            System.out.println("Success");
        } catch (IOException e) {
            System.out.println("Error save to " + filename);
        }
    }

    public void loadProduct(String filename) {
        pfList = new ArrayList<>();
        try (Scanner s = new Scanner(new File(filename))) {
            while (s.hasNext()) {
                String[] data = s.nextLine().split(", ");
                if (data.length < 6) {
                    continue;
                }
                Product product = new Product(data[0]);
                product.setName(data[1]);
                product.setBrandID(data[2]);
                product.setCategoryID(data[3]);
                product.setYear(Integer.parseInt(data[4]));
                product.setListPrice(Integer.parseInt(data[5]));
                pfList.add(product);
            }
        } catch (FileNotFoundException e) {
            System.out.println("Not found" + filename);
            throw new RuntimeException(e);
        }
    }
    
    public void printProducts(String filename) {
        loadProduct(filename);
        pfList.sort(Comparator.comparingDouble(Product::getListPrice).reversed().thenComparing(Product::getName));
        
        for (Product product : pfList) {
            String categoryName = "";
            String brandName = "";
            for (Category x : cList) {
                if (x.getCategoryID().equals(product.getCategoryID())) {
                    categoryName = x.getCategoryName();
                }
            }
            for (Brand x : bList) {
                if (x.getBrandID().equals(product.getBrandID())) {
                    brandName = x.getBrandName();
                }
            }
            System.out.println(product.getId() + ", " + product.getName() + ", " + brandName + ", " + categoryName + ", " + product.getYear() + ", " + product.getListPrice());
            
        }
    }  
    
    public void list() {
       bList.forEach(System.out::println);
       cList.forEach(System.out::println);
    }
}
