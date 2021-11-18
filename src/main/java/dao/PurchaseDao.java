package dao;

import db.HibernateUtil;
import model.Customer;
import model.Purchase;
import org.hibernate.Session;
import org.hibernate.Transaction;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

public class PurchaseDao {
    public void writeInDB(List<Purchase> purchases) {
        Transaction transaction = null;
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
    public void writeInDB(Purchase purchase) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            // start a transaction
            transaction = session.beginTransaction();
            // save the student objects
            session.save(purchase);
            // commit transaction
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

    public List <Purchase> readFromDB() {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            List <Purchase> purchases = session.createQuery("FROM Purchase", Purchase.class).list();
            return purchases;
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
        return null;
    }

    public void printPurchasesAvgPrice(){
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            Double avgPrice = (Double) session.createSQLQuery("select AVG(price) from Purchase").getSingleResult();
            System.out.println("!!!AVG price = " + avgPrice + "!!!");
        } catch (Exception e) {
            if (transaction != null) {
                //transaction.rollback();
            }
            e.printStackTrace();
        }
    }
}
