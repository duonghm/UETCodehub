/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vn.edu.vnu.uet.fit.model;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.hibernate.Criteria;
import org.hibernate.JDBCException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.StatelessSession;
import org.hibernate.Transaction;
import vn.edu.vnu.uet.fit.beans.GenericBean;
import vn.edu.vnu.uet.fit.utils.HibernateUtil;

/**
 *
 * @author hmduong
 */
public class GenericModel<T> {

    private Class<T> entityClass;

    private static SessionFactory sessionFactory = HibernateUtil.getSessionFactory();

    static Session lazySession;
    
    public GenericModel(Class<T> entityClass) {
        this.entityClass = entityClass;
    }

    public Session getSession() {
        return sessionFactory.openSession();
    }

    public static Session getLazySession() {
        if(lazySession == null){
            System.out.println("=== CREATE NEW LAZY SESSION");
            lazySession = sessionFactory.openSession();
        }
        return lazySession;
    }
    
    public Criteria createCriteria() {
        return getLazySession().createCriteria(entityClass);
    }

    public List<T> getAll() {
        System.out.println("=== GET ALL: " + entityClass.getName());
        //List<T> lst = createCriteria().list();
        //return lst;
        try {
            Session session = getLazySession();
            session.beginTransaction();
            String queryString = "FROM " + entityClass.getName();
            System.out.println("===QUERY STRING: " + queryString);
            Query query = session.createQuery(queryString);
            List<T> lst = query.list(); 
            session.getTransaction().commit();
            return lst;
        } catch (Exception ex) {
            Logger.getLogger(GenericBean.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

    public List<T> search(String condition) {
        System.out.println("=== SEARCH: " + entityClass.getName());
        Session session = getLazySession();
        session.beginTransaction();
        String queryString = "FROM " + entityClass.getName() + " WHERE " + condition;
        System.out.println("===QUERY STRING: " + queryString);
        Query query = session.createQuery(queryString);
        List<T> lst = query.list();
        session.getTransaction().commit();
        //session.close();
        return lst;
    }

    public void create(T entity) throws JDBCException, Exception {
        StatelessSession session = HibernateUtil.getSessionFactory().openStatelessSession();
        Transaction trans = session.getTransaction();
        try {
            trans.begin();
            session.insert(entity);
            //session.save(entity);
            trans.commit();
        } catch (JDBCException ex) {
            trans.rollback();
            throw ex;
        } catch (Exception ex) {
            trans.rollback();
            throw ex;
        }
        session.close();
    }

    /*public void create(List<T> entities) throws JDBCException, Exception {
        StatelessSession session = HibernateUtil.getSessionFactory().openStatelessSession();
        Transaction trans = session.getTransaction();
        try {
            trans.begin();
            for (T e : entities) {
                session.insert(e);
            }
            //session.save(entity);
            trans.commit();
        } catch (JDBCException ex) {
            trans.rollback();
            throw ex;
        } catch (Exception ex) {
            trans.rollback();
            throw ex;
        }
        session.close();
    }*/

    public void update(T entity) throws JDBCException, Exception {
        StatelessSession session = HibernateUtil.getSessionFactory().openStatelessSession();
        Transaction trans = session.getTransaction();
        try {
            trans.begin();
            session.update(entity);
            trans.commit();
        } catch (JDBCException ex) {
            trans.rollback();
            throw ex;
        } catch (Exception ex) {
            trans.rollback();
            throw ex;
        }
        session.close();
    }

    public void delete(T entity) throws Exception {
        Session session = getSession();
        Transaction trans = session.getTransaction();
        try {
            trans.begin();
            session.delete(entity);
            trans.commit();
        } catch (Exception ex) {
            trans.rollback();
            throw ex;
        }
        session.close();
    }
}
