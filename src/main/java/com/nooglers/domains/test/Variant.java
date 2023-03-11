package com.nooglers.domains.test;

import com.nooglers.domains.BaseEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity( name = "variant" )
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class Variant implements BaseEntity {

    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY )
    private Integer id;
    private String term;
    @ManyToOne( cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    private Question question;
    @Column( name = "is_correct" )
    private boolean isCorrect;


}
