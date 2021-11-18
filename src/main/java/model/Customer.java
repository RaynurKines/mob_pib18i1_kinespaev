package model;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "customer")
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "name")
    private String name;

    @Column(name = "sex")
    private String sex;

    @Column(name = "money")
    private double money;

    @Column(name = "regular")
    private boolean regular = false;

    @Column(name = "score")
    public int score = 0;

    @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL, orphanRemoval = true, fetch= FetchType.EAGER)
    private List<Purchase> purchases;

    public Customer() {

    }

    public Customer(String name, String sex, double money) {
        this.name = name;
        this.sex = sex;
        this.money = money;
    }

    public String getName() {
        return name;
    }

    public double getMoney() {
        return money;
    }

    public void setMoney(double money) {
        this.money = money;
    }

    public boolean isRegular() {
        return regular;
    }

    public void setRegular(boolean regular) {
        this.regular = regular;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public int getId() {
        return id;
    }

    public List<Purchase> getPurchases() {
        return purchases;
    }

    public void setPurchases(List<Purchase> purchases) {
        this.purchases = purchases;
    }

    @Override
    public String toString() {
        StringBuilder strPurchases = new StringBuilder();
        if ((purchases != null) && (purchases.size() > 0)) {
            for (int i = 0; i < purchases.size(); i++) {
                if (i > 0)
                    strPurchases.append(",");
                strPurchases.append(purchases.get(i).toString());
            }
        }
        return "Customer {" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", sex='" + sex + '\'' +
                ", money=" + money +
                ", regular=" + regular +
                ", purchases count=" + purchases.size() +
                ", purchases=[{" + strPurchases +
                "]}";
    }
}
