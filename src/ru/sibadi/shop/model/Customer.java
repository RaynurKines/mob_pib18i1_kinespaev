package ru.sibadi.shop.model;

public class Customer {
    private final String name;
    private String sex;
    private double money;
    private boolean regular = false;
    public int score = 0;

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

    @Override
    public String toString() {
        return name + " : " + money;
    }
}
