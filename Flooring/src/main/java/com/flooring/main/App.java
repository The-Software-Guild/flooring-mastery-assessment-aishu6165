package com.flooring.main;

import com.flooring.controller.FlooringController;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class App {

    public static void main(String[] args) {
        ApplicationContext appContext
                = new ClassPathXmlApplicationContext("classpath:applicationContext.xml");

        FlooringController controller = appContext.getBean("controller",FlooringController.class);
        controller.run();
    }
}
