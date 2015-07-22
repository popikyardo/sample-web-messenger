package com.intech.jpa;

import javax.persistence.*;
import java.util.List;

/**
 * Created by popikyardo on 22.07.15.
 */
@Entity
@Table(name = "system_role")
public class SystemRole {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column
    private String name;

    @ManyToMany(fetch = FetchType.LAZY, mappedBy = "userRole", cascade = CascadeType.ALL)
    private List<User> users;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }
}
