package com.traffic.api.models.users;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "roles")
public class Role {
    @Id
    @Column(name = "id",unique=true, nullable = false)
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Integer id;
    private String role;
    private String roleName;
    @ManyToMany(mappedBy = "roles", fetch = FetchType.LAZY)
    private List<User> users;


    public Role() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public Role(List<User> users) {
        this.users = users;
    }

}
