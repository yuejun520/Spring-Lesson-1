package com.hbnu.pojo;

import java.util.HashSet;
import java.util.Set;

/**
 * @author luanhao
 * @date 2023年03月13日 21:59
 */
public class Player {
    private int pid;
    private String name;
    private String gender;

    Set<Role> roles = new HashSet<>();

    public int getPid() {
        return pid;
    }

    public void setPid(int pid) {
        this.pid = pid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }

    @Override
    public String toString() {
        return "Player{" +
                "pid=" + pid +
                ", name='" + name + '\'' +
                ", gender='" + gender + '\'' +
                ", roles=" + roles +
                '}';
    }
}
