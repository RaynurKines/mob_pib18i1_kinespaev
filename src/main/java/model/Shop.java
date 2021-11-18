package model;

import dao.PurchaseDao;
import db.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;

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

    public Purchase createPurchase(Customer customer, List<Product> products){
        if (customer.score == 10)
            customer.setRegular(true);
        Purchase purchase = new Purchase(customer, products, sale);
        purchases.add(purchase);
        customer.score += 1;
        update(customer);
        products.forEach(p ->update(p));

        PurchaseDao puDao = new PurchaseDao();
        puDao.writeInDB(purchase);
        return purchase;
    }

    public void update(Object object) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        session.update(object);
        tx1.commit();
        session.close();
    }
}
