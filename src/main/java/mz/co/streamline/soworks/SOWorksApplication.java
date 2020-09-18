/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mz.co.streamline.soworks;

import javax.persistence.EntityManager;
import mz.co.streamline.soworks.core.ConnectionFactory;



/**
 *
 * @author edmilson.cassecasse
 */
public class SOWorksApplication {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        
        EntityManager manager = ConnectionFactory.build();
        
        System.out.println(manager.createNativeQuery("select version()")
                .getSingleResult());
    }
    
}
