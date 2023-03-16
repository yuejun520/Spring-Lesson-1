package com.hbnu.pojo;

/**
 * @author luanhao
 * @date 2023年02月19日 23:14
 */
public class User {
    private int uId;
    private String username;
    private String address;

    public int getuId() {
        return uId;
    }

    public void setuId(int uId) {
        this.uId = uId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public String toString() {
        return "User{" +
                "uId=" + uId +
                ", username='" + username + '\'' +
                ", address='" + address + '\'' +
                '}';
    }
}
