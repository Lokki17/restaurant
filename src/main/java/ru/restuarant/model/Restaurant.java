package ru.restuarant.model;

import javax.persistence.*;
import java.util.List;

@NamedQueries({
    @NamedQuery(name = Restaurant.GET_ALL, query = "SELECT r FROM Restaurant r")
})
@Entity
@Table(name = "restaurants")
public class Restaurant{

    public static final String GET_ALL = "Restaurant.getAll";

    @Id
    @SequenceGenerator(name = "global_seq", sequenceName = "global_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "global_seq")
    private Integer id;

    @Column(name = "name")
    private String name;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "restaurant", cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private List<Dish> dishs;

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

    public List<Dish> getDishs() {
        return dishs;
    }

    public void setDishs(List<Dish> dishs) {
        this.dishs = dishs;
    }
}
