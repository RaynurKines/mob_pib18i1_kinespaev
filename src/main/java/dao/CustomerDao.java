package dao;

import db.HibernateUtil;
import model.Customer;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.ArrayList;
import java.util.List;

public class CustomerDao {
    public void writeInDB(List<Customer> customers) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            // start a transaction
            transaction = session.beginTransaction();
            // save the student objects
            customers.forEach(session::save);
            // commit transaction
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

    public List<Customer> readFromDB() {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            List<Customer> customers = session.createQuery("from Customer", Customer.class).list();
            return customers;
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
        return null;
    }
    public void printMaleCustomers(){
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            List<Customer> customers = session.createQuery("from Customer where sex = 'male'", Customer.class).list();
            System.out.println("!!!Male customers:!!!");
            customers.forEach(System.out::println);
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }
    public void printCustomersSumMoneyGroupBySex(){
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            List<Object[]> groupedCustomers = session.createQuery("select sex, sum(money) as sum from Customer group by sex", Object[].class).getResultList();
            List<Customer> customers = new ArrayList<>();
            for (Object[] row : groupedCustomers) {
                Customer customer = new Customer() {
                    @Override
                    public String toString() {
                        return "Customers {" +
                                "sex=" + getSex() +
                                ", sum of money=" + getMoney() +
                                '}';
                    }
                };
                customer.setSex((String) row[0]);
                customer.setMoney((double) row[1]);

                customers.add(customer);
            }
            System.out.println("!!!Customers grouped by sex, money is summed:!!!");
            customers.forEach(System.out::println);
        } catch (Exception e) {
            if (transaction != null) {
                //transaction.rollback();
            }
            e.printStackTrace();
        }
    }
}
