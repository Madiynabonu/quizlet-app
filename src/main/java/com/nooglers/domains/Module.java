package com.nooglers.domains;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity( name = "module" )
@Builder
//@Data
@Getter
@Setter

@NoArgsConstructor
@AllArgsConstructor
public class Module implements BaseEntity {

    @Column( name = "created_at", columnDefinition = "timestamp with time zone default current_timestamp", nullable = false )
    @CreationTimestamp
    private LocalDateTime createdAt;

    @Column( name = "updated_at", columnDefinition = "timestamp with time zone" )

    private LocalDateTime updatedAt;
    @Column( columnDefinition = "smallint default 0" )
    private short deleted;
    @OneToOne
    private User createdBy;
    @ManyToMany( cascade = CascadeType.ALL, fetch = FetchType.LAZY )
    @JoinTable( name = "folder_module",
            joinColumns = {@JoinColumn( name = "module" )},
            inverseJoinColumns = {@JoinColumn( name = "folder" )}
    )
    @Builder.Default
    private Set<Folder> folders = new HashSet<>();
    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY )
    private Integer id;
    @Column( nullable = false )
    private String name;

    @Column( name = "is_public", columnDefinition = "bool default true", nullable = false )
    private boolean isPublic;

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER,mappedBy = "modules")
    @Builder.Default
    private List<Class> classes=new ArrayList<>();

    @Column( name = "last_seen" )
    private LocalDateTime lastSeen;


    private String description;

}
