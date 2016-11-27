package ru.restaurant.model;

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
        if (!(o instanceof BaseEntity)){
            return -1;
        }
        NamedEntity that = (NamedEntity) o;

        if (Objects.equals(this.id, that.getId()) && Objects.equals(this.name, that.getName())){
            return 0;
        } else return -1;
    }
}
