package com.github.vkpeb.controller;

import com.github.vkpeb.model.Auth;
import com.github.vkpeb.service.AuthService;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

/**
 * Created by pasty on 13.04.2016.
 */
@Controller
@RequestMapping("/")
public class MainController implements ApplicationContextAware {

    private static ApplicationContext applicationContext;

    @RequestMapping(value = {"", "/welcome"}, method = RequestMethod.GET)
    public ModelAndView defaultPage() {
        ModelAndView response = new ModelAndView();
        response.addObject("pageName", "default page");
        getUserName(response);
        response.setViewName("welcome");
        return response;
    }

    @RequestMapping(value = "/admin", method = RequestMethod.GET)
    public ModelAndView admin() {
        ModelAndView response = new ModelAndView();
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        UserDetails userDetails = (UserDetails) auth.getPrincipal();
        response.addObject("pageName", "admin page");
        getUserName(response);
        response.setViewName("welcome");
        return response;
    }

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public ModelAndView login(@RequestParam(value = "error", required = false) String error,
                              @RequestParam(value = "logout", required = false) String logout) {
        ModelAndView response = new ModelAndView();

        if(error != null) response.addObject("error", "Error 403");

        if(logout != null) response.addObject("msg", "Logout successful!");

        getUserName(response);

        response.setViewName("login");

        return response;
    }

    @RequestMapping(value = "/error", method = RequestMethod.GET)
    public ModelAndView error() {
        ModelAndView response = new ModelAndView();

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if(!(auth instanceof AnonymousAuthenticationToken)) {
            UserDetails userDetails = (UserDetails) auth.getPrincipal();
            response.addObject("username", userDetails.getUsername());
        }
        getUserName(response);
        response.setViewName("error");
        return response;
    }

    public static void getUserName(ModelAndView modelAndView) {
        AuthService authService = applicationContext.getBean(AuthService.class);
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Auth auth = authService.getAuthByLogin(authentication.getName());
        if (auth != null)
            modelAndView.addObject("userName", authService.getUserName(auth));
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }
}
