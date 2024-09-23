package laptoprammanagement;

import java.io.*;
import java.util.ArrayList;
import java.io.BufferedReader;
import java.util.*;
import java.time.YearMonth;
import static laptoprammanagement.myTool.*;

public class RAMList extends ArrayList<RAMItems> {

    Scanner sc = new Scanner(System.in);
    ArrayList<RAMItems> rList;
    ArrayList<RAMModule> module;

    public RAMList() {
        rList = new ArrayList<>();
        module = new ArrayList<>();
    }

//     Load RAMModule from file
    public void loadRAMFromFile(String filename) {
        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] data = line.split("-");
                if (data.length < 2) {
                    System.out.println("Invalid format");
                    continue;
                }

                String type = data[0];
                String[] data2 = data[1].replaceAll("[\\[\\]]", "").split(", ");

                RAMModule ramModule = new RAMModule();
                ramModule.setType(data[0]);
                ramModule.setBus(data2);
                module.add(ramModule);

            }
        } catch (FileNotFoundException e) {
            System.out.println("Not found " + filename);
        } catch (IOException e) {
            System.out.println("Error reading " + filename);
        }
    }

    // List for checking purpose
    public void list() {
        module.forEach((System.out::println));
        rList.forEach(System.out::println);
    }

    // Check unique code 
    private boolean isUniqueCode(String code) {
        for (RAMItems x : rList) {
            if (x.getCode().equals(code)) {
                return false;
            }
        }
        return true;
    }

    //check valid quantity
    private int validQuantity() {
        int quantity = 0;
        boolean valid = false;

        do {
            System.out.println("Enter quantity ");
            try {
                quantity = sc.nextInt();
                sc.nextLine();  // Consume the newline character
                if (quantity <= 0) {
                    System.out.println("Please enter a valid quantity ");
                } else {
                    valid = true;  // Input is valid, exit loop
                }
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a number for quantity.");
                sc.nextLine();  // Clear the invalid input
            }
        } while (!valid);

        return quantity;
    }

    // Check if type is valid
    private boolean isValidType(String type) {
        for (RAMModule x : module) {
            if (x.getType().equals(type)) {
                return true;
            }
        }
        return false;
    }

    // check if bus is valid
    private boolean isValidBus(String type, String bus) {
        if (!isValidType(type)) {
            System.out.println("Invalid RAM module type ");
            return false;
        }
        //get index for checking
        int index = -1;
        for (int i = 0; i < module.size(); i++) {
            if (module.get(i).getType().equals(type)) {
                index = i;
            }
        }
        if (index == -1) {
            System.out.println("Not found but pass the check ");
            return false;
        }
//        System.out.println("Checking BUs " + bus);   //debug
        for (String x : module.get(index).getBus()) {
//            System.out.println("Avail bus " + x);    //debug
            if (x.equalsIgnoreCase(bus)) {
                return true;
            }
        }
        return false;
    }

    //Display type for user
    private void displayType() {
        System.out.println("Available RAM type ");
        for (RAMModule x : module) {
            System.out.println(x.getType());
        }
    }

    //Display bus type to choose
    private void displayBusType(String type) {
        System.out.println("Avail Bus speed for " + type + ":");
        for (RAMModule x : module) {
            if (x.getType().equals(type)) {
                System.out.println(Arrays.toString(x.getBus()).replaceAll("MHZ", ""));
            }
        }
    }

    // Get type from user
    private String getType() {
        displayType();
        String type;
        do {
            type = readStr("Enter RAM module TYPE ").toUpperCase();
            if (!isValidType(type)) {
                System.out.println("Please enter valid type !!");
            }
        } while (!isValidType(type));
        return type;
    }

    //Get Bus speed from user
    private String getBus(String type) {
        displayBusType(type);
        String bus;
        do {
            int num = readInt("Enter BUS speed", "BUS speed");
            bus = num + "MHZ";
            if (!isValidBus(type, bus)) {
                System.out.println("Please enter Valid BUS speed");
            }

        } while (!isValidBus(type, bus));
        return bus;
    }

    public void createProduct() {
        String code;
        String type;
        String bus;
        String brand;
        int quantity;
        YearMonth product_year;
        boolean active;

        type = getType();
        do {
            code = generateCodeFromStr(type);
            if (!isUniqueCode(code)) {
                System.out.println("Please enter unique code");
            }
        } while (!isUniqueCode(code));
        RAMItems item = new RAMItems(type, code);

        item.setBus(getBus(type));

        do {
            brand = readStr("Enter brand: ");
            if (brand.isEmpty()) {
                System.out.println("Please enter brand");
            }
        } while (brand.isEmpty());
        item.setBrand(brand);

        quantity = validQuantity();
        item.setQuantity(quantity);

        int month, year;
        do {
            System.out.println("Enter month");
            month = sc.nextInt();
            sc.nextLine();
            System.out.println("Enter year");
            year = sc.nextInt();
            sc.nextLine();
            if (month < 1 || month > 12 || year < 0 || year > 9999) {
                System.out.println("Please enter valid date ");
            }
        } while (month < 1 || month > 12 || year < 0 || year > 9999);
        YearMonth prod_date = YearMonth.of(year, month);
        item.setProduct_year(prod_date);
        item.setCheckActive(true);
        rList.add(item);

        System.out.println("Added !!");
    }

    public void searchByType() {
        String type = getType();
        for (RAMItems x : rList) {
            if (x.getType().equals(type)) {
                System.out.println(x.getCode() + ", " + type + ", " + x.getProduct_year() + ", " + x.getQuantity());
            }
        }
    }

    public void searchByBus() {
        List<RAMItems> search = new ArrayList<>();
        String busSpeed;
        
        do {
            busSpeed = readStr("Enter bus speed");
            if (!busSpeed.matches("\\d+")) { // Matches only digits
                System.out.println("Invalid bus speed. Please enter a valid number");
            }
        } while (!busSpeed.matches("\\d+"));
        int busSpeedInt = Integer.parseInt(busSpeed);
        String searchName = busSpeedInt + "MHZ";
        for (RAMItems x : rList) {
            if (x.getBus().equalsIgnoreCase(searchName)) {
                search.add(x);
            }
        }
        if (search.isEmpty()) {
            System.out.println("No RAM modules found for bus speed " + searchName);
            return;
        }
        System.out.println("RAM modules matching BUS speed");
        for (RAMItems found : search) {
            System.out.println(found.getCode() + ", " + found.getBus() + ", " + found.getProduct_year() + ", " + found.getQuantity());
        }
    }

    public void searchByBrand() {
        String brand = readStr("Enter brand ");
        for (RAMItems x : rList) {
            if (x.getBrand().equalsIgnoreCase(brand)) {
                System.out.println(x.getCode() + ", " + x.getBrand() + ", " + x.getProduct_year() + ", " + x.getQuantity());
            }
        }
    }

    public void updateProduct() {
        String code = readStr("Enter code ");
        RAMItems itemUpdate = null;

        //Find the item with the code
        for (RAMItems x : rList) {
            System.out.println(x.getCode());
            if (x.getCode().equalsIgnoreCase(code)) {
                itemUpdate = x;
                break;
            }
        }
        //Check if code existed
        if (itemUpdate == null) {
            System.out.println("Code " + code + " not found");
            return;
        }

        //Update type
        displayType();
        String newType;
        do {
            newType = readStr("Enter new Type ").toUpperCase();
            if (newType.isEmpty()) {
                break;
            }
            if (!isValidType(newType)) {
                System.out.println("Please enter valid type");
            }
        } while (!isValidType(newType));
        if (!newType.isEmpty() && isValidType(newType)) {
            itemUpdate.setType(newType);
            System.out.println("Success");
        }

        //Update bus
        displayBusType(itemUpdate.getType());
        String newBus;
        do {
            newBus = readStr("Enter new bus ");
            if (newBus.isEmpty()) {
                break;
            }
            newBus = newBus + "MHZ";
            if (!isValidBus(itemUpdate.getType(), newBus)) {
                System.out.println("Please enter valid bus speed");
            }
        } while (!isValidBus(itemUpdate.getType(), newBus));
        if (!newBus.isEmpty() && isValidBus(itemUpdate.getType(), newBus)) {
            itemUpdate.setBus(newBus);
            System.out.println("Success");
        }

        //Update brand
        String newBrand = readStr("Enter new Brand ");
        if (!newBrand.isEmpty()) {
            itemUpdate.setBrand(newBrand);
            System.out.println("Success");
        }

        //Update quantity
        String newQuantityStr;
        int intQuantity = -1;
        do {
            newQuantityStr = readStr("Enter new Quantity ");
            if (newQuantityStr.isEmpty()) {
                break;
            }
            intQuantity = readIntFromStr(newQuantityStr);
            if (intQuantity <= 0) {
                System.out.println("Please enter valid quantity");
            }
        } while (intQuantity <= 0);
        if (!newQuantityStr.isEmpty()) {
            itemUpdate.setQuantity(intQuantity);
            System.out.println("Sucess");
        }

        //Update active
        int choice = -1;
        if (!itemUpdate.isCheckActive()) {
            System.out.println("Do you want to activate " + itemUpdate.getCode() + " ?");
            choice = int_menu("Yes", "No");
            if (choice == 1) {
                itemUpdate.setCheckActive(true);
                System.out.println("Sucess");
            }
        }
    }

    public void deleteProduct() {
        String code = readStr("Enter RAM code to delete: ");
        RAMItems itemDelete = null;

        // Find the item with the code
        for (RAMItems item : rList) {
            if (item.getCode().equalsIgnoreCase(code) && item.isCheckActive()) {
                itemDelete = item;
                break;
            }
        }

        // Check if code exist
        if (itemDelete == null) {
            System.out.println("Code " + code + " not found.");
            return;
        }

        // Confirm deletion
        String confirmation = readStr("Are you sure you want to delete this item? (yes/no): ");
        if (!confirmation.equalsIgnoreCase("yes")) {
            System.out.println("Deletion cancelled.");
            return;
        } else {
            itemDelete.setCheckActive(false);
            System.out.println("RAM item marked as inactive.");
        }
    }

    public void displayAll() {
        // Store active items to list
        List<RAMItems> activeItems = new ArrayList<>();
        for (RAMItems x : rList) {
            if (x.isCheckActive()) {
                activeItems.add(x);
            }
        }

        //Sort items by type, bus, brand
        activeItems.sort(Comparator.comparing(RAMItems::getType).thenComparing(RAMItems::getBus).thenComparing(RAMItems::getBrand));

        for (RAMItems x : activeItems) {
            System.out.println(x);
        }
    }

    public void saveToFile(String filename) {
        //Add activeItems to List 
        List<RAMItems> activeItems = new ArrayList<>();
        for (RAMItems x : rList) {
            if (x.isCheckActive()) {
                activeItems.add(x);
            }
        }
        try (ObjectOutputStream output = new ObjectOutputStream(new FileOutputStream(filename))) {
            output.writeObject(activeItems);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            System.out.println("Saved!");
        }
    }

    public void loadFromFile(String filename) {
        try (ObjectInputStream input = new ObjectInputStream(new FileInputStream(filename))) {
            List<RAMItems> loadedItem = (List<RAMItems>) input.readObject();
            rList.clear();
            rList.addAll(loadedItem);
            System.out.println("Loaded successfully");
        } catch (IOException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

}
