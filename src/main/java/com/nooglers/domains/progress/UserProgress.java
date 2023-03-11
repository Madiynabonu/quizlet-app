package com.nooglers.domains.progress;

import com.nooglers.domains.BaseEntity;
import com.nooglers.domains.Card;
import com.nooglers.domains.User;
import com.nooglers.enums.LearningStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity( name = "user_progress" )
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserProgress implements BaseEntity {


    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY )
    private Integer id;


    @ManyToOne
    private User user;

    @ManyToOne
    private Card card;

    @Enumerated( EnumType.STRING )
    @Column( columnDefinition = "varchar default 'NOT_STUDIED'" )
    private LearningStatus status;

    @Column( columnDefinition = "smallint default 0" )
    private short score;

}
