import DataAccessObjects.CustomerDao;
import DataAccessObjects.ProductDao;
import DataAccessObjects.PurchaseDao;
import model.*;
import org.hibernate.Transaction;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {

        Transaction transaction = null;

        ProductDao prDao = new ProductDao();
        CustomerDao cuDao = new CustomerDao();
        PurchaseDao puDao = new PurchaseDao();

        List<Product> products = new ArrayList<>();
        List<Customer> customers = new ArrayList<>();
        List<Purchase> purchases = new ArrayList<>();

        customers.add(new Customer("Ваня", "male", 1000));
        customers.add(new Customer("Петя", "male", 500));
        products.add(new Product("bread", 40));
        products.add(new Product("butter", 30));
        products.add(new Product("milk", 50));

        Shop shop = new Shop(products, customers, purchases);
        shop.createPurchase(shop.getCustomers().get(1), shop.getProducts());
        shop.createPurchase(shop.getCustomers().get(0), shop.getProducts());
        cuDao.writeInDB(transaction, shop.getCustomers());
        prDao.writeInDB(transaction,shop.getProducts());
        puDao.writeInDB(transaction,shop.getPurchases());
        cuDao.readFromDB(transaction);
        prDao.readFromDB(transaction);
        //Не показываются продукты, если в toString() вписать customer, то будет вылетать с ошибкой "customer is null"
        puDao.readFromDB(transaction);



        /*System.out.println(shop.getCustomers().get(0));
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
        shop.writeInTxtPurchases();*/

        //StreamAPI
        /*double averageCostOfProducts = shop.getProducts().stream()
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
                .forEach(System.out::println);*/
    }

    private static void buy(Customer c, List<Product> p, Shop shop) {
        System.out.println(shop.createPurchase(c, p) + ". Remainder " + c.getMoney());
    }

    public static void watchPurchases(Shop shop) {
        System.out.println("\nPurchases:");
        for (Purchase buy : shop.getPurchases()) {
            System.out.println(buy);
        }
    }
}