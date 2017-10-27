package cn.lonecloud.student.pojo;

import java.util.Date;
/**
* @Title: User.java
* @Description: TODO
* @author lonecloud
* @date 2017/10/26 下午10:49
* @version V1.0
*/
public class User {
    private Integer id;

    private String username;

    private String password;

    private Boolean gender;

    private Date createTime;

    private Date updateTime;

    public User(Integer id, String username, String password, Boolean gender, Date createTime, Date updateTime) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.gender = gender;
        this.createTime = createTime;
        this.updateTime = updateTime;
    }

    public User() {
        super();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username == null ? null : username.trim();
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password == null ? null : password.trim();
    }

    public Boolean getGender() {
        return gender;
    }

    public void setGender(Boolean gender) {
        this.gender = gender;
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