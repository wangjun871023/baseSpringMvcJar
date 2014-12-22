package com.macrosoft.service;  
  
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.macrosoft.controller.UserController;
import com.macrosoft.core.SpringContextHolder;
import com.macrosoft.dao.IUserDao;
import com.macrosoft.daoGeneral.IGeneralDao;
import com.macrosoft.model.User;
  
@Service  
public class UserService implements IUserService {  
    @Autowired  
    private IUserDao userDao;  
      
    public boolean saveUser(User user) {  
    	userDao.saveUser(user);  
//        System.out.println(2/0);
        return false;  
    }  
}  
