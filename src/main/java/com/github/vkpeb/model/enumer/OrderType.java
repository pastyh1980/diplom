package com.github.vkpeb.model.enumer;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by pasty on 26.05.2016.
 */
public enum OrderType {

    ENROLLMENT ("зачислении"), TRANSFER ("переводе"), EXPULSION ("отчислении");

    String rus;

    OrderType(String rus) {
        this.rus = rus;
    }

    public String getRus() {
        return rus;
    }

    public static List<String> getRusList() {
        List<String> rusList = new ArrayList<>();
        for (OrderType eng: OrderType.values()) {
            rusList.add(eng.rus);
        }

        return rusList;
    }

    public static OrderType getByRus(String rus) {
        for (OrderType eng: OrderType.values()) {
            if (eng.rus.equals(rus)) return eng;
        }

        return null;
    }
}
