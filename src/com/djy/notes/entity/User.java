package com.djy.notes.entity;

public class User {

    private int userId;          //用户id

    private String userName;     //用户名

    private String password;     //密码

    private String telephone;    //电话号码

    private String email;        //邮箱地址

    private String sign;         //个性签名

    private Boolean isblack;     //拉黑

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSign() {
        return sign;
    }

    public void setSign(String sign) {
        this.sign = sign;
    }

    public Boolean getIsblack() {
        return isblack;
    }

    public void setIsblack(Boolean isblack) {
        this.isblack = isblack;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}
