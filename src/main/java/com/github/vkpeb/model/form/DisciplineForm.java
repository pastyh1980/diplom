package com.github.vkpeb.model.form;

/**
 * Created by pasty on 28.05.2016.
 */
public class DisciplineForm {

    private long id;
    private String disciplineName;
    private String prepod;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getDisciplineName() {
        return disciplineName;
    }

    public void setDisciplineName(String disciplineName) {
        this.disciplineName = disciplineName;
    }

    public String getPrepod() {
        return prepod;
    }

    public void setPrepod(String prepod) {
        this.prepod = prepod;
    }
}
