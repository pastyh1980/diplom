package com.github.vkpeb.controller;

import com.github.vkpeb.model.Auth;
import com.github.vkpeb.model.enumer.UserType;
import com.github.vkpeb.model.form.AuthForm;
import com.github.vkpeb.service.AuthService;
import com.github.vkpeb.validator.AuthFormValidator;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by pasty on 12.04.2016.
 */
@Controller
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private AuthService authService;

    @Autowired
    private AuthFormValidator authFormValidator;

    @InitBinder
    protected void initBinder(WebDataBinder binder) {
        binder.setValidator(authFormValidator);
    }

    private static Logger logger = Logger.getLogger(AuthController.class);

    public void setAuthService(AuthService authService) {
        this.authService = authService;
    }

    @RequestMapping(value = "/reguser", method = RequestMethod.POST)
    public ModelAndView addOrUpdateUser(@ModelAttribute("userForm") @Validated AuthForm authForm,
                                                BindingResult result) {
        logger.debug("save or update user: " + authForm);

        ModelAndView response = new ModelAndView();

        if (result.hasErrors()) {
            response.addObject("userForm", authForm);
            updateModel(response);
            response.setViewName("userform");
            return response;
        }

        Auth auth = new Auth();
        synchAuthWithAuthForm(auth, authForm);

        if (authForm.getId() == 0)
            authService.addAuth(auth);
        else authService.updateAuth(auth);

        response.setViewName("redirect:/auth/userlist");

        return response;
    }


    @RequestMapping(value = "/userlist", method = RequestMethod.GET)
    public ModelAndView listUsers() {
        logger.debug("List all users");

        ModelAndView response = new ModelAndView();

        List<Auth> authList = authService.getAllActive();

        response.addObject("users", authList);

        response.setViewName("userslist");

        return response;
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ModelAndView userDetail(@PathVariable("id") long id) {
        logger.debug("Details for user with id " + id);

        ModelAndView response = new ModelAndView();

        Auth auth = authService.getAuth(id);

        if (auth == null) {
            response.addObject("css", "error");
            response.addObject("msg", "Пользователь с id " + id + " не найден!");
            response.setViewName("redirect:/auth/userlist");
            return response;
        }

        AuthForm authForm = new AuthForm();
        synchAuthWithAuthForm(auth, authForm);

        response.addObject("userForm", authForm);
        updateModel(response);
        response.setViewName("userform");

        return response;
    }

    @RequestMapping(value = "/adduser", method = RequestMethod.GET)
    public ModelAndView addUser(/*Model model*/) {
        logger.debug("Show addUser form");

        ModelAndView response = new ModelAndView();

        AuthForm authForm = new AuthForm();

        response.addObject("userForm", authForm);
        updateModel(response);

        response.setViewName("userform");

        return response;
    }

    private void updateModel(ModelAndView model) {
        List<UserType> userTypes = new ArrayList<>();
        userTypes.addAll(Arrays.asList(UserType.values()));
        model.addObject("userTypes", userTypes);
    }

    private void synchAuthWithAuthForm(Auth auth, AuthForm authForm) {
        if (auth.getLogin() == null) {
            auth.setId(authForm.getId());
            auth.setLogin(authForm.getLogin());
            auth.setPasswd(authForm.getPasswd());
            auth.setType(authForm.getType());
            auth.setEnabled(authForm.isEnabled() ? 1 : 0);
        } else {
            authForm.setId(auth.getId());
            authForm.setLogin(auth.getLogin());
            authForm.setPasswd(auth.getPasswd());
            authForm.setType(auth.getType());
            authForm.setEnabled(auth.getEnabled() == 1);
        }
    }
}
