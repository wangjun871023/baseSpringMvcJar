package com.macrosoft.service;  
  
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.macrosoft.dao.IUserExampleDao;
import com.macrosoft.model.User;
  
@Service  
public class UserExampleService implements IUserExampleService {  
    @Autowired  
    private IUserExampleDao userDao;  
      
    public boolean saveUser(User user) {  
    	userDao.saveUser(user);  
//        System.out.println(2/0);
        return false;  
    }  
}  
