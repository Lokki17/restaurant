package ru.restaurant.model;

import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

@NamedQueries({
        @NamedQuery(name = User.GET_ALL, query = "SELECT u FROM User u"),
        @NamedQuery(name = User.DELETE, query = "DELETE FROM User u WHERE u.id=:userId"),
        @NamedQuery(name = User.GET, query = "SELECT u FROM User u WHERE u.id=:userId")
})
@Entity
@Table(name = "users")
public class User extends NamedEntity{
    public static final String GET_ALL = "User.getAll";
    public static final String DELETE = "User.delete";
    public static final String GET = "User.get";

    @Column(name = "password", nullable = false)
    @NotEmpty
    private String password;

    @Enumerated(EnumType.STRING)
    @CollectionTable(name = "user_roles", joinColumns = @JoinColumn(name = "user_id"))
    @Column(name = "role")
    @ElementCollection(fetch = FetchType.LAZY)
    private Set<Role> role;

    @OneToMany(mappedBy = "user")
    private List<Voice> voices;

    public boolean isAdmin(){
        return role.contains(Role.ADMIN);
    }

    public Set<Role> getRole() {
        return role;
    }

    public void setRole(Set<Role> role) {
        this.role = role;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<Voice> getVoices() {
        return voices;
    }

    public void setVoices(List<Voice> voices) {
        this.voices = voices;
    }
}
