package com.github.vkpeb.model.enumer;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by pasty on 19.04.2016.
 */
public enum FamilyStatuses {

    LARGEFAMILY ("Многодетные семьи"),
    LOSSPARENT ("Неполные семьи (потеря одного из родителей)"),
    DIVORCED ("Неполные семьи (родители в разводе)"),
    SINGLEMOTHER("Неполные семьи (мать-одиночка)"),
    DYSFUNCTIONALFAMILY ("Неблагополучные семью (семьи, стоящие на учете в КДН, ОДН)"),
    NEEDYFAMILY ("Малообеспеченые семьи"),
    ORPHAN ("Дети, проживающие в детском доме"),
    WITHOUTCARE ("Дети-сироты, оставшиеся без попечения родителей"),
    REFUGEES ("Дети из семей беженцев и переселенцев"),
    RISKGROUP ("Дети, входящие в группу «риска», склонные к правонарушениям(стоящие на учете в КДН, ОДН)");

    String rus;

    FamilyStatuses(String rus) {
        this.rus = rus;
    }

    public String getRus() {
        return rus;
    }

    public static List<String> getRusList() {
        List<String> rusList = new ArrayList<>();
        for (FamilyStatuses eng: FamilyStatuses.values()) {
            rusList.add(eng.rus);
        }

        return rusList;
    }

    public static FamilyStatuses getByRus(String rus) {
        for (FamilyStatuses eng: FamilyStatuses.values()) {
            if (eng.rus.equals(rus)) return eng;
        }

        return null;
    }
}
