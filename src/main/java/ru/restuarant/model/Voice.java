package ru.restuarant.model;

import javax.persistence.*;

@NamedQueries({

})
@Entity
@Table(name = "voices")
public class Voice extends DatedEntity {
    @OneToOne(optional = false)
    @JoinColumn(name = "dishes_id")
    private Dish dish;

    @Column(name = "user_id")
    private int userId;

    public Dish getDish() {
        return dish;
    }

    public void setDish(Dish dish) {
        this.dish = dish;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }
}
