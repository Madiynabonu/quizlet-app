package com.nooglers.domains;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Entity( name = "cookie" )
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class AppCookie implements BaseEntity {


    @Id
    @GeneratedValue( strategy = GenerationType.UUID )
    private String id;
    @CreationTimestamp
    @Column( name = "created_at", columnDefinition = "timestamp default now()", nullable = false )
    private LocalDateTime createdAt;

    @Column( name = "updated_at" )
    private LocalDateTime updatedAt;

    @OneToOne
    private User user;


}
