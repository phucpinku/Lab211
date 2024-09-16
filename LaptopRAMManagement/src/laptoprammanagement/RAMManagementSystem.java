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
public class RAMManagementSystem {
    public static void main(String[] args) {
        RAMList ramList = new RAMList();
        String moduleFile = "D:\\Github\\Lab211\\LaptopRAMManagement\\src\\laptoprammanagement\\RAM_data.txt";

        ramList.loadRAMFromFile(moduleFile);
        ramList.list();
    }
}