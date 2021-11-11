package DataAccessObjects;

import db.HibernateUtil;
import model.Product;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class ProductDao extends ObjectDao {
    public void writeInDB(Transaction transaction, List<Product> products) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            // start a transaction
            transaction = session.beginTransaction();
            // save the student objects
            products.forEach(session::save);
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
            List <Product> products = session.createQuery("from Product", Product.class).list();
            products.forEach(p -> System.out.println(p.getName()));
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }
}
