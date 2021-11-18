package model;

import javax.persistence.*;
import java.util.List;
import java.util.stream.Collectors;

@Entity
@Table(name = "purchase")
public class Purchase {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "purchase_id")
    private int id;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "customer_id")
    private Customer customer;
    @ManyToMany(cascade = CascadeType.ALL, fetch= FetchType.EAGER)
    private List<Product> products;
    @Column(name = "price")
    private Double finalPrice;

    private static double calcFinalPrice(Customer customer, Product product, Sale sale) {
        double price = product.getPrice();
        if (sale == Sale.BLACK_FRIDAY)
            price = price * 0.8;
        else if (customer.isRegular())
            price = price * 0.9;
        return price;
    }

    public Purchase() {
    }

    public Purchase(Customer customer, List<Product> products, Sale sale) {
        this.customer = customer;
        this.products = products;

        this.finalPrice = products.stream().mapToDouble(p -> calcFinalPrice(customer, p, sale)).sum();

        customer.setMoney(customer.getMoney() - finalPrice);
    }

    public int getId() {
        return id;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }

    public Double getFinalPrice() {
        return finalPrice;
    }

    public void setFinalPrice(Double finalPrice) {
        this.finalPrice = finalPrice;
    }

    public String toString() {
        StringBuilder strProducts = new StringBuilder();
        if ((products != null) && (products.size() > 0)) {
            for (int i = 0; i < products.size(); i++) {
                if (i > 0)
                    strProducts.append(",");
                strProducts.append(products.get(i).toString());
            }
        }
        return "Purchase {id " + getId() +
                ", price=" + getFinalPrice() +
                "', products =[" + strProducts + "]}";
    }
}