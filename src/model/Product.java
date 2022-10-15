package model;

public class Product implements Comparable<Product> {
    private String productID;
    private String productName;
    private double unitPrice;
    private int quantity;
    private String status;

    public Product(String productID, String productName, double unitPrice, int quantity, String status) {
        this.productID = productID;
        this.productName = productName;
        this.unitPrice = unitPrice;
        this.quantity = quantity;
        this.status = status;
    }

    public String getProductID() {
        return productID;
    }

    public void setProductID(String productID) {
        this.productID = productID;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public double getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(double unitPrice) {
        this.unitPrice = unitPrice;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void output() {
        System.out.printf("|%-10s|%-20s|%-14s|%-10s|%-14s|\n", productID, productName, unitPrice,
                quantity, status);
    }

    @Override
    public String toString() {
        return productID + "," + productName + "," + unitPrice
                + "," + quantity + "," + status;
    }

    @Override
    public int compareTo(Product o) {
        return this.getProductID().compareToIgnoreCase(o.getProductID());
    }
}
