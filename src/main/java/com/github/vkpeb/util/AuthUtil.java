package com.github.vkpeb.util;

import com.github.vkpeb.model.Auth;
import com.github.vkpeb.service.AuthService;
import com.github.vkpeb.service.impl.AuthServiceImpl;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Component;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by pasty on 06.05.2016.
 */
@Component
public class AuthUtil implements ApplicationContextAware {

    private static ApplicationContext applicationContext;

    private static List<String> cacheLogins = new ArrayList<>();

    public static String generatePassword(){
        StringBuilder password = new StringBuilder();
        Random random = new Random();

        int passwdLength = 8 + random.nextInt(5);

        for (int i = 0; i < passwdLength; ++i) {
            char next = 0;
            int range = 10;

            switch (random.nextInt(3)) {
                case 0: next = '0'; range = 10; break;
                case 1: next = 'a'; range = 26; break;
                case 2: next = 'A'; range = 26; break;
            }

            password.append((char) (next + random.nextInt(range)));
        }

        return password.toString();
    }

    public static String createLoginByName(String family, String name) {
        String newFamily = convertToLat(family);
        String newName = convertToLat(name);
        String login = newFamily + '.' + newName;

        AuthService authService = applicationContext.getBean(AuthService.class);

        Auth auth = authService.getAuthByLogin(login);
        if (auth != null || cacheLogins.contains(login)) {
            int index = 1;
            while (authService.getAuthByLogin(login + '.' + String.valueOf(index)) != null || cacheLogins.contains(login + '.' + index)) {
                ++index;
            }
            login += '.' + String.valueOf(index);
        }

        cacheLogins.add(login);

        return login;
    }

    private static String convertToLat(String source) {
        source = source.toLowerCase();

        StringBuilder target = new StringBuilder();

        for (char item : source.toCharArray()) {
            String ch;

            switch (item) {
                case 'а': ch = "a"; break;
                case 'б': ch = "b"; break;
                case 'в': ch = "v"; break;
                case 'г': ch = "g"; break;
                case 'д': ch = "d"; break;
                case 'е': ch = "e"; break;
                case 'ё': ch = "jo"; break;
                case 'ж': ch = "zh"; break;
                case 'з': ch = "z"; break;
                case 'и': ch = "i"; break;
                case 'й': ch = "y"; break;
                case 'к': ch = "k"; break;
                case 'л': ch = "l"; break;
                case 'м': ch = "m"; break;
                case 'н': ch = "n"; break;
                case 'о': ch = "o"; break;
                case 'п': ch = "p"; break;
                case 'р': ch = "r"; break;
                case 'с': ch = "s"; break;
                case 'т': ch = "t"; break;
                case 'у': ch = "u"; break;
                case 'ф': ch = "f"; break;
                case 'х': ch = "kh"; break;
                case 'ц': ch = "c"; break;
                case 'ч': ch = "ch"; break;
                case 'ш': ch = "sh"; break;
                case 'щ': ch = "jsh"; break;
                case 'ъ': ch = "hh"; break;
                case 'ы': ch = "ih"; break;
                case 'ь': ch = "jh"; break;
                case 'э': ch = "eh"; break;
                case 'ю': ch = "ju"; break;
                case 'я': ch = "ja"; break;
                default: ch = "" + item;
            }

            target.append(ch);
        }

        return target.toString();
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }
}
