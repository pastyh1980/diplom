package com.github.vkpeb.model.form;

import com.github.vkpeb.model.enumer.UserType;

/**
 * Created by pasty on 17.04.2016.
 */
public class AuthForm {
    private long id;
    private String login;
    private String passwd;
    private String confirmPasswd;
    private UserType type;
    private boolean enabled;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPasswd() {
        return passwd;
    }

    public void setPasswd(String passwd) {
        this.passwd = passwd;
    }

    public String getConfirmPasswd() {
        return confirmPasswd;
    }

    public void setConfirmPasswd(String confirmPasswd) {
        this.confirmPasswd = confirmPasswd;
    }

    public UserType getType() {
        return type;
    }

    public void setType(UserType type) {
        this.type = type;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }
}
