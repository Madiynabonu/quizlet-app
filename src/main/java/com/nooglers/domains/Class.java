package com.nooglers.domains;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;


@Entity()
@Table( name = "class" )
@Builder
//@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Class implements BaseEntity {
    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY )
    private Integer id;

    @Column( nullable = false, name = "created_by" )
    private Integer createdBy;
    @Column( nullable = false )
    private String name;

    //    private Set<User> users;
    @Column
    private String description;
    @Column( name = "permission_to_invite", nullable = false, columnDefinition = "boolean default true" )
    private boolean permissionToInvite;
    @Column( name = "permission_to_update_sets", nullable = false, columnDefinition = "boolean default true" )
    private boolean permissionToUpdateSets;
    @Column( name = "school_name", nullable = false )
    private String schoolName;
    @Column( nullable = false, name = "invitation_link" )
    private String invitationLink;
    @CreationTimestamp
    @Column( columnDefinition = "timestamp default current_timestamp", name = "created_at", nullable = false )
    private LocalDateTime createdAt;
    @CreationTimestamp
    @Column( name = "updated_at" )
    private LocalDateTime updatedAt;
    @Column( columnDefinition = "smallint default 0" )
    private short deleted;

    @ManyToMany( cascade = CascadeType.ALL, fetch = FetchType.EAGER )
    @Builder.Default
//    @JoinTable(name = "class_module",
//            joinColumns = {@JoinColumn(name = "class")},
//            inverseJoinColumns = {@JoinColumn(name = "module")}
//    )
    private Set<Module> modules = new HashSet<>();

    @ManyToMany( cascade = CascadeType.MERGE, fetch = FetchType.LAZY)
    @Builder.Default
    private Set<User> users = new HashSet<>();
}
