package com.hbnu.pojo;

import java.util.HashSet;
import java.util.Set;

/**
 * @author luanhao
 * @date 2023年03月13日 22:00
 */
public class Role {
    private int rid; // 角色id
    private String name;
    private String description;

    Set<Player> players = new HashSet<>();

    public int getRid() {
        return rid;
    }

    public void setRid(int rid) {
        this.rid = rid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Set<Player> getPlayers() {
        return players;
    }

    public void setPlayers(Set<Player> players) {
        this.players = players;
    }

    @Override
    public String toString() {
        return "Role{" +
                "rid=" + rid +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", players=" + players +
                '}';
    }
}
