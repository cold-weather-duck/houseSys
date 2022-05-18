package com.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;
import java.io.Serializable;
import java.util.Date;

public class House implements Serializable{
    private Integer id;

    private Integer userId;

    private Integer typeId;

    private String title;

    private String description;

    private Integer price;

    @DateTimeFormat(pattern = "yyyy-MM-dd-HH-mm-ss") //进
    @JsonFormat(pattern = "yyyy-MM-dd-HH-mm-ss" , timezone = "GMT+8") //出
    private Date pubdate;

    private Integer floorage;

    private String contact;

    private Integer streetId;

    private Users users;//房屋用户

    private Street street;//房屋街道

    private Type2 type2;//房屋类型

    public Users getUsers() {
        return users;
    }

    public void setUsers(Users users) {
        this.users = users;
    }

    public Street getStreet() {
        return street;
    }

    public void setStreet(Street street) {
        this.street = street;
    }

    public Type2 getType2() {
        return type2;
    }

    public void setType2(Type2 type2) {
        this.type2 = type2;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getTypeId() {
        return typeId;
    }

    public void setTypeId(Integer typeId) {
        this.typeId = typeId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title == null ? null : title.trim();
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description == null ? null : description.trim();
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public Date getPubdate() {
        return pubdate;
    }

    public void setPubdate(Date pubdate) {
        this.pubdate = pubdate;
    }

    public Integer getFloorage() {
        return floorage;
    }

    public void setFloorage(Integer floorage) {
        this.floorage = floorage;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact == null ? null : contact.trim();
    }

    public Integer getStreetId() {
        return streetId;
    }

    public void setStreetId(Integer streetId) {
        this.streetId = streetId;
    }

    @Override
    public String toString() {
        return "House{" +
                "id=" + id +
                ", userId=" + userId +
                ", typeId=" + typeId +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", price=" + price +
                ", pubdate=" + pubdate +
                ", floorage=" + floorage +
                ", contact='" + contact + '\'' +
                ", streetId=" + streetId +
                '}';
    }
}