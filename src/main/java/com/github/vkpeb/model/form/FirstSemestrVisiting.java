package com.github.vkpeb.model.form;

/**
 * Created by pasty on 02.06.2016.
 */
public class FirstSemestrVisiting {

    private int number;
    private String studFio;
    private int septemberValid;
    private int septemberInvalid;
    private int octoberValid;
    private int octoberInvalid;
    private int novemberValid;
    private int novemberInvalid;
    private int decemberValid;
    private int decemberInvalid;
    private int totalValid;
    private int totalInvalid;

    public FirstSemestrVisiting() {
        this.septemberValid = 0;
        this.septemberInvalid = 0;
        this.octoberValid = 0;
        this.octoberInvalid = 0;
        this.novemberValid = 0;
        this.novemberInvalid = 0;
        this.decemberValid = 0;
        this.decemberInvalid = 0;
        this.totalValid = 0;
        this.totalInvalid = 0;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public String getStudFio() {
        return studFio;
    }

    public void setStudFio(String studFio) {
        this.studFio = studFio;
    }

    public int getSeptemberValid() {
        return septemberValid;
    }

    public void setSeptemberValid(int septemberValid) {
        this.septemberValid = septemberValid;
    }

    public int getSeptemberInvalid() {
        return septemberInvalid;
    }

    public void setSeptemberInvalid(int septemberInvalid) {
        this.septemberInvalid = septemberInvalid;
    }

    public int getOctoberValid() {
        return octoberValid;
    }

    public void setOctoberValid(int octoberValid) {
        this.octoberValid = octoberValid;
    }

    public int getOctoberInvalid() {
        return octoberInvalid;
    }

    public void setOctoberInvalid(int octoberInvalid) {
        this.octoberInvalid = octoberInvalid;
    }

    public int getNovemberValid() {
        return novemberValid;
    }

    public void setNovemberValid(int novemberValid) {
        this.novemberValid = novemberValid;
    }

    public int getNovemberInvalid() {
        return novemberInvalid;
    }

    public void setNovemberInvalid(int novemberInvalid) {
        this.novemberInvalid = novemberInvalid;
    }

    public int getDecemberValid() {
        return decemberValid;
    }

    public void setDecemberValid(int decemberValid) {
        this.decemberValid = decemberValid;
    }

    public int getDecemberInvalid() {
        return decemberInvalid;
    }

    public void setDecemberInvalid(int decemberInvalid) {
        this.decemberInvalid = decemberInvalid;
    }

    public int getTotalValid() {
        return totalValid;
    }

    public void setTotalValid(int totalValid) {
        this.totalValid = totalValid;
    }

    public int getTotalInvalid() {
        return totalInvalid;
    }

    public void setTotalInvalid(int totalInalid) {
        this.totalInvalid = totalInalid;
    }
}
