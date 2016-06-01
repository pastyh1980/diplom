package com.github.vkpeb.model.enumer;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by pasty on 03.04.2016.
 */
public enum ParentType {

    FATHER ("Отец"),
    MOTHER ("Мать"),
    GUARDIAN ("Опекун");

    String rus;

    ParentType(String rus) {
        this.rus = rus;
    }

    public String getRus() {
        return rus;
    }

    public static List<String> getRusList() {
        List<String> rusList = new ArrayList<>();
        for (ParentType eng: ParentType.values()) {
            rusList.add(eng.rus);
        }

        return rusList;
    }

    public static ParentType getByRus(String rus) {
        for (ParentType eng: ParentType.values()) {
            if (eng.rus.equals(rus)) return eng;
        }

        return null;
    }
}
