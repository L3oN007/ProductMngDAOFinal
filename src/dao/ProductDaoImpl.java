package dao;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.StringTokenizer;

import model.Product;
import tools.MyTool;

public class ProductDaoImpl implements ProductDao {
    private String dataFile = "Product.txt";
    boolean changed = false;
    private ArrayList<Product> products;
    private ArrayList<Product> runTimeList = new ArrayList<>();

    public ProductDaoImpl() {
        products = new ArrayList<>();
        loadProductFromFile();
    }

    @Override
    public void loadProductFromFile() {
        try {
            FileReader fr = new FileReader(dataFile);
            BufferedReader br = new BufferedReader(fr);
            String info;
            while ((info = br.readLine()) != null) {
                StringTokenizer st = new StringTokenizer(info, ",");
                String productID = st.nextToken();
                String productName = st.nextToken();
                Double unitPrice = Double.parseDouble(st.nextToken());
                int quantity = Integer.parseInt(st.nextToken());
                String status = st.nextToken();
                products.add(new Product(productID, productName, unitPrice, quantity, status));
            }
            br.close();
            fr.close();
        } catch (Exception e) {
            // System.out.println(e);
        }
    }

    @Override
    public int checkName(String name, ArrayList<Product> list) {
        if (list.isEmpty()) {
            return -1;
        }
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).getProductName().equalsIgnoreCase(name)) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public int checkID(String id, ArrayList<Product> list) {
        if (list.isEmpty()) {
            return -1;
        }
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).getProductID().equalsIgnoreCase(id)) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public void createProduct() {
        int indexID;
        int indexName;
        String productID;
        String productName;
        System.out.println("Enter new product information:");
        do {
            productID = MyTool.getString("Product ID: ", "Not blank or empty.").toUpperCase();
            indexID = checkID(productID, products);
            if (indexID >= 0) {
                System.out.println("ID is duplicated.Input again.");
            }
        } while (indexID >= 0);
        do {
            productName = MyTool.getStringWithRegex("Product Name: ",
                    "A Product name must be at least five characters and no spaces.", "^[a-zA-Z0-9_.-]{5,}$")
                    .toLowerCase();
            indexName = checkName(productName, products);
            if (indexName >= 0) {
                System.out.println("Product Name is duplicated.Input again.");
            }
        } while (indexName >= 0);
        double unitPrice = MyTool.checkInputDoubleLimit("Product Unit Price: ", 0, 10000);
        int quantity = MyTool.checkInputIntLimit("Product Quantity: ", 0, 1000);
        String status = MyTool.confirmAvailable();
        // products.add(new Product(productID, productName, unitPrice, quantity,
        // status));
        runTimeList.add(new Product(productID, productName, unitPrice, quantity, status));
        System.out.println("New dealer added successfully!");
        System.out.println("--------------------------------------------------------");
        changed = true;
    }

    @Override
    public void updateProduct() {
        String productID = MyTool.getString("Enter ID product to update: ", "Not blank or empty.");
        // Check ID
        int index = checkID(productID, runTimeList);
        if (index >= 0) {
            // ! update name
            int indexName;
            String newName;
            do {
                newName = MyTool.getStringWithRegex("Enter new product name to update: ",
                        "A Product name must be at least five characters and no spaces.", "^[a-zA-Z0-9_.-]{5,}$|^$");
                indexName = checkName(newName, runTimeList);
                if (indexName >= 0) {
                    System.out.println("Product name is duplicated.Input again.");
                }
            } while (indexName >= 0);
            if (!newName.isEmpty()) {
                runTimeList.get(index).setProductName(newName);
                changed = true;
            }
            // ! update unitPrice
            String newUnitPrice = MyTool.getStringWithRegex("Enter new unit price to update: ",
                    "Only number or null if not update!", "(^[0-9]+$|^$)");
            if (!newUnitPrice.isEmpty()) {
                double newUnitPrice2 = Double.parseDouble(newUnitPrice);
                runTimeList.get(index).setUnitPrice(newUnitPrice2);
                changed = true;
            }
            // ! update quantity
            String newQuantity = MyTool.getStringWithRegex("Enter new quantity to update: ",
                    "Only number or null if not update!", "(^[0-9]+$|^$)");
            if (!newQuantity.isEmpty()) {
                int newQuantity2 = Integer.parseInt(newQuantity);
                runTimeList.get(index).setQuantity(newQuantity2);
                changed = true;
            }
            // ! update status
            String newStatus = MyTool.confirmAvailableForUpdate();
            if (!newStatus.isEmpty()) {
                runTimeList.get(index).setStatus(newStatus);
                changed = true;
            }
            if (changed == true) {
                System.out.println("The Product's information has been updated.");
            } else {
                System.out.println("The Product's information not changed.");
            }
        } else {
            System.out.println("Product with ID " + productID.toUpperCase() + " does not exist!");
        }
    }

    @Override
    public void deleteProduct() {
        int index;
        String inputID = MyTool.getString("Enter ID product to delete: ", "Not blank or empty.").toUpperCase();
        index = checkID(inputID, runTimeList);
        if (index >= 0) {
            runTimeList.remove(index);
            System.out.println("Delete successfully!!!");
            changed = true;
        } else {
            System.out.println("Product with ID " + inputID.toUpperCase() + " does not exist!");
        }
    }

    @Override
    public void checkExitProduct() {
        int index;
        String inputName = MyTool.getStringWithRegex("Enter product name to check: ",
                "A Product name must be at least five characters and no spaces.", "^[a-zA-Z0-9_.-]{5,}$");
        index = checkName(inputName, products);
        if (index >= 0) {
            System.out.println("Exist Product.");
            System.out.println("--------------------------------------------------------");
        } else {
            System.out.println("No Product Found!");
            System.out.println("--------------------------------------------------------");
        }
    }

    @Override
    public void saveProductToFile() {
        if (changed) {
            try {
                File f = new File(dataFile);
                FileWriter fw = new FileWriter(f);
                PrintWriter pw = new PrintWriter(fw);
                for (int i = 0; i < products.size(); i++) {
                    pw.println(products.get(i).toString());
                }
                for (int i = 0; i < runTimeList.size(); i++) {
                    pw.println(runTimeList.get(i).toString());
                }
                pw.close();
                fw.close();
                products.removeAll(runTimeList);
                products.addAll(runTimeList);
                runTimeList.removeAll(products);
            } catch (Exception e) {
                System.out.println(e);
            }
            changed = false;
            System.out.println("Save to file successfully!!");
        }
    }

    @Override
    public void printProductFromFile() {
        if (products.isEmpty()) {
            System.out.println("List empty.Nothing to print.");
            System.out.println("--------------------------------------------------------");

        } else {
            System.out.println(
                    "--------------------------- LIST OF ALL PRODUCT --------------------------");
            Collections.sort(products, new Comparator<Product>() {
                @Override
                public int compare(Product o1, Product o2) {
                    int descQuantity = Integer.compare(o2.getQuantity(), o1.getQuantity());
                    int ascUnitPrice = Double.compare(o1.getUnitPrice(), o2.getUnitPrice());
                    if (o2.getQuantity() != o1.getQuantity()) {
                        return descQuantity;
                    } else {
                        return ascUnitPrice;
                    }
                }
            });
            System.out.println("+----------+--------------------+--------------+----------+--------------+");
            System.out.println("|    ID    |        NAME        |  UNIT PRICE  | QUANTITY |    STATUS    |");
            System.out.println("+----------+--------------------+--------------+----------+--------------+");
            for (int i = 0; i < products.size(); i++) {
                products.get(i).output();
            }
            System.out.println("+----------+--------------------+--------------+----------+--------------+");
        }
    }

    @Override
    public void printNewProduct() {
        if (runTimeList.isEmpty()) {
            System.out.println("List empty.Nothing to print.");
            System.out.println("--------------------------------------------------------");
        } else {
            System.out.println(
                    "-------------------------- LIST OF NEW PRODUCTS --------------------------");
            Collections.sort(runTimeList);
            System.out.println("+----------+--------------------+--------------+----------+--------------+");
            System.out.println("|    ID    |        NAME        |  UNIT PRICE  | QUANTITY |    STATUS    |");
            System.out.println("+----------+--------------------+--------------+----------+--------------+");
            for (int i = 0; i < runTimeList.size(); i++) {
                runTimeList.get(i).output();
            }
            System.out.println("+----------+--------------------+--------------+----------+--------------+");
        }
    }

    @Override
    public void searchProductByName() {
        boolean changed = false;
        String inputName = MyTool.getString("Enter product name you want to find: ", "Not blank or empty.")
                .toLowerCase();
        for (int i = 0; i < products.size(); i++) {
            if (products.get(i).getProductName().toLowerCase().contains(inputName)) {
                changed = true;
            }
        }
        if (changed == true) {
            Collections.sort(products, new Comparator<Product>() {
                @Override
                public int compare(Product o1, Product o2) {
                    return o1.getProductName().compareTo(o2.getProductName());
                }
            });
            System.out.println("+----------+--------------------+--------------+----------+--------------+");
            System.out.println("|    ID    |        NAME        |  UNIT PRICE  | QUANTITY |    STATUS    |");
            System.out.println("+----------+--------------------+--------------+----------+--------------+");
            for (int i = 0; i < products.size(); i++) {
                if (products.get(i).getProductName().contains(inputName)) {
                    products.get(i).output();
                }
            }
            System.out.println("+----------+--------------------+--------------+----------+--------------+");
        } else {
            System.out.println("Have no any Product.");
        }
    }

}
