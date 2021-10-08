package com.liangma.migration.pluginTest.domain;

import com.liangma.migration.annotation.*;

@Comment("this is a student")
@Table
public class Student {

    @Key(autoIncrement = true)
    @Comment("唯一标识符")
    private long id;

    @Comment("姓名")
    @Required
    private String name;

    @Comment("年龄")
    @Required
    private int age;

    @MaxLength(50)
    @Required
    private String address;

    @Comment("性别")
    @Required
    private Sex Sex;

    @Comment("个人简介")
    @MaxLength(255)
    @Required
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

    public Sex getSex() {
        return Sex;
    }

    public void setSex(Sex sex) {
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
