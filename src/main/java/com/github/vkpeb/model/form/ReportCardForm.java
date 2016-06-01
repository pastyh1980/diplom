package com.github.vkpeb.model.form;

import com.github.vkpeb.model.Discipline;
import com.github.vkpeb.model.Student;
import com.github.vkpeb.model.enumer.Month;
import org.apache.commons.collections4.Factory;
import org.apache.commons.collections4.MapUtils;

import java.util.*;

/**
 * Created by pasty on 26.05.2016.
 */
public class ReportCardForm {

    private Month month;
    private Integer year;
    private Map<Long, Row> rows;

    public ReportCardForm() {
        this.rows = MapUtils.lazyMap(new HashMap<Long, Row>(), new Factory<Row>() {
            @Override
            public Row create() {
                return new Row();
            }
        });
    }

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

    public Map<Long, Row> getRows() {
        return rows;
    }

    public void setRows(Map<Long, Row> rows) {
        this.rows = rows;
    }

    public static class Rate {
        private long id;
        private String rate;

        public Rate() {
            this.rate = "";
        }

        public long getId() {
            return id;
        }

        public void setId(long id) {
            this.id = id;
        }

        public String getRate() {
            return rate;
        }

        public void setRate(String rate) {
            this.rate = rate;
        }
    }

    public static class Row extends HashMap<String, Rate> {
    }
}
