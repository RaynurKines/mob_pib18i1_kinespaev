package ru.sibadi.shop.DataAccessObjects;

import ru.sibadi.shop.model.Customer;
import ru.sibadi.shop.model.Shop;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class CustomerDao {
    public void writeInTxtCustomers(Shop shop) {

        try (FileWriter writer3 = new FileWriter("Customers.txt")) {
            for (Customer customer : shop.getCustomers()) {
                String name = customer.getName();
                String sex = customer.getSex();
                String money = String.valueOf(customer.getMoney());
                writer3.write(name + " " + sex + " " + money + System.getProperty("line.separator"));

            }
        } catch (IOException ex) {

            System.out.println(ex.getMessage());
        }
    }

    public List<Customer> readFromTxtCustomers() {
        List<Customer> customers = new ArrayList<>();
        try (Reader readerCustomers = new FileReader("Customers.txt")) {
            BufferedReader bufferedReaderCustomers = new BufferedReader(readerCustomers);
            while (true) {
                String line = bufferedReaderCustomers.readLine();
                if (line == null)
                    break;
                String[] splitedLineToCustomers = line.split(" ");
                Customer customer = new Customer(splitedLineToCustomers[0], splitedLineToCustomers[1], Double.parseDouble(splitedLineToCustomers[2]));
                customers.add(customer);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return customers;
    }
}
