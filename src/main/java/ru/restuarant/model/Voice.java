package ru.restuarant.model;

import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import java.time.LocalDateTime;

@NamedQueries({
    @NamedQuery(name = Voice.GET_ALL, query = "SELECT v FROM Voice v WHERE v.dateTime=:dateTime")
})
@Entity
@Table(name = "voices")
public class Voice extends DatedEntity {
    public static final String GET_ALL = "Voice.getAll";
    //public static final String SAVE = "Voice.save";

    @Id
    @SequenceGenerator(name = "global_seq", sequenceName = "global_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "global_seq")
    private Integer id;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "dishes_id", unique = true, nullable = false)
    private Dish dish;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "user_id")
    private User user;

    @Column(name = "name", nullable = false)
    @NotEmpty
    private String name;

    @Column(name = "date_time", nullable = false)
    @NotEmpty
    private LocalDateTime dateTime;

    public Dish getDish() {
        return dish;
    }

    public void setDish(Dish dish) {
        this.dish = dish;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }
}
