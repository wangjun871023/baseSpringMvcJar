package com.macrosoft.dao;

import org.springframework.stereotype.Repository;

import com.macrosoft.core.orm.hibernate.HibernateDao;
import com.macrosoft.model.User;

@Repository  
public class UserDao extends HibernateDao<User, Integer>  implements IUserDao{
	public void saveUser(User user) {
		this.save(user);
	}
}
