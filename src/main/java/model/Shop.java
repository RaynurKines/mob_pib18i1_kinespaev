package model;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Shop{
    private List<Product> products;
    private List<Customer> customers;
    private List<Purchase> purchases;
    private Sale sale = Sale.WITHOUT;

    public Shop() {
    }
    public Shop(List<Product> products, List<Customer> customers, List<Purchase> purchases){
        this.products=products;
        this.customers=customers;
        this.purchases=purchases;
    }

    public List<Customer> getCustomers() { return customers; }

    public List<Product> getProducts() {
        return products;
    }

    public List<Purchase> getPurchases() {
        return purchases;
    }

    public Sale getSale() {
        return sale;
    }

    public void setSale(Sale sale) {
        this.sale = sale;
    }

    public void setPurchases(List<Purchase> purchases) {
        this.purchases = purchases;
    }

    public Purchase createPurchase(Customer c, List<Product> p) {
        if (c.score == 10)
            c.setRegular(true);
        Purchase purchase = new Purchase(c, p, sale);
        purchases.add(purchase);
        c.score += 1;
        return purchase;
    }


}