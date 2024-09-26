package laptoprammanagement;

import java.util.*;
import static laptoprammanagement.myTool.*;

public class RAMManagementSystem {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int choice;
        RAMList ramList = new RAMList();
        String moduleFile = "D:\\Github\\Lab211\\LaptopRAMManagement\\src\\laptoprammanagement\\RAM_data.txt";
        String RAMModules = "D:\\Github\\Lab211\\LaptopRAMManagement\\src\\laptoprammanagement\\RAMModules.dat";
        ramList.loadRAMFromFile(moduleFile);
        ramList.loadFromFile(RAMModules);

        //create menu
        System.out.println("=====================================");
        System.out.println("=====================================");
        System.out.println("======= RAM Management System =======");
        System.out.println("=====================================");
        System.out.println("=====================================");

        do {
            System.out.println("1. Add item");
            System.out.println("2. Search item");
            System.out.println("3. Update item");
            System.out.println("4. Delete item");
            System.out.println("5. Show all item");
            System.out.println("6. Store data to file");
            System.out.println("7. Exit..");
            System.out.println("Enter choice: ");
            choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {
                case 1:
                    ramList.createProduct();
                    System.out.println("Exiting...");
                    break;
                case 2:
                    if (ramList.rList.isEmpty()) {
                        System.out.println("List is empty");
                        break;
                    }
                    searchMenu(ramList, sc);
                    System.out.println("Exiting...");
                    break;
                case 3:
                    ramList.updateProduct();
                    System.out.println("Exiting...");
                    break;
                case 4:
                    ramList.deleteProduct();
                    System.out.println("Exiting...");
                    break;
                case 5:
                    ramList.displayAll();
                    System.out.println("Exiting...");
                    break;
                case 6:
                    ramList.saveToFile(RAMModules);
                    break;
                case 7:
                    System.out.println("Quitting program!!");
                    break;
                default:
                    System.out.println("Enter valid option");
            }
        } while (choice != 7);

    }
}
