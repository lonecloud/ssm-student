package cn.lonecloud.student.pojo;

import java.util.Date;
/**
* @Title: Student.java
* @Description: TODO
* @author lonecloud
* @date 2017/10/26 下午10:49
* @version V1.0
*/
public class Student {
    private Integer id;

    private String name;

    private Integer age;

    private String major;

    private Date createTime;

    private Date updateTime;

    public Student(Integer id, String name, Integer age, String major, Date createTime, Date updateTime) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.major = major;
        this.createTime = createTime;
        this.updateTime = updateTime;
    }

    public Student() {
        super();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getMajor() {
        return major;
    }

    public void setMajor(String major) {
        this.major = major == null ? null : major.trim();
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
}