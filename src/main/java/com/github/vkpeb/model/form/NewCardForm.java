package com.github.vkpeb.model.form;

import com.github.vkpeb.model.enumer.Month;

/**
 * Created by pasty on 27.05.2016.
 */
public class NewCardForm {

    private Month month;
    private Integer year;

    public Month getMonth() {
        return month;
    }

    public void setMonth(Month month) {
        this.month = month;
    }

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }
}
