package ru.restuarant.model;

import javax.persistence.*;

@NamedQueries({

})
@Entity
@Table(name = "voices")
public class Voice extends DatedEntity {
    @OneToOne(optional = false)
    @JoinColumn(name = "dishes_id", unique = true, nullable = false)
    private Dish dish;

    @ManyToOne(optional = false)
    @JoinColumn(name = "user_id")
    private User user;

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
}
