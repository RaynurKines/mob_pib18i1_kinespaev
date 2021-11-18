package dao;

import db.HibernateUtil;
import model.Product;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class ProductDao {
    public void writeInDB(List<Product> products) {
        Transaction transaction = null;
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

    public List<Product> readFromDB() {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            List <Product> products = session.createQuery("from Product", Product.class).list();
            return products;
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
        return null;
    }
}
