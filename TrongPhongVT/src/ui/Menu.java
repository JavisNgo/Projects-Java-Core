/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ui;

import java.util.ArrayList;
import utils.Validation;

/**
 *
 * @author trien
 */
public class Menu {
    private String menuTitle;
    private ArrayList<String> optionList = new ArrayList();

    public Menu(String menuTitle) {
        this.menuTitle = menuTitle;
    }
        
    public void addNewOption(String newOption) {
        optionList.add(newOption);        
    }

    public void printMenu() {
        System.out.println("\nWelcome To " + menuTitle); 
        for (int i = 0; i < optionList.size(); i++) {
            System.out.println(optionList.get(i));
        }
    }
    
    public int getChoice() {
        int maxOption = optionList.size();
        String inputMsg = "Choose [1.." + maxOption + "]: ";
        String errorMsg = "You are required to choose the option 1.." + maxOption;
        return Validation.getAnInteger(inputMsg, errorMsg, 1, maxOption);
    }
}