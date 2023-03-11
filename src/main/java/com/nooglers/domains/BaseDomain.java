package com.nooglers.domains;

import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;


@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public abstract class BaseDomain {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @CreationTimestamp
    @Column(name = "created_at", nullable = false, columnDefinition = "timestamp with time zone default current_timestamp  ")
    private LocalDateTime createdAt;

    @CreationTimestamp
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;


    @Column(columnDefinition = "smallint default 0", nullable = false)
    private short deleted;


    @Column(name = "updated_by")
    @OneToOne
    private User updatedBy;
}

