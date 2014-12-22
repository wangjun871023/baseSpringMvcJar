package com.macrosoft.service;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

import com.macrosoft.controller.UserController;
  
public class UserServiceTest {  
    @Test  
    public void test() {  
        ApplicationContext ctx = new FileSystemXmlApplicationContext(  
                "src/main/resources/conf/spring/spring_application_context.xml");  
        UserController controller = ctx.getBean(UserController.class);  
    }  
}  
