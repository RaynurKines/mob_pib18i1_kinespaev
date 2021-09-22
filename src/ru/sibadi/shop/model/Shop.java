package ru.sibadi.shop.model;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Shop{
    private final List<Product> products;
    private final List<Customer> customers;
    private final List<Buy> purchases = new ArrayList<>();
    private Sale sale = Sale.WITHOUT;

    public Shop() {
        this.products = new ArrayList<>();
        products.add(new Product("Bread", 25));
        products.add(new Product("Butter", 40));
        products.add(new Product("Milk", 70));
        products.add(new Product("Cookie", 100));
        this.customers = new ArrayList<>();
        customers.add(new Customer("Danil", "Male", 150));
        customers.add(new Customer("Tanya", "Female", 300));
        customers.add(new Customer("Dmitriy", "Male", 100));
    }
    public Shop(List<Product> products, List<Customer> customers){
        this.products=products;
        this.customers=customers;
    }

    public List<Customer> getCustomers() { return customers; }

    public List<Product> getProducts() {
        return products;
    }

    public List<Buy> getPurchases() {
        return purchases;
    }

    public Sale getSale() {
        return sale;
    }

    public void setSale(Sale sale) {
        this.sale = sale;
    }

    public Buy createBuy(Customer c, Product p) {
        if (c.score == 10)
            c.setRegular(true);
        Buy buy = new Buy(c, p, sale);
        purchases.add(buy);
        c.score += 1;
        return buy;
    }





    public void writeInTxtPurchases() {

        try (FileWriter writer2 = new FileWriter("Purchases.txt")) {
            for (Buy buy : purchases) {
                String customer = buy.getCustomer().getName();
                String product = buy.getProduct().getName();
                String price = String.valueOf(buy.getFinalPrice());
                writer2.write(customer + " " + product + " " + price + System.getProperty("line.separator"));
            }
        } catch (IOException ex) {

            System.out.println(ex.getMessage());
        }
    }


}