package com.nooglers.domains.test;

import com.nooglers.domains.BaseEntity;
import com.nooglers.domains.User;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Entity( name = "quiz_history" )
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
@ToString( callSuper = true )

public class QuizHistory implements BaseEntity {


    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY )
    private Integer id;

    @Column( name = "created_at", columnDefinition = "timestamp with time zone default current_timestamp" )
    @CreationTimestamp
    private LocalDateTime createdAt;
    @Column( name = "updated_at", columnDefinition = "timestamp with time zone" )
    @CreationTimestamp
    private LocalDateTime updatedAt;

    @Column( name = "started_at", columnDefinition = "timestamp with time zone" )
    private LocalDateTime startedAt;

    @Column( name = "finished_at", columnDefinition = "timestamp with time zone" )
    private LocalDateTime finishedAt;

    @Column( columnDefinition = "smallint default 0", nullable = false )
    private short deleted;

    @OneToOne
    private User createdBy;

    @Column( columnDefinition = "smallint default 0", nullable = false, name = "total_question_count" )
    private int totalQuestionCount;

    @Column( columnDefinition = "smallint default 0", nullable = false, name = "correct_answer_count" )
    private int correctAnswerCount;


    @Column( columnDefinition = "smallint",nullable = false )
    private short percentage;


}
