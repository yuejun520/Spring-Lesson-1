package com.hbnu.pojo;

/**
 * @author luanhao
 * @date 2023年03月08日 10:13
 */
public class LinkMan {
//    联系人id
    private int lid;
//    联系人姓名
    private String name;
//    联系人电话
    private String tel;
//    联系人性别
    private String gender;

    private Customer customer;

    public int getLid() {
        return lid;
    }

    public void setLid(int lid) {
        this.lid = lid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    @Override
    public String toString() {
        return "LinkMan{" +
                "lid=" + lid +
                ", name='" + name + '\'' +
                ", tel='" + tel + '\'' +
                ", gender='" + gender + '\'' +
                ", customer=" + customer +
                '}';
    }
}
