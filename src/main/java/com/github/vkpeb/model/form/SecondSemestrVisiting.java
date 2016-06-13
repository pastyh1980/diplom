package com.github.vkpeb.model.form;

/**
 * Created by pasty on 04.06.2016.
 */
public class SecondSemestrVisiting {

    private int number;
    private String studFio;
    private int januaryValid;
    private int januaryInvalid;
    private int februaryValid;
    private int februaryInvalid;
    private int martValid;
    private int martInvalid;
    private int aprilValid;
    private int aprilInvalid;
    private int mayValid;
    private int mayInvalid;
    private int totalValid;
    private int totalInvalid;

    public SecondSemestrVisiting() {
        this.januaryValid = 0;
        this.januaryInvalid = 0;
        this.februaryValid = 0;
        this.februaryInvalid = 0;
        this.martValid = 0;
        this.martInvalid = 0;
        this.aprilValid = 0;
        this.aprilInvalid = 0;
        this.mayValid = 0;
        this.mayInvalid = 0;
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

    public int getJanuaryValid() {
        return januaryValid;
    }

    public void setJanuaryValid(int januaryValid) {
        this.januaryValid = januaryValid;
    }

    public int getJanuaryInvalid() {
        return januaryInvalid;
    }

    public void setJanuaryInvalid(int januaryInvalid) {
        this.januaryInvalid = januaryInvalid;
    }

    public int getFebruaryValid() {
        return februaryValid;
    }

    public void setFebruaryValid(int februaryValid) {
        this.februaryValid = februaryValid;
    }

    public int getFebruaryInvalid() {
        return februaryInvalid;
    }

    public void setFebruaryInvalid(int februaryInvalid) {
        this.februaryInvalid = februaryInvalid;
    }

    public int getMartValid() {
        return martValid;
    }

    public void setMartValid(int martValid) {
        this.martValid = martValid;
    }

    public int getMartInvalid() {
        return martInvalid;
    }

    public void setMartInvalid(int martInvalid) {
        this.martInvalid = martInvalid;
    }

    public int getAprilValid() {
        return aprilValid;
    }

    public void setAprilValid(int aprilValid) {
        this.aprilValid = aprilValid;
    }

    public int getAprilInvalid() {
        return aprilInvalid;
    }

    public void setAprilInvalid(int aprilInvalid) {
        this.aprilInvalid = aprilInvalid;
    }

    public int getMayValid() {
        return mayValid;
    }

    public void setMayValid(int mayValid) {
        this.mayValid = mayValid;
    }

    public int getMayInvalid() {
        return mayInvalid;
    }

    public void setMayInvalid(int mayInvalid) {
        this.mayInvalid = mayInvalid;
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

    public void setTotalInvalid(int totalInvalid) {
        this.totalInvalid = totalInvalid;
    }
}
