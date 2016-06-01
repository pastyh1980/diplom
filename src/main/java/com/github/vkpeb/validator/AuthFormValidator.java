package com.github.vkpeb.validator;

import com.github.vkpeb.model.Auth;
import com.github.vkpeb.model.form.AuthForm;
import com.github.vkpeb.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import java.util.regex.Pattern;

/**
 * Created by pasty on 13.04.2016.
 */
@Component
public class AuthFormValidator implements Validator {

    @Autowired
    private AuthService authService;

    private Pattern onlyLatAndDig = Pattern.compile("^[a-z0-9]+$}");

    @Override
    public boolean supports(Class<?> clazz) {
        return AuthForm.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        AuthForm authForm = (AuthForm) target;

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "login", "notEmpty");

        Auth auth = authService.getAuth(authForm.getId());
        Auth auth1 = null;
        if (!authForm.getLogin().isEmpty()) auth1 = authService.getAuthByLogin(authForm.getLogin());
        if (auth != null) {
            if (auth1 != null && !auth.getLogin().equals(authForm.getLogin()))
                errors.rejectValue("login", "loginIsUsed");
        } else {
            if (auth1 != null)
                errors.rejectValue("login", "loginIsUsed");
        }

        if (authForm.getLogin().contains("student") || authForm.getLogin().contains("parent"))
            errors.rejectValue("login", "loginIsUsed");

        if (onlyLatAndDig.matcher(authForm.getLogin()).matches())
            errors.rejectValue("login", "loginIncorrect");

        if (auth == null) {
            ValidationUtils.rejectIfEmptyOrWhitespace(errors, "passwd", "notEmpty");
            ValidationUtils.rejectIfEmptyOrWhitespace(errors, "confirmPasswd", "notEmpty");
        } else {
            if (authForm.getPasswd().isEmpty() && authForm.getConfirmPasswd().isEmpty()) {
                authForm.setPasswd(auth.getPasswd());
                authForm.setConfirmPasswd(auth.getPasswd());
            }
        }

        if (authForm.getPasswd() != null && authForm.getPasswd().length() < 6)
            errors.rejectValue("passwd", "passwdToShort");

        if (!authForm.getPasswd().equals(authForm.getConfirmPasswd()))
            errors.rejectValue("passwd", "passwdNotConfirmed");
    }
}
