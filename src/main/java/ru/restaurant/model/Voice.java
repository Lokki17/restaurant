package ru.restaurant.model;

import javax.persistence.*;

@NamedQueries({
        @NamedQuery(name = Voice.GET_ALL, query = "SELECT v FROM Voice v WHERE v.dateTime=:dateTime"),
        @NamedQuery(name = Voice.DELETE, query = "DELETE FROM Voice v WHERE v.id=:voiceId"),
        @NamedQuery(name = Voice.GET, query = "SELECT v FROM Voice v WHERE v.id=:dishId AND v.dateTime=:dateTime")
})
@Entity
@Table(name = "voices")
public class Voice extends DatedEntity {
    public static final String GET_ALL = "Voice.getAll";
    public static final String DELETE = "Voice.delete";
    public static final String GET = "Voice.delete";

/*    @Id
    @SequenceGenerator(name = "global_seq", sequenceName = "global_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "global_seq")
    private Integer id;*/

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "dish_id", unique = true, nullable = false)
    private Dish dish;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "user_id")
    private User user;

/*    @Column(name = "date_time", nullable = false)
    @NotEmpty
    private LocalDateTime dateTime;*/

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

/*    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }*/
}
