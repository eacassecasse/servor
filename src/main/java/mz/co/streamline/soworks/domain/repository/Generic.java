/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mz.co.streamline.soworks.domain.repository;

import java.util.List;
import javax.persistence.EntityManager;
import mz.co.streamline.soworks.core.ConnectionFactory;

/**
 *
 * @author edmilson.cassecasse
 * @param <T>
 */
public interface Generic<T> {
    
    public final static EntityManager MANAGER = 
            ConnectionFactory.build();
    
    Class<T> getType();
    boolean save(Object object);
    List<Object> findAll();
    Object findById(Long id);
    boolean update(Object object);
    boolean deleteById(Long id);
    
}
