package com.github.vkpeb.service;

import com.github.vkpeb.model.Auth;

import java.util.List;

/**
 * Created by pasty on 12.04.2016.
 */
public interface AuthService {

    Auth addAuth(Auth auth);
    void deleteAuth(Auth auth);
    Auth getAuth(long id);
    Auth getAuthByLogin(String login);
    Auth updateAuth(Auth auth);
    List<Auth> getAll();
}
