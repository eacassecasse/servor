/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mz.co.streamline.soworks.core;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author edmilson.cassecasse
 */
public class ConnectionFactory {
    
    private static final String PERSISTENCE_UNIT = "SOWorksModelPU";
    private static EntityManagerFactory factory = null;
    private static EntityManager manager = null;

    private ConnectionFactory() {
    }
    
    public static EntityManager build() {
        if (factory == null) {
            factory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT);
        }
        
        if (manager == null) {
            manager = factory.createEntityManager();
        }
        
        return manager;
    }
    
    public static ConnectionFactory getInstance() {
        return ConnectionFactoryHolder.INSTANCE;
    }
    
    private static class ConnectionFactoryHolder {
        private static final ConnectionFactory INSTANCE = new ConnectionFactory();
    }
}
