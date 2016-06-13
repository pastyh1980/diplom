package com.github.vkpeb.model.form;

import com.github.vkpeb.model.Order;
import com.github.vkpeb.model.enumer.FamilyStatuses;
import com.github.vkpeb.model.enumer.LivingConditions;
import com.github.vkpeb.model.enumer.Posts;

import java.util.List;

/**
 * Created by pasty on 19.04.2016.
 */
public class StudentForm {

    private long id;
    private String studFamily;
    private String studName;
    private String studOtchestvo;
    private String studBorn;
    private String studPhone;
    private String livingCondition;
    private String familyStatus;
    private String post;
    private String address;
    private List<ParentForm> parents;
    private List<OrderForm> orders;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getStudFamily() {
        return studFamily;
    }

    public void setStudFamily(String studFamily) {
        this.studFamily = studFamily;
    }

    public String getStudName() {
        return studName;
    }

    public void setStudName(String studName) {
        this.studName = studName;
    }

    public String getStudOtchestvo() {
        return studOtchestvo;
    }

    public void setStudOtchestvo(String studOtchestvo) {
        this.studOtchestvo = studOtchestvo;
    }

    public String getStudBorn() {
        return studBorn;
    }

    public void setStudBorn(String studBorh) {
        this.studBorn = studBorh;
    }

    public String getStudPhone() {
        return studPhone;
    }

    public void setStudPhone(String studPhone) {
        this.studPhone = studPhone;
    }

    public String getLivingCondition() {
        return livingCondition;
    }

    public void setLivingCondition(String livingCondition) {
        this.livingCondition = livingCondition;
    }

    public String getFamilyStatus() {
        return familyStatus;
    }

    public void setFamilyStatus(String familyStatus) {
        this.familyStatus = familyStatus;
    }

    public String getPost() {
        return post;
    }

    public void setPost(String post) {
        this.post = post;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public List<ParentForm> getParents() {
        return parents;
    }

    public void setParents(List<ParentForm> parents) {
        this.parents = parents;
    }

    public List<OrderForm> getOrders() {
        return orders;
    }

    public void setOrders(List<OrderForm> orders) {
        this.orders = orders;
    }
}
