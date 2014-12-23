package com.macrosoft.dao;

import org.springframework.stereotype.Repository;

import com.macrosoft.core.orm.hibernate.HibernateDao;
import com.macrosoft.model.User;

@Repository  
public class UserExampleDao extends HibernateDao<User, Integer>  implements IUserExampleDao{
	public void saveUser(User user) {
		this.save(user);
	}
}
