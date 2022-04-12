package com.debugteam.auction_test.database.entities;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.time.LocalDateTime;

public class BetEntity {
//    @Id
//    @GeneratedValue(generator = "system-uuid")
//    @GenericGenerator(name = "system-uuid", strategy = "uuid") // генерация id
//    @Column(length = 32, updatable = false, nullable = false)
//    private String id; // primary key in data base
//
//    int betSize;
//
//    @CreationTimestamp
//    @Column(columnDefinition = "TIMESTAMP") // генерация даты
//    private LocalDateTime addDate;
}
