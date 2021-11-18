import dao.CustomerDao;
import dao.ProductDao;
import dao.PurchaseDao;
import model.*;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {

        ProductDao prDao = new ProductDao();
        CustomerDao cuDao = new CustomerDao();
        PurchaseDao puDao = new PurchaseDao();

        List<Product> products = new ArrayList<>();
        List<Customer> customers = new ArrayList<>();
        List<Purchase> purchases = new ArrayList<>();

        customers.add(new Customer("Ваня", "male", 1000));
        customers.add(new Customer("Петя", "male", 500));
        customers.add(new Customer("Алина", "female", 500));
        customers.add(new Customer("Екатерина", "female", 500));
        products.add(new Product("bread", 40));
        products.add(new Product("butter", 30));
        products.add(new Product("milk", 50));
        products.add(new Product("coffee", 80));

        Shop shop = new Shop(products, customers, purchases);

        List<Product> productSet1 = new ArrayList<Product>();
        productSet1.add(shop.getProducts().get(0));
        productSet1.add(shop.getProducts().get(3));
        List<Product> productSet2 = new ArrayList<Product>();
        productSet2.add(shop.getProducts().get(2));
        productSet2.add(shop.getProducts().get(3));
        productSet2.add(shop.getProducts().get(3));
        productSet2.add(shop.getProducts().get(1));

        cuDao.writeInDB(shop.getCustomers());
        prDao.writeInDB(shop.getProducts());
        puDao.writeInDB(shop.getPurchases());

        shop.createPurchase(shop.getCustomers().get(1), productSet1);
        shop.createPurchase(shop.getCustomers().get(0), productSet2);
        shop.createPurchase(shop.getCustomers().get(3), productSet1);
        shop.createPurchase(shop.getCustomers().get(3), productSet2);
        customers = cuDao.readFromDB();
        products = prDao.readFromDB();
        purchases = puDao.readFromDB();

        customers.forEach(System.out::println);
        products.forEach(System.out::println);
        purchases.forEach(System.out::println);

        cuDao.printMaleCustomers();
        cuDao.printCustomersSumMoneyGroupBySex();
        puDao.printPurchasesAvgPrice();

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