package com.github.vkpeb.service.impl;

import com.github.vkpeb.dao.AuthDao;
import com.github.vkpeb.model.Auth;
import com.github.vkpeb.service.AuthService;
import org.apache.log4j.Logger;
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

    private static Logger logger = Logger.getLogger(AuthServiceImpl.class);

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
        logger.debug("Try get auth by login");
        Auth auth = authDao.getAuthByLogin(login);
//        logger.debug("Auth: " + auth.getLogin() + "; " + auth.getPasswd() + "; " + auth.getType());
        return auth;
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

    @Override
    @Transactional
    public List<Auth> getAllActive() {
        return authDao.getAllActiveAuth();
    }
}
