package DataAccessObjects;

import db.HibernateUtil;
import model.Customer;
import model.Product;
import model.Purchase;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class PurchaseDao extends ObjectDao {
    public void writeInDB(Transaction transaction, List<Purchase> purchases) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            // start a transaction
            transaction = session.beginTransaction();
            // save the student objects
            purchases.forEach(session::save);
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
            List <Purchase> purchases = session.createQuery("from Purchase", Purchase.class).list();
            purchases.forEach(System.out::println);
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }
}
