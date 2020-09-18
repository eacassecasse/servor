/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mz.co.streamline.soworks.domain.repository;

import java.util.List;
import javax.persistence.Query;

/**
 *
 * @author edmilson.cassecasse
 * @param <T>
 */
public class Repository<T> implements Generic<T>{
    
    private final Class<T> type;

    public Repository(Class<T> type) {
        this.type = type;
    }

    @Override
    public Class<T> getType() {
        return type;
    }

    @Override
    public boolean save(Object object) {
        try {
            MANAGER.getTransaction().begin();
            MANAGER.persist(object);
            MANAGER.getTransaction().commit();
            return true;
        } catch (Exception e) {
            MANAGER.getTransaction().rollback();
            System.err.println(e);
            return false;
        } finally {
            MANAGER.close();
        }
    }

    @Override
    public List<Object> findAll() {
       List<Object> list = null;
       
       
        try {
            list = MANAGER.createQuery("from " + getSingleName() + " " + getInitial()).getResultList();
        } catch (Exception e) {
            System.err.println(e);
        }
        
        return list;
    }

    
    @Override
    public Object findById(Long id) {
        Object object = null;
        
        try {
            object = MANAGER.find(type, id);
            
        } catch (Exception e) {
            System.err.println(e);
        }
        
        return object;
    }

    @Override
    public boolean deleteById(Long id) {
        try {
            MANAGER.getTransaction().begin();
            Query query = MANAGER
                    .createQuery("delete from " + getSingleName() + " " + 
                            getInitial() + " where " + getInitial() + ".id = :id");
            query.setParameter("id", id).executeUpdate();
            MANAGER.getTransaction().commit();
            return true;
        } catch (Exception e) {
            MANAGER.getTransaction().rollback();
            System.err.println(e);
            return false;
        }
    }
    

    
    public Object findByVatNumber(int vatNumber) {
        
        Object object = null;
        try {
          Query query = MANAGER
                  .createQuery("select " + getInitial() + " from " +
                          getSingleName() + " " + getInitial() + 
                          " where " + getInitial() + ".vatNumber = " + vatNumber);
          object = query.getSingleResult();
        } catch (Exception e) {
            System.err.println(e);
        }
        return object;
    }
    
    public Object findByIdNumber(String idNumber) {
        Object object = null;
        
        try {
            Query query = MANAGER
                    .createQuery("select " + getInitial() + " from " + 
                            getSingleName() + " " + getInitial() + 
                            "where " + getInitial() + ".idNumber = " + 
                            idNumber);
            
            object = query.getSingleResult();
            
        } catch (Exception e) {
            System.err.println(e);
        }
        return object;
    }
    
    
    public Object findByLicenceNo(String licenceNo) {
        Object object = null;
        
        try {
            Query query = MANAGER
                    .createQuery("select " + getInitial() + " from " + 
                            getSingleName() + " " + getInitial() + 
                            "where " + getInitial() + ".licenceNo = " + 
                            licenceNo);
            
            object = query.getSingleResult();
            
        } catch (Exception e) {
            System.err.println(e);
        }
        return object;
    }
    
    private String getSingleName() {
        return type.getSimpleName();
    }
    
    private char getInitial() {
        return getSingleName().toLowerCase().charAt(0);
    }

    @Override
    public boolean update(Object object) {
        try {
            MANAGER.getTransaction().begin();
            MANAGER.merge(object);
            MANAGER.getTransaction().commit();
            return true;
        } catch (Exception e) {
            MANAGER.getTransaction().rollback();
            System.err.println(e);
            return false;
        }
    }
}
