package com.teachmeskills.gym_website.request.dataBase.request.general;

import com.teachmeskills.gym_website.config.SecurityConfig;
import com.teachmeskills.gym_website.entity.Role;
import com.teachmeskills.gym_website.entity.User;
import com.teachmeskills.gym_website.request.AppCfg;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.ui.Model;

import java.util.ArrayList;
import java.util.List;

import static com.teachmeskills.gym_website.request.AppCfg.entityManagerFactory;

@Component
public class GeneralRequest {


    private static PasswordEncoder passwordEncoder = SecurityConfig.passwordEncoder();

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


    public static void addToModelListRoles(Model model) {
        List<String> roleList = new ArrayList<>();
        try (Session session = sf.getCurrentSession()) {
            session.beginTransaction();
            Query findRoleList = session.createQuery("Select role FROM Role");
            roleList = findRoleList.getResultList();
            session.getTransaction().commit();
        }
        System.out.printf(roleList.toString());
        model.addAttribute("ROLE_STATE", roleList);
    }



    public static void saveUser(String nameUser,
                                String surnameUser,
                                String emailUser,
                                String phoneUser,
                                String dateBirthUser,
                                String passwordUser) {
        User user = new User();
        user.setEmail(emailUser);
        user.setPassword(passwordEncoder.encode(passwordUser));
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
