package com.liangma.migration.test;

import com.liangma.migration.annotation.*;

import javax.activation.DataSource;


//@Comment("this is a student")
@Table
public class Student {

    @Comment("唯一标识符")
    private long id;
    private Long pLong;

    private int pInt;
    private Integer pInteger;

    private double pDouble;
    private Double pDouble2;

    private short pShort;
    private Short pShort2;

    private boolean pBoolean;
    private Boolean pBoolean2;


    @Comment("姓名")
    @Required
    private String name;

    @Comment("年龄")
    private int age;

    @MaxLength(50)
    private String address;

    @Comment("性别")
    private Sex Sex;

    @Comment("个人简介")
    @MaxLength(255)
    private String profile;

    @NotMapped
    public int School;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public com.liangma.migration.test.Sex getSex() {
        return Sex;
    }

    public void setSex(com.liangma.migration.test.Sex sex) {
        Sex = sex;
    }

    public String getProfile() {
        return profile;
    }

    public void setProfile(String profile) {
        this.profile = profile;
    }

    public int getSchool() {
        return School;
    }

    public void setSchool(int school) {
        School = school;
    }

    private void hello() {

    }
}


enum Sex {
    Man,
    Woman
}
