/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vn.edu.vnu.uet.fit.model;

import java.util.List;
import org.hibernate.JDBCException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.StatelessSession;
import org.hibernate.Transaction;
import vn.edu.vnu.uet.fit.utils.HibernateUtil;

/**
 *
 * @author hmduong
 */
public class GenericModel<T> {

    private Class<T> entityClass;
    //Session session;

    public GenericModel(Class<T> entityClass) {
        this.entityClass = entityClass;
        //this.proxies = proxies;        
//        Hibernate.initialize(entityClass);
    }

    public List<T> getAll() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        String queryString = "FROM " + entityClass.getName();
        System.out.println("===QUERY STRING: " + queryString);
        Query query = session.createQuery(queryString);
        List<T> lst = query.list();
        //session.close();
        return lst;
    }

    public List<T> search(String condition) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        String queryString = "FROM " + entityClass.getName() + " WHERE " + condition;
        System.out.println("===QUERY STRING: " + queryString);
        Query query = session.createQuery(queryString);
        List<T> lst = query.list();
        //session.close();
        return lst;
    }

    public void create(T entity) throws JDBCException, Exception {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction trans = session.getTransaction();
        try {
            trans.begin();
            session.save(entity);
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
        Session session = HibernateUtil.getSessionFactory().openSession();
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
