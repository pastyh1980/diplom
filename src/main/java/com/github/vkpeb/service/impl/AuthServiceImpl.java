package com.github.vkpeb.service.impl;

import com.github.vkpeb.dao.AuthDao;
import com.github.vkpeb.model.Auth;
import com.github.vkpeb.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by pasty on 12.04.2016.
 */
@Service
public class AuthServiceImpl implements AuthService {

    @Autowired
    private AuthDao authDao;

    public void setAuthDao(AuthDao authDao) {
        this.authDao = authDao;
    }

    @Override
    @Transactional
    public Auth addAuth(Auth auth) {
        return authDao.saveAndFlush(auth);
    }

    @Override
    @Transactional
    public void deleteAuth(Auth auth) {
        auth.setEnabled(0);
        authDao.saveAndFlush(auth);
    }

    @Override
    @Transactional
    public Auth getAuth(long id) {
        return authDao.findOne(id);
    }

    @Override
    @Transactional
    public Auth getAuthByLogin(String login) {
        return authDao.getAuthByLogin(login);
    }

    @Override
    @Transactional
    public Auth updateAuth(Auth auth) {
        return authDao.saveAndFlush(auth);
    }

    @Override
    @Transactional
    public List<Auth> getAll() {
        return authDao.findAll();
    }
}
