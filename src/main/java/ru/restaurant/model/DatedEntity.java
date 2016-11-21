package ru.restaurant.model;

import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import java.time.LocalDate;

@MappedSuperclass
public class DatedEntity extends BaseEntity {
    @Column(name = "date_time")
    @NotEmpty
    protected LocalDate date;

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate dateTime) {
        this.date = dateTime;
    }
}
