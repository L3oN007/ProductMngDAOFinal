package ui;

import dao.ProductDao;
import dao.ProductDaoImpl;
import tools.MyTool;

public class ProductManager {
    public static void main(String[] args) {
        ProductDao pList = new ProductDaoImpl();
        boolean confirm = false;
        boolean changed = false;
        boolean toMainMenu;
        int choice;
        Menu menu = new Menu("Managing Products: ");
        menu.addNewOption("   1-Print new product");
        menu.addNewOption("   2-Create a Product");
        menu.addNewOption("   3-Check exits Product");
        menu.addNewOption("   4-Search Product information by Name");
        menu.addNewOption("   5-Update Product");
        menu.addNewOption("   6-Save Products to file");
        menu.addNewOption("   7-Print list Products from the file");
        menu.addNewOption("   Others- Quit");
        do {
            menu.printMenu();
            choice = menu.getChoice();
            switch (choice) {
                case 1:
                    pList.printNewProduct();
                    break;
                case 2:
                    do {
                        pList.createProduct();
                        changed = true;
                        toMainMenu = MyTool.confirmYesNo("Do you want to continue create Product(Y/N): ");
                    } while (toMainMenu);
                    break;
                case 3:
                    do {
                        pList.checkExitProduct();
                        toMainMenu = MyTool.confirmYesNo("Do you want to continue check exist Product(Y/N): ");
                    } while (toMainMenu);
                    break;
                case 4:
                    do {
                        pList.searchProductByName();
                        toMainMenu = MyTool.confirmYesNo("Do you want to continue search Product by name(Y/N): ");
                    } while (toMainMenu);
                    break;
                case 5:
                    int choiceSubMenu;
                    Boolean response;
                    boolean quit = true;
                    System.out.println("--------------------------------------------------------");
                    System.out.println("");
                    do {
                        System.out.println("5-Update Product");
                        System.out.println("   5.1 Update Product");
                        System.out.println("   5.2 Delete Product");
                        choiceSubMenu = MyTool.getChoiceForSubMenu("Choose 5.1 or 5.2: ");
                        switch (choiceSubMenu) {
                            case 1:
                                pList.updateProduct();
                                response = MyTool.confirmYesNo("You continue(Y/N): ");
                                if (response == true) {
                                    quit = true;
                                } else {
                                    quit = false;
                                }
                                changed = true;
                                break;
                            case 2:
                                pList.deleteProduct();
                                response = MyTool.confirmYesNo("You continue(Y/N): ");
                                if (response == true) {
                                    quit = true;
                                } else {
                                    quit = false;
                                }
                                changed = true;
                                break;
                            default:
                                quit = false;
                        }
                    } while (quit);
                    changed = true;
                    System.out.println("--------------------------------------------------------");
                    break;
                case 6:
                    pList.saveProductToFile();
                    changed = false;
                    break;
                case 7:
                    pList.printProductFromFile();
                    break;
                default:
                    if (changed == true) {
                        confirm = MyTool.confirmYesNo("Do you want to save (Y/N): ");
                        if (confirm == true) {
                            pList.saveProductToFile();
                            System.out.println("Bye.Have a nice day!");
                        } else {
                            System.out.println("Bye.Have a nice day!");
                        }
                    }
            }
        } while (choice < 8 && choice > 0);
    }
}
