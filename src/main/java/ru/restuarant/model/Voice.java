package ru.restuarant.model;

import javax.persistence.*;

@NamedQueries({

})
@Entity
@Table(name = "voices")
public class Voice extends DatedEntity {
    @Column(name = "restaurant_id")
    private int restaurantId;

    @Column(name = "user_id")
    private int userId;

    public int getRestaurantId() {
        return restaurantId;
    }

    public void setRestaurantId(int restaurantId) {
        this.restaurantId = restaurantId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }
}
