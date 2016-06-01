package com.github.vkpeb.model.enumer;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by pasty on 19.04.2016.
 */
public enum Posts {

    CAPTAIN ("Староста"),
    CAPTAINASSIST ("Заместитель старосты"),
    EDUCATIONSECTOR ("Член учебного сектора"),
    PATRIOTICADVICE ("Член патриотического совета"),
    CULTMASS ("Ответственный за культурно-массовую работу"),
    SPORT ("Ответственный за оздоровительную и спортивную деятельность"),
    EDITOR ("Член редколлегии"),
    LIBRARY ("Член совета библиотеки"),
    INFORMSECTOR ("Информационный сектор"),
    MUSEUM ("Член совета музея"),
    WORK ("Член трудового совета");

    String rus;

    Posts(String rus) {
        this.rus = rus;
    }

    public String getRus() {
        return rus;
    }

    public static List<String> getRusList() {
        List<String> rusList = new ArrayList<>();
        for (Posts eng: Posts.values()) {
            rusList.add(eng.rus);
        }

        return rusList;
    }

    public static Posts getByRus(String rus) {
        for (Posts eng: Posts.values()) {
            if (eng.rus.equals(rus)) return eng;
        }

        return null;
    }
}
