package com.github.vkpeb.model;

import com.github.vkpeb.model.enumer.UserType;
import org.hibernate.annotations.GenericGenerator;
import javax.persistence.*;

/**
 * Created by pasty on 03.04.2016.
 */
@Entity
@Table(name = "auth")
public class Auth {

    @Id
    @GeneratedValue(generator = "increment")
    @GenericGenerator(name = "increment", strategy = "increment")
    @Column(name = "auth_id", length = 6, nullable = false)
    private long id;

    @Column(name = "login", unique = true)
    private String login;

    @Column(name = "passwd")
    private String passwd;

    @Column(name = "type")
    @Enumerated(EnumType.STRING)
    private UserType type;

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

    public UserType getType() {
        return type;
    }

    public void setType(UserType type) {
        this.type = type;
    }

    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }

    @Override
    public String toString() {
        return "Auth { id: " + id + ", login: " + login + ", passwd: " + passwd + ", accessType: " + type.name() + "}";
    }
}
