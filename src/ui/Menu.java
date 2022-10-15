package ui;

import java.util.ArrayList;

import tools.MyTool;

public class Menu {
    private ArrayList<String> optionList = new ArrayList();
    private String menuTitle;

    public Menu(String menuTitle) {
        this.menuTitle = menuTitle;
    }

    public void addNewOption(String newOption) {
        optionList.add(newOption);
    }

    public void printMenu() {
        if (optionList.isEmpty()) {
            System.out.println("There is no item in the menu");
            return;
        }
        System.out.println(menuTitle);
        for (String x : optionList) {
            System.out.println(x);
        }
    }

    public int getChoice() {
        return MyTool.getInt("Choose [1.." + "7" + "]: ");
    }
}
