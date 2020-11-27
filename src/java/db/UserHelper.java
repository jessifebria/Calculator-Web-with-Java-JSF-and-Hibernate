/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package db;

import java.io.Serializable;
import java.util.List;
import org.hibernate.Query;

import org.hibernate.Session;

/**
 *
 * @author x.x
 */
public class UserHelper implements Serializable {

    Session session = null;
    private User user = null;

    public UserHelper() {
        this.session = NewHibernateUtil.getSessionFactory().getCurrentSession();
    }

    public User CheckUser(String email, String pass) {
        session = NewHibernateUtil.getSessionFactory().openSession();
        try {
            org.hibernate.Transaction tx = session.beginTransaction();
            Query q = session.createQuery("from User where email =:emaill and password=:passs");
            q.setString("emaill", email);
            q.setString("passs", pass);
            user = (User) q.uniqueResult();
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (user != null) {
            return user;

        } else {
            return null;
        }
    }

    public void SaveUser(User user) {
        session = NewHibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        session.save(user);
        session.getTransaction().commit();
        session.close();
    }

    public boolean IsUserExist(String email) {
        session = NewHibernateUtil.getSessionFactory().openSession();
        Long count = null;
        try {
            org.hibernate.Transaction tx = session.beginTransaction();
            Query query = session.createQuery("select count(*) from User where email=:emaill");
            query.setString("emaill", email);
            count = (Long)query.uniqueResult();
            System.out.println("count " + count);
        } catch (Exception e) {
            e.printStackTrace();
        }

        if (count > 0) {
            return true;
        }
        return false;
    }
}
