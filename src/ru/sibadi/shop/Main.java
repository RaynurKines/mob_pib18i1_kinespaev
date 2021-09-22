package ru.sibadi.shop;

import ru.sibadi.shop.DataAccessObjects.CustomerDao;
import ru.sibadi.shop.DataAccessObjects.ProductDao;
import ru.sibadi.shop.model.*;

import java.util.ArrayList;
import java.util.List;

import static java.util.stream.Collectors.averagingDouble;

public class Main {
    public static void main(String[] args) {

        ProductDao prDao = new ProductDao();
        CustomerDao cuDao = new CustomerDao();

        List<Product> products = prDao.readFromTxtProducts();
        List<Customer> customers = cuDao.readFromTxtCustomers();
        Shop shop = new Shop(products, customers);

        for (Product product : shop.getProducts()) {
            System.out.println(product);
        }
        System.out.println(shop.getCustomers().get(0));
        System.out.println(shop.getCustomers().get(1));

        buy(shop.getCustomers().get(0), shop.getProducts().get(0), shop);
        buy(shop.getCustomers().get(1), shop.getProducts().get(2), shop);
        buy(shop.getCustomers().get(1), shop.getProducts().get(1), shop);
        buy(shop.getCustomers().get(1), shop.getProducts().get(1), shop);
        buy(shop.getCustomers().get(1), shop.getProducts().get(1), shop);
        shop.setSale(Sale.BLACK_FRIDAY);
        buy(shop.getCustomers().get(1), shop.getProducts().get(1), shop);
        shop.setSale(Sale.WITHOUT);
        buy(shop.getCustomers().get(1), shop.getProducts().get(1), shop);
        buy(shop.getCustomers().get(1), shop.getProducts().get(0), shop);

        watchPurchases(shop);
        prDao.writeInTxtProducts(shop);
        shop.writeInTxtPurchases();
        cuDao.writeInTxtCustomers(shop);

        //StreamAPI
        double averageCostOfProducts = shop.getProducts().stream()
                .filter(p -> p.getPrice() > 40)
                .collect(averagingDouble(Product::getPrice));
        double averageCostOfBuyedProducts = shop.getPurchases().stream().collect(averagingDouble(Buy::getFinalPrice));

        System.out.println("\n Средняя стоимость продуктов: " + averageCostOfProducts);
        System.out.println("\n Средняя стоимость купленных продуктов: " + averageCostOfBuyedProducts);
        customers.stream()
                .filter(p -> "Male".equals(p.getSex()))
                .forEach(System.out::println);
        customers.stream()
                .map(Customer::getName)
                .forEach(System.out::println);
    }

    private static void buy(Customer c, Product p, Shop shop) {
        System.out.println(shop.createBuy(c, p) + ". Remainder " + c.getMoney());
    }

    public static void watchPurchases(Shop shop) {
        System.out.println("\nPurchases:");
        for (Buy buy : shop.getPurchases()) {
            System.out.println(buy);
        }
    }
}