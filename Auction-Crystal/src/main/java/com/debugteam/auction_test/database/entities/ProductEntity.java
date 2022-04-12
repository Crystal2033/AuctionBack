package com.debugteam.auction_test.database.entities;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Generated;
import org.hibernate.annotations.GenerationTime;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Getter
@Setter
@Table(name="product")
public class ProductEntity {
    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid") // генерация id
    @Column(length = 32, updatable = false, nullable = false)
    private String id; // primary key in data base

    String name;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private AccountEntity user;

    @Generated(GenerationTime.INSERT) // Серийник для человека.
    private Integer serial;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ProductEntity productEntity = (ProductEntity) o;
        return Objects.equals(id, productEntity.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
