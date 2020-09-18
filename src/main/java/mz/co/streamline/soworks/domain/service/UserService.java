/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mz.co.streamline.soworks.domain.service;

import mz.co.streamline.soworks.domain.exception.EntityNotFoundException;
import mz.co.streamline.soworks.domain.model.SessionStatus;
import mz.co.streamline.soworks.domain.model.User;
import mz.co.streamline.soworks.domain.model.UserSession;
import mz.co.streamline.soworks.domain.repository.Repository;
import org.joda.time.LocalDateTime;

/**
 *
 * @author edmilson.cassecasse
 */
public class UserService {
    
    private final Repository<User> repository;

    public UserService() {
        repository = new Repository<>(User.class);
    }
    
    public boolean openSession(Long id, UserSession session) {
        
        User user = findById(id);
        
        session.setOpenDate(LocalDateTime.now());
        session.setStatus(SessionStatus.ABERTA);
        
        user.addSession(session);
        return true;
        
    }
    
    private User findById(Long id) {
        User user = (User)repository.findById(id);
        
        if (user == null)
            throw new EntityNotFoundException("Usuário não encontrado");
        
        return user;
    }
    
    
}
