package com.nooglers.domains.test;

import com.nooglers.domains.BaseEntity;
import com.nooglers.domains.Card;
import com.nooglers.enums.QuizType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity( name = "question" )
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class Question implements BaseEntity {


    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY )
    private Integer id;
    @Column( nullable = false )
    private String definition;
    @Column( name = "user_answer" )
    private String userAnswer;

    @Enumerated( EnumType.STRING )
    @Column( name = "quiz_type" )
    private QuizType quizType;
    @ManyToOne( fetch = FetchType.LAZY )
    private Card card;

    @ManyToOne( fetch = FetchType.LAZY, cascade = CascadeType.ALL )
    private QuizHistory quizHistory;
    @OneToMany( fetch = FetchType.LAZY, cascade = CascadeType.ALL )
    List<Variant> variants;
    @Column( columnDefinition = "bool default false", name = "is_correct" )
    private boolean isCorrect;

    @Column( name = "display_term" )
    private String displayTerm;

}
