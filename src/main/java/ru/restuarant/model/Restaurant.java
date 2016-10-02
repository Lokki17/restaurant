package ru.restuarant.model;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "restaurants")
public class Restaurant extends NamedEntity{
}
