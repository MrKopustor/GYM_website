package com.teachmeskills.gym_website.request.dataBase.request.general;

import com.teachmeskills.gym_website.entity.Role;
import com.teachmeskills.gym_website.entity.User;
import com.teachmeskills.gym_website.request.AppCfg;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.stereotype.Component;

import static com.teachmeskills.gym_website.request.AppCfg.entityManagerFactory;

@Component
public class GeneralRequest {

    private static SessionFactory sf = entityManagerFactory();

    public static User findUserByUsername(String email) {
        User user = null;
        try (Session session = sf.getCurrentSession()) {
            session.beginTransaction();
            Query findUser = session.createQuery("FROM User Where email =: login");
            findUser.setParameter("login", email);
            if (!findUser.getResultList().isEmpty()) user = (User) findUser.getResultList().get(0);
            session.getTransaction().commit();
        }
        return user;
    }

    public static void saveUser(String nameUser,
                                String surnameUser,
                                String emailUser,
                                String phoneUser,
                                String dateBirthUser,
                                String passwordUser) {
        User user = new User();
        user.setEmail(emailUser);
        user.setPassword(passwordUser);
        user.setName(nameUser);
        user.setSurname(surnameUser);
        user.setCellPhone(phoneUser);
        user.setDateBirth(dateBirthUser);
        try (Session session = sf.getCurrentSession()) {
            session.beginTransaction();
            Query findRole = session.createQuery("FROM Role where role =: role");
            findRole.setParameter("role", "ROLE_CLIENT");
            Role role = (Role) findRole.getResultList().get(0);

            user.setRole(role);

            session.save(user);
            session.getTransaction().commit();
        }
    }
}
