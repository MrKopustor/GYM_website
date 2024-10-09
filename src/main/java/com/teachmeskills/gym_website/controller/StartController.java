package com.teachmeskills.gym_website.controller;

import com.teachmeskills.gym_website.entity.User;
import com.teachmeskills.gym_website.request.dataBase.request.general.GeneralRequest;
import jakarta.annotation.security.RolesAllowed;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import static com.teachmeskills.gym_website.request.dataBase.request.general.GeneralRequest.*;

@Controller
//@RequestMapping(path = "/main")
public class StartController {

    @GetMapping(path = "/gymhub.com")
    private String startPage(Model model) {
        return "start";
    }

    @GetMapping(path = "/main-sing-up")
    private String singUp(Model model) {
        return "registration";
    }

    @GetMapping(path = "/main-sing-in")
    private String singIn(Model model) {
        addToModelListRoles(model);
        model.addAttribute("user", findUserByUsername(SecurityContextHolder.getContext().getAuthentication().getName()));
        return "authPage";
    }

    @PostMapping(path = "/main-sing-up")
    private String saveUserContr(@RequestParam(name = "nameUser") String nameUser,
                            @RequestParam(name = "surnameUser") String surnameUser,
                            @RequestParam(name = "emailUser") String emailUser,
                            @RequestParam(name = "phoneUser") String phoneUser,
                            @RequestParam(name = "dateBirthUser") String dateBirthUser,
                            @RequestParam(name = "passwordUser") String passwordUser,
                            @RequestParam(name = "passwordRepeatUser") String passwordRepeatUser,
                            Model model) {

        User user = findUserByUsername(emailUser);
        if (user != null) {
            model.addAttribute("userNotNULL", true);
            model.addAttribute("nameUser", nameUser);
            model.addAttribute("surnameUser", surnameUser);
            model.addAttribute("emailUser", emailUser);
            model.addAttribute("phoneUser", phoneUser);
            model.addAttribute("emailUser", emailUser);
            model.addAttribute("dateBirthUser", dateBirthUser);
            model.addAttribute("passwordUser", passwordUser);
            model.addAttribute("passwordRepeatUser", passwordRepeatUser);
            return "registration";
        }

        if (!passwordUser.equals(passwordRepeatUser)) {
            model.addAttribute("passwordUserWRONG", true);
            model.addAttribute("nameUser", nameUser);
            model.addAttribute("surnameUser", surnameUser);
            model.addAttribute("emailUser", emailUser);
            model.addAttribute("phoneUser", phoneUser);
            model.addAttribute("emailUser", emailUser);
            model.addAttribute("dateBirthUser", dateBirthUser);
            model.addAttribute("passwordUser", passwordUser);
            model.addAttribute("passwordRepeatUser", passwordRepeatUser);
            return "registration";
        }
        saveUser(nameUser, surnameUser, emailUser, phoneUser, dateBirthUser, passwordUser);
        return "start";
    }


}
