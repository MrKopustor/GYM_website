package com.teachmeskills.gym_website.request;

import com.teachmeskills.gym_website.entity.*;
import org.hibernate.SessionFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppCfg {

    @Bean
    public static SessionFactory entityManagerFactory() {
        return new org.hibernate.cfg.Configuration().configure("hibernate.cfg.xml")
                .addAnnotatedClass(Exercise.class)
                .addAnnotatedClass(Review.class)
                .addAnnotatedClass(User.class)
                .addAnnotatedClass(Role.class)
                .addAnnotatedClass(SaleSeasonTickets.class)
                .addAnnotatedClass(Schedule.class)
                .addAnnotatedClass(Ticket.class)
                .addAnnotatedClass(Training.class)
                .buildSessionFactory();
    }
}


