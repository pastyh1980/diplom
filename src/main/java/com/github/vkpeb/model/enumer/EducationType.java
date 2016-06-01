package com.github.vkpeb.model.enumer;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by pasty on 03.04.2016.
 */
public enum EducationType {

    HIGH ("Высшее"),
    MIDDLE ("Среднее"),
    MIDDLESPEC ("Среднее специальное"),
    INCOMPLETEMIDDLE ("Незаконченное среднее");

    String rus;

    EducationType(String rus){
        this.rus = rus;
    }

    public String getRus() {
        return rus;
    }

    public static List<String> getRusList() {
        List<String> rusList = new ArrayList<>();
        for (EducationType eng: EducationType.values()) {
            rusList.add(eng.rus);
        }

        return rusList;
    }

    public static EducationType getByRus(String rus) {
        for (EducationType eng: EducationType.values()) {
            if (eng.rus.equals(rus)) return eng;
        }

        return null;
    }
}
