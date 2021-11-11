package DataAccessObjects;

import db.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class ObjectDao {
    public Object findById(int id) {
        return HibernateUtil.getSessionFactory().openSession().get(Object.class, id);
    }

    public void save(Object object) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        session.save(object);
        tx1.commit();
        session.close();
    }

    public void update(Object object) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        session.update(object);
        tx1.commit();
        session.close();
    }

    public void delete(Object object) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        session.delete(object);
        tx1.commit();
        session.close();
    }

    public List<Object> findAll() {
        String nameClass = Object.class.getSimpleName();
        return (List<Object>)  HibernateUtil.getSessionFactory().openSession().createQuery("From " + nameClass).list();
    }
}
