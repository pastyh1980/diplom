package com.github.vkpeb.model.form;

import com.github.vkpeb.model.enumer.MissType;
import com.github.vkpeb.model.enumer.Month;
import org.apache.commons.collections4.Factory;
import org.apache.commons.collections4.MapUtils;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by pasty on 29.05.2016.
 */
public class VisitingCardForm {

    private Month month;
    private Integer year;
    private Map<Long, Row> rows;

    public VisitingCardForm() {
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

    public static class Row extends HashMap<String, Miss> {
    }

    public static class Miss {
        private long id;
        private Integer hour;
        private String missType;

        public Miss() {
            this.hour = 0;
            this.missType = MissType.VALID.getRus();
        }

        public long getId() {
            return id;
        }

        public void setId(long id) {
            this.id = id;
        }

        public Integer getHour() {
            return hour;
        }

        public void setHour(Integer hour) {
            this.hour = hour;
        }

        public String getMissType() {
            return missType;
        }

        public void setMissType(String missType) {
            this.missType = missType;
        }
    }
}
