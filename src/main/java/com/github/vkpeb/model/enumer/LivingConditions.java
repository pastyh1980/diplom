package com.github.vkpeb.model.enumer;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by pasty on 19.04.2016.
 */
public enum LivingConditions {

    DORMITORY ("Проживает в коммунальной кв. (общежитии)"),
    APARTMENT ("Проживает в отдельной квартире"),
    RENT ("Арендует жилье"),
    COTTAGE ("Проживает в частном доме");

    String rus;

    LivingConditions(String rus) {
        this.rus = rus;
    }

    public String getRus() {
        return rus;
    }

    public static List<String> getRusList() {
        List<String> rusList = new ArrayList<>();
        for (LivingConditions eng: LivingConditions.values()) {
            rusList.add(eng.rus);
        }

        return rusList;
    }

    public static LivingConditions getByRus(String rus) {
        for (LivingConditions eng: LivingConditions.values()) {
            if (eng.rus.equals(rus)) return eng;
        }

        return null;
    }
}
