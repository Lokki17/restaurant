package ru.restaurant.model;

import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import java.time.LocalDate;

@MappedSuperclass
public class DatedEntity extends BaseEntity {
    @Column(name = "date_time")
    @NotEmpty
    protected LocalDate dateTime;

    public LocalDate getDate() {
        return dateTime;
    }

    public void setDateTime(LocalDate dateTime) {
        this.dateTime = dateTime;
    }
}
