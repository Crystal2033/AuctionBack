package com.debugteam.auction_test.database.entities;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.Generated;
import org.hibernate.annotations.GenerationTime;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

@Entity
@Getter
@Setter
@Table(name = "lot")
public class LotEntity {

    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid") // генерация id
    @Column(length = 32, updatable = false, nullable = false)
    private String id; // primary key in data base

    String name; //
    int startPrice;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private AccountEntity user;

    @CreationTimestamp
    @Column(columnDefinition = "TIMESTAMP") // генерация даты
    private LocalDateTime addDate;

    @Generated(GenerationTime.INSERT) // Серийник для человека.
    private Integer serial;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "lot")
    private List<ProductEntity> lotProducts;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "lot")
    private List<BetEntity> lotBets;

    //private Product product;


    ///////////////////////////////////////////////////////////////////////////
    //                          equals + hash
    ///////////////////////////////////////////////////////////////////////////
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        LotEntity lotEntity = (LotEntity) o;
        return Objects.equals(id, lotEntity.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
