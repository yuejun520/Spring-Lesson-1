package com.hbnu.pojo;

import java.util.HashSet;
import java.util.Set;

/**
 * @author luanhao
 * @date 2023年03月08日 10:11
 */
public class Customer {
//    客户id
    private int cid;
//    客户姓名
    private String name;
//    客户地址
    private String address;

    Set<LinkMan> linkManSet = new HashSet<>();

    public int getCid() {
        return cid;
    }

    public void setCid(int cid) {
        this.cid = cid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Set<LinkMan> getLinkManSet() {
        return linkManSet;
    }

    public void setLinkManSet(Set<LinkMan> linkManSet) {
        this.linkManSet = linkManSet;
    }

    @Override
    public String toString() {
        return "Customer{" +
                "cid='" + cid + '\'' +
                ", name='" + name + '\'' +
                ", address='" + address + '\'' +
                ", linkManSet=" + linkManSet +
                '}';
    }
}
