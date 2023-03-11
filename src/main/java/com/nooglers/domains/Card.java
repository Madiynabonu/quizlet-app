package com.nooglers.domains;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Entity( name = "card" )
@Table
@Builder
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Card extends BaseDomain implements BaseEntity {
    @CreationTimestamp
    @Column( columnDefinition = "timestamp default current_timestamp" )
    private transient LocalDateTime createdAt;
    @CreationTimestamp
    @Column( columnDefinition = "timestamp with time zone", name = "updated_at" )
    private transient LocalDateTime updatedAt;
    @Column( columnDefinition = "smallint default 0" )
    private short deleted;
    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY )
    private Integer id;
    @ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    private Module module;
    @Column( nullable = false )
    private String title;
    @Column( nullable = false )
    private String description;
    @OneToOne( cascade = {CascadeType.ALL} )
    @JoinColumn( name = "document_id" )
    private Document document;
}