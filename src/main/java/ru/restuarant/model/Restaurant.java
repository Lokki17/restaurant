package ru.restuarant.model;

import javax.persistence.*;
import java.util.List;

@NamedQueries({

})
@Entity
@Table(name = "restaurants")
public class Restaurant extends NamedEntity{

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "restaurant")
    private List<Dish> dishs;

    public List<Dish> getDishs() {
        return dishs;
    }

    public void setDishs(List<Dish> dishs) {
        this.dishs = dishs;
    }
}
