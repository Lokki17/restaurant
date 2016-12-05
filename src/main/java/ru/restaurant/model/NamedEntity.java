package ru.restaurant.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import java.util.Objects;

@MappedSuperclass
public class NamedEntity extends BaseEntity implements Comparable{
    @Column(name = "name")
    @NotEmpty
    protected String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public int compareTo(Object o) {
        NamedEntity that = (NamedEntity) o;
        if (!this.name.equals(that.name)){
            return this.name.compareTo(that.name);
        } else {
            return this.id.compareTo(that.getId());
        }
    }
}
