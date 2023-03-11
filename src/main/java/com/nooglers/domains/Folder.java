package com.nooglers.domains;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Entity( name = "folder" )
@Table(name = "folder")
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Folder extends BaseDomain implements BaseEntity {


    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY )
    private Integer id;
    @CreationTimestamp
    @Column( name = "created_at", columnDefinition = "timestamp with  time zone default current_timestamp", insertable = true, updatable = false )
    private LocalDateTime createdAt;

    @CreationTimestamp
    @Column( name = "updated_at", columnDefinition = "timestamp with time zone default current_timestamp", updatable = true )
    private LocalDateTime updatedAt;
    //    @Column(columnDefinition = "smallint default 0")
//    @Enumerated(EnumType.STRING)
//    @Column(nullable = false, columnDefinition = "varchar default 'CREATED'")
//    private FolderStatus status;

    @Column( columnDefinition = "smallint default 0" )
    private short deleted;

    @Column( nullable = false )
    private String title;

    @ManyToMany( cascade = CascadeType.ALL, mappedBy = "folders" )
    private Set<Module> modules = new HashSet<>();


    @Column( nullable = true )
    private String description;


    @OneToOne
    private User createdBy;

}