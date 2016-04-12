package com.github.vkpeb.controller;

import com.github.vkpeb.model.Auth;
import com.github.vkpeb.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.util.UriComponentsBuilder;

/**
 * Created by pasty on 12.04.2016.
 */
@Controller
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private AuthService authService;

    public void setAuthService(AuthService authService) {
        this.authService = authService;
    }

    @RequestMapping(value = "/regUser", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> addUser(@RequestBody Auth auth, UriComponentsBuilder ucBuilder) {
        Auth savedAuth = authService.addAuth(auth);
        if (savedAuth == null) return new ResponseEntity<>(HttpStatus.CONFLICT);
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setLocation(ucBuilder.path("/auth/{id}").buildAndExpand(savedAuth.getId()).toUri());
        return new ResponseEntity<>(httpHeaders, HttpStatus.CREATED);
    }
}
