package bikestoresmanagementsystem;

import java.util.Scanner;
import java.util.ArrayList;
import static BikeStoresManagementSystem.Tool.int_menu;
public class ProductManagement {
    public static void main(String[] args) {
        ProductList productL = new ProductList();
        Scanner scanner = new Scanner(System.in);
        
        String bFile = "C:\\Users\\ACER\\Desktop\\Java\\BikeStoresManagementSystem\\src\\Brand.txt";
        String cFile = "C:\\Users\\ACER\\Desktop\\Java\\BikeStoresManagementSystem\\src\\Category.txt";
        String pFile = "C:\\Users\\ACER\\Desktop\\Java\\BikeStoresManagementSystem\\src\\Product.txt";
        
        productL.addToBrand(bFile);
        productL.addToCategory(cFile);
        //making menu
        ArrayList<String> options = new ArrayList<>();
        options.add("Add a product");
        options.add("Search product by name");
        options.add("Update product");
        options.add("Delete product");
        options.add("Save product to file");
        options.add("Print list from the file");
        options.add("Exit");
        int choice;
        System.out.println("====================================");
        System.out.println("====================================");
        System.out.println("=== Bike Store Management System ===");
        System.out.println("====================================");
        System.out.println("====================================");
        
        do {
            choice = int_menu(options);
            System.out.println();
            
            switch(choice) {
                case 1:
                    productL.CreateProduct();
                    break;
                case 2:
                    productL.searchNameProduct();
                    break;
                case 3:
                    productL.updateProductInf();
                    break;
                case 4:
                    productL.deleteProductInf();
                    break;
                case 5:
                    productL.saveToFile(pFile);
                    break;
                case 6:
                    productL.printProducts(pFile);
                    break;
                default:
                    System.out.println("Exiting...");
            }
        } while (choice > 0 && choice <= 6);    
    }
}
