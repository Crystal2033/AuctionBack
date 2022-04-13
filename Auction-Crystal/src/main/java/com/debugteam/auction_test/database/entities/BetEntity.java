package com.debugteam.auction_test.database.entities;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@Table(name = "bet")
public class BetEntity {
    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid") // генерация id
    @Column(length = 32, updatable = false, nullable = false)
    private String id; // primary key in data base

    private int betSize;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private AccountEntity user;

    @CreationTimestamp
    @Column(columnDefinition = "TIMESTAMP") // генерация даты
    private LocalDateTime addDate;
}
