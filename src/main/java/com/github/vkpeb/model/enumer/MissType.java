package com.github.vkpeb.model.enumer;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by pasty on 03.04.2016.
 */
public enum MissType {

    VALID ("уважительная"), INVALID ("прогул");

    String rus;

    MissType(String rus) {
        this.rus = rus;
    }

    public String getRus() {
        return rus;
    }

    public static List<String> getRusList() {
        List<String> rusList = new ArrayList<>();
        for (MissType eng: MissType.values()) {
            rusList.add(eng.rus);
        }

        return rusList;
    }

    public static MissType getByRus(String rus) {
        for (MissType eng: MissType.values()) {
            if (eng.rus.equals(rus)) return eng;
        }

        return null;
    }
}
