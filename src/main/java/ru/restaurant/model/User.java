package ru.restaurant.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import java.util.EnumSet;
import java.util.List;
import java.util.Set;

@NamedQueries({
        @NamedQuery(name = User.GET_ALL, query = "SELECT DISTINCT u FROM User u LEFT JOIN FETCH u.roles ORDER BY u.id"),
        @NamedQuery(name = User.DELETE, query = "DELETE FROM User u WHERE u.id=:userId"),
        @NamedQuery(name = User.GET, query = "SELECT u FROM User u WHERE u.id=:userId"),
        @NamedQuery(name = User.GET_BY_NAME, query = "SELECT u FROM User u WHERE u.name=:name")
})
@Entity
@Table(name = "users")
public class User extends NamedEntity{
    public static final String GET_ALL = "User.getAll";
    public static final String DELETE = "User.delete";
    public static final String GET = "User.get";
    public static final String GET_BY_NAME = "User.get.by.name";

    @Column(name = "password", nullable = false)
    @NotEmpty
    private String password;

    @Enumerated(EnumType.STRING)
    @CollectionTable(name = "user_roles", joinColumns = @JoinColumn(name = "user_id"))
    @Column(name = "role")
    @ElementCollection(fetch = FetchType.EAGER)
    private Set<Role> roles;

    public User() {
    }

    public User(Integer id, String name, String password, Role role, Role... roles) {
        this.id = id;
        this.name = name;
        this.password = password;
        this.roles = EnumSet.of(role, roles);
    }

    public User(String name, String password, Role role, Role... roles) {
        this.id = null;
        this.name = name;
        this.password = password;
        this.roles = EnumSet.of(role, roles);
    }

    public boolean isAdmin(){
        return roles.contains(Role.ROLE_ADMIN);
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> role) {
        this.roles = role;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "User (" +
                "id=" + id +
                ", name=" + name +
                ", roles=" + roles +
                ')';
    }
}
