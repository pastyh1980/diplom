package com.github.vkpeb.service.impl;

import com.github.vkpeb.model.Auth;
import com.github.vkpeb.service.AuthService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by pasty on 16.04.2016.
 */
@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private AuthService authService;

    private static Logger logger = Logger.getLogger(UserDetailsServiceImpl.class);

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException{
        logger.info("To loadUserByUsername");
        Auth auth = authService.getAuthByLogin(username);
        Set<GrantedAuthority> role = new HashSet<>();
        SimpleGrantedAuthority authority = new SimpleGrantedAuthority("ROLE_" + auth.getType().name());
        /*role.add(new GrantedAuthority() {
            @Override
            public String getAuthority() {
                return "ROLE_" + auth.getType().name();
            }
        });*/
        role.add(authority);
        boolean isEnable = auth.getEnabled() == 1;

        logger.debug("Auth: " + auth.getLogin() + "; " + auth.getPasswd() + "; " + auth.getType());

        UserDetails userDetails = new User(auth.getLogin(), auth.getPasswd(), isEnable, true, true, true, role);

        return userDetails;
    }
}
