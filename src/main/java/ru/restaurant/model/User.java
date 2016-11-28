package ru.restaurant.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

@NamedQueries({
        @NamedQuery(name = User.GET_ALL, query = "SELECT DISTINCT u FROM User u LEFT JOIN FETCH u.roles"),
//        @NamedQuery(name = User.GET_ALL, query = "SELECT DISTINCT u FROM User u left JOIN FETCH u.voices LEFT JOIN FETCH u.roles"),
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

/*    @OneToMany(mappedBy = "user")
    //@JsonIgnore
    private List<Voice> voices;*/

    public boolean isAdmin(){
        return roles.contains(Role.ADMIN);
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

/*    public List<Voice> getVoices() {
        return voices;
    }

    public void setVoices(List<Voice> voices) {
        this.voices = voices;
    }*/
}
