package laptoprammanagement;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.io.BufferedReader;
import java.util.*;

public class RAMList extends ArrayList<RAMItems> {

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
            System.out.println("Not found: " + filename);
        } catch (IOException e) {
            System.out.println("Error reading" + filename);
        }
    }
    
    
    
    public void list() {
        module.forEach((System.out::println));
        rList.forEach(System.out::println);
    }
    
    
    
    
    private boolean isUniqueCode(String code) {
        for (RAMItems x : rList) {
            if(x.getCode().equals(code)) {
                return false;
            }
        }
        return true;
    }
    
    private boolean isValidType(String type) {
        for (RAMModule x : module) {
            if (x.getType().equals(type)) {
                return true;
            }
        }
        return false;
    }
    
    private boolean isValidBus(String type, String bus) {
        if(!isValidType(type)) {
            System.out.println("Invalid RAM module type");
            return false;
        }
        //get index for checking
        int index = -1;
        for(int i = 0; i < module.size(); i++) {
            if(module.get(i).getType().equals(type)) {
                index = i;
            }
        }
        if (index == -1) {
            System.out.println("Not found but pass the check");
        }
        
        for (String x : module.get(index).getBus()) {
            if(x.equalsIgnoreCase(bus)) {
                return true;
            }
        }
        return false;
    }
    
    
    
    private void displayType() {
        System.out.println("Available RAM type: ");
        for(RAMModule x : module) {
            System.out.println(x.getType());
        }
    }
    
    private void displayBusType(String type) {
        System.out.println("Avail Bus speed for " + type + ":");
        for (RAMModule x : module) {
            if (x.getType().equals(type)) {
                System.out.println(Arrays.toString(x.getBus()).replaceAll("MHz", ""));
            }
        }
    }
    
    

}
