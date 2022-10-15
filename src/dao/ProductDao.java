package dao;

import java.util.ArrayList;

import model.Product;

public interface ProductDao {
    void loadProductFromFile();

    int checkName(String name, ArrayList<Product> list);

    int checkID(String id, ArrayList<Product> list);

    void createProduct();

    void updateProduct();

    void deleteProduct();

    void checkExitProduct();

    void saveProductToFile();

    void printProductFromFile();

    void printNewProduct();

    void searchProductByName();
}
