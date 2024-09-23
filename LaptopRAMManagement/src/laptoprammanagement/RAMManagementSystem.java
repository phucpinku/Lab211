package laptoprammanagement;

import java.util.*;


import static laptoprammanagement.myTool.*;

public class RAMManagementSystem {
    
    public static void main(String[] args) {
        int choice;
        RAMList ramList = new RAMList();
        String moduleFile = "D:\\Github\\Lab211\\LaptopRAMManagement\\src\\laptoprammanagement\\RAM_data.txt";
        String RAMModules = "D:\\Github\\Lab211\\LaptopRAMManagement\\src\\laptoprammanagement\\RAMModules.dat";
//        ramList.loadRAMFromFile(moduleFile);
//        ramList.loadFromFile(RAMModules);
        
        //create menu
        ArrayList<String> options = new ArrayList<>();
        options.add("Add item");
        options.add("Search item");
        options.add("Update item");
        options.add("Delete item");
        options.add("Show all item");
        options.add("Store data to file");
        options.add("Quit menu");
        ramList.list();
        
        System.out.println("=====================================");
        System.out.println("=====================================");
        System.out.println("======= RAM Management System =======");
        System.out.println("=====================================");
        System.out.println("=====================================");
        
        do {
            choice = int_menu(options);
            System.out.println();
            
            switch (choice) {
                case 1:
                    do {
                        ramList.createProduct();
                    } while (exitChoice("Add another", "Exit"));
                    System.out.println("Exiting...");
                   break;
                    
                case 2:
                    if (ramList.rList.isEmpty()) {
                        System.out.println("List is empty");
                        break;
                    }
                    do {
                        switch(int_menu("Search by Type", "Search by bus", "Search by brand")) {
                            case 1:
                                ramList.searchByType();
                                break;
                            case 2:
                                ramList.searchByBus();
                                break;
                            case 3:
                                ramList.searchByBrand();
                                break;
                            default:
                                System.out.println("Please enter 1 to 3");
                        }
                    } while (exitChoice("Search again", "Exit"));
                    System.out.println("Exiting...");
                    break;
                case 3:
                    do {
                        ramList.updateProduct();
                    } while (exitChoice("Update another", "Exit"));
                    System.out.println("Exiting...");
                    break;
                case 4:
                    do {
                        ramList.deleteProduct();
                    } while (exitChoice("Delete another", "Exit"));
                    break;
                case 5:
                    do {
                        ramList.displayAll();
                    } while (exitChoice("Display", "Exit"));
                    break;
                case 6:
                    do {
                        ramList.saveToFile(RAMModules);
                    } while (exitChoice("Save again", "Exit"));
                    break;
                case 7:
                    System.out.println("Quitting program!!");
                    break;
                default:
                    System.out.println("Enter valid option");
            }
        } while (choice != options.size());
        
    }
}