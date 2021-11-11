package DataAccessObjects;

import db.HibernateUtil;
import model.Customer;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class CustomerDao extends ObjectDao {
    public void writeInDB(Transaction transaction, List<Customer> customers) {
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

    public void readFromDB(Transaction transaction) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            List <Customer> customers = session.createQuery("from Customer", Customer.class).list();
            customers.forEach(System.out::println);
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }
}
